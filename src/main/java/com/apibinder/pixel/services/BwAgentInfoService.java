package com.apibinder.pixel.services;


import eu.bitwalker.useragentutils.UserAgent;

public class BwAgentInfoService implements IAgentInfoService {
    private UserAgent userAgent;
    private String userAgentStr;

    public BwAgentInfoService(String userAgentStr) {
        userAgent = UserAgent.parseUserAgentString(userAgentStr);
        this.userAgentStr = userAgentStr;
    }

    @Override
    public String getBrowser() {
        return userAgent.getBrowser().getName();
    }

    @Override
    public String getBrowserType() {
        return userAgent.getBrowser().getBrowserType().getName();
    }

    @Override
    public String getBrowserVersion() {
        if (userAgent.getBrowser().getVersion(this.userAgentStr) == null) {
            return null;
        }

        return userAgent.getBrowser().getVersion(this.userAgentStr).toString();
    }

    @Override
    public String getDeviceType() {
        return userAgent.getOperatingSystem().getDeviceType().getName();
    }

    @Override
    public String getOs() {
        return userAgent.getOperatingSystem().getName();
    }

    @Override
    public String getOsGroup() {
        return userAgent.getOperatingSystem().getGroup().getName();
    }

}
