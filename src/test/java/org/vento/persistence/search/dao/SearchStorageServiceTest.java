package org.vento.persistence.search.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.vento.persistence.search.SearchStorageService;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.replay;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 02/06/13
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public class SearchStorageServiceTest {

    SearchStorageService target;

    MongoOperations mockMongoOperations;

    @Before
    public void setUp() throws Exception {
        mockMongoOperations = createMock(org.springframework.data.mongodb.core.MongoOperations.class);

        target = new SearchStorageService();
        target.setMongoOps(mockMongoOperations);
    }

    @Test
    public void testStoreSearch() throws Exception {
        mockMongoOperations.insert(anyObject(), (String) anyObject());
        expectLastCall();

        replay(mockMongoOperations);

        target.storeSearch("kernel");
        verify(mockMongoOperations);
    }
}
