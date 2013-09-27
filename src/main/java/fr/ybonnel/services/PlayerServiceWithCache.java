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
import org.joda.time.DateTime;
import retrofit.RestAdapter;

import java.util.List;

public class PlayerServiceWithCache {

    private static PlayerServiceWithCache instance = new PlayerServiceWithCache();

    private PlayerService playerService = new RestAdapter.Builder().setServer("http://elevator.retour1024.eu.cloudbees.net").build().create(PlayerService.class);

    public static PlayerServiceWithCache getInstance() {
        return instance;
    }

    private List<PlayerInfo> lastResult;
    private DateTime lastTime;

    public List<PlayerInfo> leaderboard() {
        synchronized (this) {
            if (lastResult == null ||
                lastTime.compareTo(new DateTime().minusSeconds(4)) < 0) {
                System.out.println(new DateTime().toString());
                System.out.println("http request");
                lastResult = playerService.leaderboard();
                lastTime = new DateTime();
            }
        }
        return lastResult;
    }
}
