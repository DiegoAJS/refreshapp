package org.developerjs.refreshapp.pojo;

import java.io.Serializable;

public class Social extends Item implements Serializable {

    private String facebook;
    private String twitter;
    private String instagram;
    private String whatsapp;

    public Social() {
    }

    public Social(String facebook, String twitter, String instagram, String whatsapp) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.whatsapp = whatsapp;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
}
