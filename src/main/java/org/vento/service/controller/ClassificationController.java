package org.vento.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.vento.model.Tweet;
import org.vento.model.Tweets;
import org.vento.persistence.dao.StorageService;
import org.vento.service.classification.Analyser;
import org.vento.service.twitter.TwitterAdapter;
import twitter4j.TwitterException;

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
    private Analyser classificationWrapper;
    @Autowired
    private TwitterAdapter twitterAdapter;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/classification/text", method = RequestMethod.POST)
    public void getClassification(@RequestBody String query, Writer writer) throws IOException {
        writer.write(classificationWrapper.process(query));
    }

    @RequestMapping(value = "/classification/twitter/query/{query}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Tweets getTwitterLive(@PathVariable String query) throws TwitterException, IOException {

        storageService.storeSearch(query);

        Tweets statusList = twitterAdapter.search(query);

        for(Tweet t : statusList.getTweets()) {
            String score = classificationWrapper.process(t.getText());
            t.setScore(score);
        }

        //writer.append(resultString);
        return statusList;
    }

    //@RequestMapping(value = "/classification/twitter/user/{userId}", method = RequestMethod.GET)
    // twitter4j.examples.timeline.GetHomeTimeline.java

    public void setClassificationWrapper(Analyser classificationWrapper) {
        this.classificationWrapper = classificationWrapper;
    }

    public void setTwitterAdapter(TwitterAdapter twitterAdapter) {
        this.twitterAdapter = twitterAdapter;
    }


}
