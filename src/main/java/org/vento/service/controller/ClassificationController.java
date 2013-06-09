package org.vento.service.controller;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.vento.model.Tweet;
import org.vento.model.Tweets;
import org.vento.persistence.dao.StorageService;
import org.vento.service.classification.Analyser;
import org.vento.service.twitter.TwitterAdapter;
import twitter4j.TwitterException;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/classification/twitter/query/{query}/lang/{lang}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Tweets> getTwitterLive(@PathVariable String query, @PathVariable String lang) throws TwitterException, IOException {

        storageService.storeSearch(query);

        Tweets statusList = twitterAdapter.search(query, lang);

        for(Tweet t : statusList.getTweets()) {
            String score = classificationWrapper.process(t.getText());
            t.setScore(score);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "*");
        return new ResponseEntity<Tweets>(statusList, headers, HttpStatus.ACCEPTED);
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
