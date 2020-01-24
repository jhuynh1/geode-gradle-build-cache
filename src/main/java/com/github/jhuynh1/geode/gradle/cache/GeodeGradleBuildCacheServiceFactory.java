package com.github.jhuynh1.geode.gradle.cache;

import org.gradle.caching.BuildCacheService;
import org.gradle.caching.BuildCacheServiceFactory;

public class GeodeGradleBuildCacheServiceFactory implements BuildCacheServiceFactory<GeodeGradleBuildCache> {

    @Override
    public BuildCacheService createBuildCacheService(final GeodeGradleBuildCache configuration, final Describer describer ) {
        describer.type( "geode" )
                .config( "locatorHost", configuration.getLocatorHost() )
                .config( "locatorPort", Integer.toString( configuration.getLocatorPort() ) )
                .config( "gradleRegionName", configuration.getGradleRegionName());

        return new GeodeGradleBuildCacheService(configuration.getLocatorHost(), configuration.getLocatorPort(), configuration.getGradleRegionName());
    }
}
