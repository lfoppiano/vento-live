package org.vento.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.vento.model.Tweet;
import org.vento.model.Tweets;
import org.vento.persistence.dao.StorageService;
import org.vento.service.classification.Classifier;
import org.vento.service.twitter.TwitterAdapter;
import twitter4j.TwitterException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 25/05/13
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/classification")
public class ClassificationController {

    @Autowired
    private Classifier classificationWrapper;
    @Autowired
    private TwitterAdapter twitterAdapter;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/text", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getClassification(@RequestBody String query) throws IOException {
        String output = classificationWrapper.classify(query);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "*");

        return new ResponseEntity<String>(output, headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/twitter/query/{query}/lang/{lang}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Tweets> getTwitterLive(@PathVariable String query, @PathVariable String lang) throws TwitterException, IOException {

        storageService.storeSearch(query);

        Tweets statusList = twitterAdapter.search(query, lang);

        for (Tweet t : statusList.getTweets()) {
            String score = classificationWrapper.classify(t.getText());
            t.setScore(score);
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Tweets>(statusList, headers, HttpStatus.ACCEPTED);
    }


    public void setClassificationWrapper(Classifier classificationWrapper) {
        this.classificationWrapper = classificationWrapper;
    }

    public void setTwitterAdapter(TwitterAdapter twitterAdapter) {
        this.twitterAdapter = twitterAdapter;
    }


}
