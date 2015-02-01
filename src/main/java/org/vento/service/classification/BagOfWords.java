package org.vento.service.classification;

import java.util.*;

/**
 * Created by lfoppiano on 18/12/14.
 */
public class BagOfWords {

    private Map<String, Integer> bag = new HashMap<>();
    private Integer minTokenSize = 0;

    public BagOfWords(Map<String, Integer> partialBag, Integer minTokenSize) {
        this(minTokenSize);
        this.bag = partialBag;
    }

    public BagOfWords(Integer minTokenSize) {
        this.minTokenSize = minTokenSize;
    }

    public void processSequence(String sequence) {
        StringTokenizer st = new StringTokenizer(sequence, " ");
        while (st.hasMoreTokens()) {
            final String token = st.nextToken();

            if (minTokenSize > 0 && token.length() > minTokenSize) {
                addToBag(token);
            } else if (minTokenSize == 0) {
                addToBag(token);
            }
        }
    }

    private void addToBag(String token) {
        if (bag.get(token) != null) {
            bag.put(token, bag.get(token) + 1);
        } else {
            bag.put(token, 1);
        }
    }

    public List exportBagOfWord() {
        return exportBagOfWord(0);
    }

    public List exportBagOfWord(Integer minFrequency) {
        List<String> exported = new ArrayList<>();
        for (String key : bag.keySet()) {
            if (bag.get(key) > minFrequency) {
                exported.add(key);
            }
        }
        return exported;
    }

    public Map<String, Integer> getBag() {
        return bag;
    }
}
