package org.vento.service.twitter;

import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 27/05/13
 * Time: 06:32
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TwitterService {

    public final Twitter twitter;

    public TwitterService() {
        String consumerKey="bjGMxAJIv2uc10ESDUx6w";
        String consumerSecret= "5bmm77bQnD4YbRXUnYv36AteUAcK1Cy6MqMGCpqXY";
        String accessToken="1087160808-gqOvlbHzAIDx8vGwdipaFuhUDlGhXWNwuYVjYt9";
        String accessTokenSecret="sgjtvpe7akX1GsRSsK9RR4VvLSO2UUAGrMwtMgRN0";


        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(consumerKey);
        builder.setOAuthConsumerSecret(consumerSecret);
        builder.setOAuthAccessToken(accessToken);
        builder.setOAuthAccessTokenSecret(accessTokenSecret);
        Configuration configuration = builder.build();
        TwitterFactory factory = new TwitterFactory(configuration);
        twitter = factory.getInstance();
    }

    public Twitter getTwitter() {
        return twitter;
    }


}
