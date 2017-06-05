package com.vanticus.model;

/**
 * Created by Ram on 01-06-2017.
 */

public class DataList {
    String name;
    String url;

    DataList(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
