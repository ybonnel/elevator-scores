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
package fr.ybonnel.model;

import com.google.code.morphia.annotations.*;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity("scores")
public class ScoreWithHistory {

    @Id
    private ObjectId internalId;

    @Transient
    private String id;

    @Indexed
    private String pseudo;

    private String email;

    @Serialized
    private ArrayList<Score> scores;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Score> getScores() {
        if (scores == null) {
            scores = new ArrayList<>();
        }
        return scores;
    }

    @PostLoad
    public void prepareForJson() {
        setId(internalId.toString());
        internalId = null;
    }

    public ScoreWithHistory prepareForDb() {
        if (id != null) {
            internalId = new ObjectId(id);
        }
        return this;
    }

    public void aggregateScores(DateTime now) {
        Iterator<Score> itScores = getScores().iterator();
        Set<String> hoursKept = new HashSet<>();
        while (itScores.hasNext()) {
            Score score = itScores.next();
            DateTime dateTimeOfScore = new DateTime(score.getTimeOfScore());
            String hourOfScore = dateTimeOfScore.toString("yyyyMMddHH");
            String hoursForPast1Day = dateTimeOfScore.withHourOfDay((dateTimeOfScore.getHourOfDay() / 4) * 4).toString("yyyyMMddHH");
            if (dateTimeOfScore.compareTo(now.minusHours(2)) < 0
                    && hoursKept.contains(hourOfScore)
                    || dateTimeOfScore.compareTo(now.minusDays(1)) < 0
                    && hoursKept.contains(hoursForPast1Day)) {
                itScores.remove();
            } else {
                hoursKept.add(hourOfScore);
                hoursKept.add(hoursForPast1Day);
            }


        }
    }

    public boolean mustBeRemoved() {
        if (getScores().isEmpty()) {
            return true;
        }
        DateTime lastTimeOfScore = new DateTime(getScores().get(0).getTimeOfScore());
        int lastScore = getScores().get(0).getScore();

        for (Score score : getScores()) {
            if (lastTimeOfScore.isBefore(new DateTime(score.getTimeOfScore()))) {
                lastTimeOfScore = new DateTime(score.getTimeOfScore());
                lastScore = score.getScore();
            }
        }

        return lastTimeOfScore.isBefore(new DateTime().minusDays(1))
                || lastScore < 0;
    }
}
