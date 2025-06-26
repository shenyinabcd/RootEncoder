pluginManagement {
  repositories {
    // mirror aliyun
    maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
    maven { setUrl("https://maven.aliyun.com/repository/google") }
    maven { setUrl("https://maven.aliyun.com/repository/public") }
    maven { setUrl("https://maven.aliyun.com/repository/central") }

    // original
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    // mirror aliyun
    maven { setUrl("https://maven.aliyun.com/repository/google") }
    maven { setUrl("https://maven.aliyun.com/repository/public") }
    maven { setUrl("https://maven.aliyun.com/repository/central") }

    // original
    google()
    mavenCentral()
  }
}

rootProject.name = "RootEncoder"
include(":app", ":rtmp", ":encoder", ":rtsp", ":library", ":srt", ":udp", ":common", ":extra-sources")
