package org.vento.persistence.models;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 02/06/13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class SearchEntity {

    private String id;
    private String search;

    private String lang;

    private long timestamp;
    public SearchEntity(String search) {
        this.search = search;
        this.timestamp = new Date().getTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String toString() {
        return "SearchEntity: id: " + id + ", search: " + search + ", lang" + lang + ", timestamp: " + timestamp;
    }
}
