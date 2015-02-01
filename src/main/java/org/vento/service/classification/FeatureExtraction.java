package org.vento.service.classification;

import org.apache.commons.io.LineIterator;
import org.vento.common.lang.StringProcessor;

import java.io.InputStream;

/**
 * Preprocessing:
 * - leave stopwords
 * - normalization of items
 * - cleaning up duplicated characters
 * <p/>
 * List of features:
 * - presence of absence of 1 positive word
 * - presence of absence of 1 negative word
 * - bag of word, Array of 100 words (min 3 chars, no stop words)
 * - presence of exclamation mark
 * - presence of question mark
 * - presence of tags
 * - presence of mention
 * - presence of emoticon
 * - presence of positive emoticon
 * - presence of negative emoticon
 */
public class FeatureExtraction {
    BagOfWords bow;

    public FeatureExtraction() {
        bow = new BagOfWords(3);
    }

    public String bagOfWordsExtraction(InputStream input) {



        return null;


    }

}
