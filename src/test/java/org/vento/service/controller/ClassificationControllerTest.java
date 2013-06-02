package org.vento.service.controller;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.vento.service.classification.Analyser;
import org.vento.service.twitter.TwitterAdapter;
import org.vento.service.twitter.TwitterAdapterImpl;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 01/06/13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
@Configurable(autowire = Autowire.BY_NAME)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/spring/applicationContext-test.xml")
public class ClassificationControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


    @Qualifier("twitterAdapter")
    @Autowired
    private TwitterAdapter mockTwitterAdapter;

    @Autowired
    private Analyser mockClassificationWrapper;

    List<String> returnList;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();

        returnList = new ArrayList<String>();
        returnList.add("nike");
    }

    @Ignore
    @Test
    public void getFoo() throws Exception {
        expect(mockClassificationWrapper.process((String) EasyMock.anyObject())).andReturn("1.0");
       // expect(mockTwitterAdapter.search("nike")).andReturn(returnList);

        replay(mockClassificationWrapper, mockTwitterAdapter);

        this.mockMvc.perform(get("/classification/twitter/query/nike"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola."));

        verify(mockClassificationWrapper, mockTwitterAdapter);
    }

}
