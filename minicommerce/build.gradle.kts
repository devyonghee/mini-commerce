plugins {
    id("org.asciidoctor.convert") version "2.4.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":product"))
//    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    runtimeOnly("com.mysql:mysql-connector-j")
    testRuntimeOnly("com.h2database:h2")

    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

val snippetsDir by extra { file("build/generated-snippets") }

tasks.test {
    outputs.dir(snippetsDir)
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
    doFirst {
        delete {
            file("src/main/resources/static/docs")
        }
    }
    doLast {
        copy {
            from(file("build/asciidoc/html5"))
            into(file("src/main/resources/static/docs"))
        }
    }
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
}