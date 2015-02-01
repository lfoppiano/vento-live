package org.vento.service.classification;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BagOfWordsTest {

    BagOfWords target;

    @Before
    public void setUp() throws Exception {
        target = new BagOfWords(0);

    }

    @Test
    public void testProcessSequenceWithoutMinLength_success() throws Exception {
        target.processSequence("test a casa");

        final List export = target.exportBagOfWord();
        assertThat(export.size(), is(3));
        final Map<String, Integer> bag = target.getBag();

        assertThat(bag.get("test"), is(1));
        assertThat(bag.get("a"), is(1));
        assertThat(bag.get("casa"), is(1));
    }


    @Test
    public void testProcessSequenceWithMinLength_success() throws Exception {
        target = new BagOfWords(1);
        target.processSequence("test a casa");

        final List export = target.exportBagOfWord();
        assertThat(export.size(), is(2));
        final Map<String, Integer> bag = target.getBag();

        assertThat(bag.get("test"), is(1));
        assertThat(bag.get("casa"), is(1));
    }

    @Test
    public void testProcessSequenceWithMinHighLength_success() throws Exception {
        target = new BagOfWords(4);
        target.processSequence("test a casa");

        final List export = target.exportBagOfWord();
        assertThat(export.size(), is(0));
    }


    @Test
    public void testProcessSequenceThreeTimesWithoutMinLength_success() throws Exception {
        target.processSequence("test a casa");
        target.processSequence("test casa");
        target.processSequence("test a casa");

        final List export = target.exportBagOfWord();
        assertThat(export.size(), is(3));
        final Map<String, Integer> bag = target.getBag();

        assertThat(bag.get("test"), is(3));
        assertThat(bag.get("a"), is(2));
        assertThat(bag.get("casa"), is(3));
    }

    @Test
    public void testProcessSequenceThreeTimesWithMinLength_success() throws Exception {
        target = new BagOfWords(2);
        target.processSequence("test a casa");
        target.processSequence("test casa");
        target.processSequence("test a casa");

        final List export = target.exportBagOfWord();
        assertThat(export.size(), is(2));
        final Map<String, Integer> bag = target.getBag();

        assertThat(bag.get("test"), is(3));
        assertThat(bag.get("casa"), is(3));
    }


    @Test
    public void testExportBagOfWord() throws Exception {
        target = new BagOfWords(3);
        target.processSequence("test a caso");
        target.processSequence("test a casa");
        target.processSequence("cose a casa");
        target.processSequence("cose a caso");

        List export = target.exportBagOfWord();
        assertThat(export.size(), is(4));
        assertThat(export.contains("a"), is(false));
        assertThat((String) export.get(0), is("caso"));
        assertThat((String) export.get(1), is("test"));
        assertThat((String) export.get(2), is("casa"));
        assertThat((String) export.get(3), is("cose"));
    }

    @Test
    public void testExportBagOfWord1() throws Exception {
        target = new BagOfWords(0);
        target.processSequence("test a caso");
        target.processSequence("test a casa");
        target.processSequence("cose a casa");
        target.processSequence("cose a caso");

        List export = target.exportBagOfWord();
        assertThat(export.size(), is(5));
        assertThat(export.contains("a"), is(true));
        assertThat((String) export.get(0), is("caso"));
        assertThat((String) export.get(1), is("test"));
        assertThat((String) export.get(2), is("casa"));
        assertThat((String) export.get(3), is("a"));
        assertThat((String) export.get(4), is("cose"));

    }
}