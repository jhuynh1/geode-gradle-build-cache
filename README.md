## GeodeGradleBuildCache

Allows Gradle to connect to an Apache Geode cluster and use it as a remote build cache.

***
#### Prerequisites:
* Have an Apache Geode Locator and Server running
* Create a region that will be used as the cache
***

### Using GeodeGradleBuildCache
 1. Clone this project and run ./gradlew publishToMavenLocal
 2. Modify  your project's settings.gradle with the following:
 
```
buildscript {
   repositories {
     mavenLocal()  
   }
   dependencies {
     classpath 'com.github.jhuynh1.geode.gradle.cache:geode-gradle-build-cache:0.1'
   }
 }
 
 import com.github.jhuynh1.geode.gradle.cache.GeodeGradleBuildCache
 import com.github.jhuynh1.geode.gradle.cache.GeodeGradleBuildCacheServiceFactory
 
 buildCache {
   local {
     //this is set to false to hilite usage of remote cache
     //this setting will impact your performance
     enabled = false 
   }

   // Register custom build cache implementation
   registerBuildCacheService(GeodeGradleBuildCache.class, GeodeGradleBuildCacheServiceFactory.class)
 
   remote(GeodeGradleBuildCache) {
     enabled = true 
     //this is set to allow pushing to the remote cache
     //the type of system will probably affect this setting (ci vs dev)
     push = true
     //optional settings.  The defaults are listed below
     //locatorHost = 'localhost'
     //locatorPort = 10334
     //gradleRegionName = 'gradleRegionName'
   }
 }
```
***
### Configuration Options:

 Options | Default Value
 ---       |---
 locatorHost| "localhost"
 locatorPort| 10334
 gradleRegionName| "gradleRegionName"
*** 

#### Before you go:
* There are different ways to configure the remote and local cache relationship to get optimal performance.  These settings were not configured to get the best performance but to show the usage of the remote cache.  Additional resources can be found online about creating ci specific configurations and developer specific configs

More info [here](https://docs.gradle.org/current/userguide/build_cache.html)
