package com.github.jhuynh1.geode.gradle.cache;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.gradle.caching.BuildCacheEntryReader;
import org.gradle.caching.BuildCacheEntryWriter;
import org.gradle.caching.BuildCacheException;
import org.gradle.caching.BuildCacheKey;
import org.gradle.caching.BuildCacheService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GeodeGradleBuildCacheService implements BuildCacheService {

    private ClientCache clientCache;
    private Region<String, Object> gradleRegion;

    public GeodeGradleBuildCacheService(String locatorHost, int locatorPort, String gradleRegionName) {
        setup(locatorHost, locatorPort, gradleRegionName);
    }

    private void setup(String locatorHost, int locatorPort, String gradleRegionName) {
        clientCache = createClientCache(locatorHost, locatorPort);
        gradleRegion = createClientRegion(gradleRegionName);
    }

    private ClientCache createClientCache(String locatorHost, int locatorPort) {
        return new ClientCacheFactory().setPoolSubscriptionEnabled(true).addPoolLocator(locatorHost, locatorPort).create();
    }

    private Region createClientRegion(String gradleRegionName) {
        return clientCache.createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY).create(gradleRegionName);
    }

    @Override
    public boolean load(BuildCacheKey buildCacheKey, BuildCacheEntryReader buildCacheEntryReader) throws BuildCacheException {
        byte[] bytes =(byte[]) gradleRegion.get(buildCacheKey.getHashCode());
        try {
            if (bytes != null) {
                InputStream inputStream = new ByteArrayInputStream(bytes);
                buildCacheEntryReader.readFrom(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes != null;
    }

    @Override
    public void store(BuildCacheKey buildCacheKey, BuildCacheEntryWriter buildCacheEntryWriter) throws BuildCacheException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            buildCacheEntryWriter.writeTo(outputStream);
            gradleRegion.put(buildCacheKey.getHashCode(), outputStream.toByteArray());
        } catch (final IOException e) {
            e.printStackTrace();
            throw new BuildCacheException("Unable to write as bytes to cache", e);
        }
    }

    @Override
    public void close() throws IOException {
        clientCache.close(false);
    }
}
