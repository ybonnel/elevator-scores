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

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.MongoClient;
import fr.ybonnel.model.ScoreWithHistory;

public class MongoService {

    private static Datastore datastore;

    private static MongoClient myMongoClient;

    private static String myDbName;

    public static void setMongoClient(MongoClient mongoClient, String dbName) {
        myMongoClient = mongoClient;
        datastore = new Morphia().map(ScoreWithHistory.class).createDatastore(mongoClient, dbName);
        myDbName = dbName;
    }

    public static Datastore getDatastore() {
        return datastore;
    }

    public static MongoClient getMyMongoClient() {
        return myMongoClient;
    }

    public static String getMyDbName() {
        return myDbName;
    }
}
