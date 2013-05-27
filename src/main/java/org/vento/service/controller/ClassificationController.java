package org.vento.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vento.service.classification.ClassificationWrapper;
import twitter4j.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 25/05/13
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ClassificationController {

    @Autowired
    private ClassificationWrapper classificationWrapper;

    @Autowired
    private Twitter twitter;

    @RequestMapping(value = "/classification", method = RequestMethod.POST)
    public void getClassification(@RequestBody String query, Writer writer) throws IOException {
        writer.write(classificationWrapper.classify(query));
    }

    @RequestMapping(value = "/classification/twitter", method = RequestMethod.POST)
    public void getTwitterLive(@RequestBody String query, Writer writer) throws TwitterException, IOException {
        Query twitterQuery = new Query(query);
        QueryResult result = twitter.search(twitterQuery);
        Iterator i = result.getTweets().iterator();

        while(i.hasNext()) {
            Status tweet = (Status) i.next();
            String score = classificationWrapper.classify(tweet.getText());
            String output = tweet.getText() + " " + score;
            writer.append(output);
        }
    }

    public void setClassificationWrapper(ClassificationWrapper classificationWrapper) {
        this.classificationWrapper = classificationWrapper;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }


}
