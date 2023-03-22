rootProject.name = "mini-commerce"

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

include("minicommerce")
include("product")
include("order")
include("common")
include("member")
include("auth")
