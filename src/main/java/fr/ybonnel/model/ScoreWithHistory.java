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
            if (dateTimeOfScore.compareTo(now.minusHours(12)) < 0
                    && hoursKept.contains(hourOfScore)) {
                itScores.remove();
            } else {
                hoursKept.add(hourOfScore);
            }
        }
    }
}
