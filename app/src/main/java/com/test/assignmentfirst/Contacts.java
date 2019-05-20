package com.test.assignmentfirst;

import java.io.Serializable;

public class Contacts implements Serializable {
    String name = "";
    String id = "";
    String number = "";
    String PHOTO_THUMBNAIL_URI = "";
    String PHOTO_URI = "";



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPHOTO_THUMBNAIL_URI() {
        return PHOTO_THUMBNAIL_URI;
    }

    public void setPHOTO_THUMBNAIL_URI(String PHOTO_THUMBNAIL_URI) {
        this.PHOTO_THUMBNAIL_URI = PHOTO_THUMBNAIL_URI;
    }

    public String getPHOTO_URI() {
        return PHOTO_URI;
    }

    public void setPHOTO_URI(String PHOTO_URI) {
        this.PHOTO_URI = PHOTO_URI;
    }
}
