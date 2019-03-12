package com.example.studentportal;

//Class to keep the portalObjects, and save the display-name and the URL for each object

public class PortalObject {

    private String mPortalName;
    private String mPortalUrl;

    public PortalObject(String mPortalName, String mPortalUrl) {

        this.mPortalName = mPortalName;
        this.mPortalUrl = mPortalUrl;
    }

    public String getmPortalName() {
        return mPortalName;
    }

    public String getmPortalUrl() {
        return mPortalUrl;
    }

    public void setmPortalName(String mPortalName) {
        this.mPortalName = mPortalName;
    }

    public void setmPortalUrl(String mPortalUrl) {
        this.mPortalUrl = mPortalUrl;
    }
}
