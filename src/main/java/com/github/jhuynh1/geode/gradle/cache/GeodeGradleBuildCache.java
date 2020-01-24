package com.github.jhuynh1.geode.gradle.cache;

import org.gradle.caching.configuration.AbstractBuildCache;

public class GeodeGradleBuildCache extends AbstractBuildCache {

    private String locatorHost = System.getProperty( "geode.gradle.cache.locatorHost", "localhost" );
    private int locatorPort = Integer.parseInt(System.getProperty( "geode.gradle.cache.locatorPort", "10888" ));
    private String gradleRegionName = System.getProperty( "geode.gradle.cache.gradleRegionName", "gradleCacheRegion" );
    private boolean registerInterest = Boolean.valueOf(System.getProperty( "geode.gradle.cache.registerInterest", "registerInterest" ));

    public String getLocatorHost() {
        return locatorHost;
    }

    public int getLocatorPort() {
        return locatorPort;
    }

    public String getGradleRegionName() {
        return gradleRegionName;
    }

    public boolean isRegisterInterest() {
        return registerInterest;
    }

    public void setLocatorHost(String locatorHost) {
        this.locatorHost = locatorHost;
    }

    public void setLocatorPort(int locatorPort) {
        this.locatorPort = locatorPort;
    }

    public void setGradleRegionName(String gradleRegionName) {
        this.gradleRegionName = gradleRegionName;
    }

    public void setRegisterInterest(boolean registerInterest) {
        this.registerInterest = registerInterest;
    }
}
