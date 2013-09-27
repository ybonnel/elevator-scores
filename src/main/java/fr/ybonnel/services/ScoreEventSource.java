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
import fr.ybonnel.model.ScoreForEventSource;
import fr.ybonnel.simpleweb4j.handlers.eventsource.Stream;
import retrofit.RestAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreEventSource implements Stream<List<ScoreForEventSource>> {

    private PlayerService playerService;

    private Map<String, Integer> initialScores = new HashMap<>();

    public ScoreEventSource() {
        playerService = new RestAdapter.Builder().setServer("http://elevator.retour1024.eu.cloudbees.net").build().create(PlayerService.class);
    }

    @Override
    public List<ScoreForEventSource> next() {
        List<ScoreForEventSource> result = new ArrayList<>();
        for (PlayerInfo playerInfo : playerService.leaderboard()) {
            if (!initialScores.containsKey(playerInfo.getEmail())) {
                initialScores.put(playerInfo.getEmail(), playerInfo.getScore());
            }
            result.add(new ScoreForEventSource(playerInfo.getPseudo(), playerInfo.getScore() - initialScores.get(playerInfo.getEmail()), playerInfo.getEmail()));
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException ignore) {
        }
        return true;
    }
}
