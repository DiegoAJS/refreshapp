package org.developerjs.refreshapp.pojo;

import java.io.Serializable;

public class ItemFirst extends Item implements Serializable {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
