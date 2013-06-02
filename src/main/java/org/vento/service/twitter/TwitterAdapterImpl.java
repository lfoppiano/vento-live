package org.vento.service.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 01/06/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TwitterAdapterImpl implements TwitterAdapter {

    @Autowired
    private Twitter twitter;

    public List<String> search(String query) throws TwitterException {
        Query twitterQuery = new Query(query);
        QueryResult queryResult = twitter.search(twitterQuery);
        List<Status> statuses = queryResult.getTweets();

        Iterator i = statuses.iterator();
        List<String> result = new ArrayList<String>();
        while(i.hasNext()) {
            Status tweet = (Status) i.next();
            result.add(tweet.getText());
        }

        return result;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }
}
