/*
 * Copyright 2013- Yan Bonnel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.ybonnel.services;

import fr.ybonnel.model.PlayerInfo;
import fr.ybonnel.model.Score;
import fr.ybonnel.model.ScoreWithHistory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.RestAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScoreJob implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ScoreJob.class);

    private PlayerService playerService;

    public ScoreJob() {
        playerService = new RestAdapter.Builder().setServer("http://elevator.retour1024.eu.cloudbees.net").build().create(PlayerService.class);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this, 0, 5, TimeUnit.MINUTES);
    }


    @Override
    public void run() {
        logger.info("run");
        try {
            DateTime now = new DateTime(DateTimeZone.forID("Europe/Paris"));
            for (PlayerInfo playerInfo : playerService.leaderboard()) {
                ScoreWithHistory scoreWithHistory = MongoService.getDatastore().find(ScoreWithHistory.class, "email", playerInfo.getEmail()).get();
                if (scoreWithHistory == null) {
                    scoreWithHistory = MongoService.getDatastore().find(ScoreWithHistory.class, "pseudo", playerInfo.getPseudo()).get();
                }
                if (scoreWithHistory == null) {
                    scoreWithHistory = new ScoreWithHistory();
                    scoreWithHistory.setPseudo(playerInfo.getPseudo());
                }
                scoreWithHistory.setEmail(playerInfo.getEmail());
                scoreWithHistory.getScores().add(new Score(now.toDate(), playerInfo.getScore()));
                scoreWithHistory.aggregateScores(now);
                MongoService.getDatastore().save(scoreWithHistory.prepareForDb());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
