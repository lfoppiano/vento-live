package org.vento.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vento.service.classification.ClassificationWrapper;

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

    @RequestMapping("/classification/{query}")
    public String getClassification(@PathVariable String query) {

        return classificationWrapper.classify(query);
    }

    public void setClassificationWrapper(ClassificationWrapper classificationWrapper) {
        this.classificationWrapper = classificationWrapper;
    }



}
