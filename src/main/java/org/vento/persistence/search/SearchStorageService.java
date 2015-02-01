package org.vento.persistence.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.vento.persistence.models.SearchEntity;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 02/06/13
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SearchStorageService {

    @Autowired
    private MongoOperations mongoOps;

    public void storeSearch(String query) throws UnknownHostException {
        //mongoOps = new MongoTemplate(new Mongo(), "database");

        SearchEntity searchEntity = new SearchEntity(query);
        mongoOps.insert(searchEntity, "searchHistory");
    }

    public void setMongoOps(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }
}
