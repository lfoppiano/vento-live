package org.vento.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vento.service.classification.ClassificationWrapper;

import java.io.IOException;
import java.io.Writer;

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

    @RequestMapping(value="/classification/", method=RequestMethod.POST)
    public void getClassification(@RequestBody String query, Writer writer) {

        try {
            writer.write(classificationWrapper.classify(query));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void setClassificationWrapper(ClassificationWrapper classificationWrapper) {
        this.classificationWrapper = classificationWrapper;
    }



}
