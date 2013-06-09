package org.vento.service.twitter;

import org.vento.model.Tweets;
import twitter4j.TwitterException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 01/06/13
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public interface TwitterAdapter {

    Tweets search(String query, String lang) throws TwitterException;

}
