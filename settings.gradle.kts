rootProject.name = "mini-commerce"

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

include("discovery")
include("gateway")
include("user")
include("catalog")
include("order")
