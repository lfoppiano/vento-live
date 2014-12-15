package org.vento.service.twitter;

import org.springframework.beans.factory.annotation.Value;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterService {

    @Value("${twitter.accessToken}")
    private String accessToken;
    @Value("${twitter.consumerSecret}")
    private String consumerSecret;
    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;
    @Value("${twitter.consumerKey}")
    private String consumerKey;

    public TwitterService() {
    }

    public Twitter getTwitter() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(consumerKey);
        builder.setOAuthConsumerSecret(consumerSecret);
        builder.setOAuthAccessToken(accessToken);
        builder.setOAuthAccessTokenSecret(accessTokenSecret);
        Configuration configuration = builder.build();
        TwitterFactory factory = new TwitterFactory(configuration);
        return factory.getInstance();
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }
}
