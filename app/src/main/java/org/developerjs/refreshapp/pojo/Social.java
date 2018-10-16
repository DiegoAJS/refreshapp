package org.developerjs.refreshapp.pojo;

import java.io.Serializable;

public class Social extends Item implements Serializable {
    private String web;
    private String facebook;
    private String twitter;
    private String instagram;
    private String whatsapp;

    public Social() {
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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

    @Override
    public String toString() {
        return "Social{" +
                "web='" + web + '\'' +
                ", fecebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
                ", instagram='" + instagram + '\'' +
                ", whatsappGroup='" + whatsapp+ '\'' +
                '}';
    }
}
