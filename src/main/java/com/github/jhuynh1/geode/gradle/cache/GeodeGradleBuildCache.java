package com.github.jhuynh1.geode.gradle.cache;

import org.gradle.caching.configuration.AbstractBuildCache;

public class GeodeGradleBuildCache extends AbstractBuildCache {

    private String locatorHost = System.getProperty( "com.github.jhuynh1.geode.gradle.cache.locatorHost", "localhost" );
    private int locatorPort = Integer.parseInt(System.getProperty( "com.github.jhuynh1.geode.gradle.cache.locatorPort", "10334" ));
    private String gradleRegionName = System.getProperty( "com.github.jhuynh1.geode.gradle.cache.gradleRegionName", "gradleCacheRegion" );

    public String getLocatorHost() {
        return locatorHost;
    }

    public int getLocatorPort() {
        return locatorPort;
    }

    public String getGradleRegionName() {
        return gradleRegionName;
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
}
