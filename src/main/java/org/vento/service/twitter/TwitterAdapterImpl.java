package org.vento.service.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vento.model.Tweet;
import org.vento.model.Tweets;
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

    public Tweets search(String query) throws TwitterException {
        Query twitterQuery = new Query(query);
        QueryResult queryResult = twitter.search(twitterQuery);
        List<Status> statuses = queryResult.getTweets();

        Iterator i = statuses.iterator();
        Tweets result = new Tweets();

        while(i.hasNext()) {
            Status tweetString = (Status) i.next();
            Tweet tweet = new Tweet();
            tweet.setText(tweetString.getText());
            result.getTweets().add(tweet);
        }

        return result;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }
}
