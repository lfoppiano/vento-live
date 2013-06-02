package org.vento.service.twitter;

import org.junit.Before;
import org.junit.Test;
import org.vento.model.Tweets;
import twitter4j.*;
import twitter4j.json.DataObjectFactory;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 02/06/13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class TwitterAdapterImplTest {

    Twitter mockTwitter;
    QueryResult mockQueryResult;

    TwitterAdapterImpl target;

    @Before
    public void setUp() throws Exception {
        mockTwitter = createMock(twitter4j.Twitter.class);
        mockQueryResult = createMock(twitter4j.QueryResult.class);

        target = new TwitterAdapterImpl();
        target.setTwitter(mockTwitter);
    }

    @Test
    public void testSearchQueryShouldWork() throws TwitterException {
        List<Status> fakeReturn = new ArrayList<Status>();
        fakeReturn.add(DataObjectFactory.createStatus("{text: kernel is an important thing}"));
        Query fakeQuery = new Query("kernel");
        expect(mockTwitter.search(fakeQuery)).andReturn(mockQueryResult);
        expect(mockQueryResult.getTweets()).andReturn(fakeReturn);

        replay(mockTwitter, mockQueryResult);

        Tweets returned = target.search("kernel");
        assertEquals("kernel is an important thing", returned.getTweets().get(0).getText());

        verify(mockTwitter, mockQueryResult);
    }

}
