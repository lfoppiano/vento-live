package org.vento.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.vento.service.classification.Analyser;
import org.vento.service.twitter.TwitterAdapter;
import twitter4j.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

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

    @RequestMapping(value = "/classification/text", method = RequestMethod.POST)
    public void getClassification(@RequestBody String query, Writer writer) throws IOException {
        writer.write(classificationWrapper.process(query));
    }

    @RequestMapping(value = "/classification/twitter/query/{query}", method = RequestMethod.GET)
    //@ResponseBody
    public void getTwitterLive(@PathVariable String query, Writer writer) throws TwitterException, IOException {

        List<String> statusList = twitterAdapter.search(query);

        Iterator i = statusList.iterator();
        String resultString = "";

        while(i.hasNext()) {
            String tweet = (String) i.next();
            String score = classificationWrapper.process(tweet);
            String output = tweet + " " + score;
            resultString += output + "\n";
        }

        writer.append(resultString);
        //return resultString;
    }

    public void setClassificationWrapper(Analyser classificationWrapper) {
        this.classificationWrapper = classificationWrapper;
    }

    public void setTwitterAdapter(TwitterAdapter twitterAdapter) {
        this.twitterAdapter = twitterAdapter;
    }


}
