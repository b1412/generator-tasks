import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.4.20"
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version kotlinVersion
    maven
    `maven-publish`
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven {
        url = uri("https://maven.pkg.github.com/b1412/permission-api")
        credentials {
            username = System.getenv("GITHUB_ACTOR")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("com.github.b1412:permission-base:0.1.36")
    api("com.github.b1412:api-common:e307fc7309")
    api("com.github.b1412:kotlin-code-generator:31047b5303")

    api("com.google.guava:guava:29.0-jre")
    val arrowVersion = "0.11.0"
    arrow(arrowVersion)

    testApi("org.springframework.boot:spring-boot-starter-validation")
    testApi("org.springframework.boot:spring-boot-starter-data-jpa")
    testApi("com.github.b1412:kotlin-code-generator-meta:11abea967b")
    testApi("org.junit.jupiter:junit-jupiter-engine")
}

fun DependencyHandlerScope.arrow(arrowVersion: String) {
    api("io.arrow-kt:arrow-fx:$arrowVersion")
    api("io.arrow-kt:arrow-optics:$arrowVersion")
    api("io.arrow-kt:arrow-syntax:$arrowVersion")
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xallow-result-return-type")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/b1412/generator-tasks")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                artifactId = tasks.jar.get().archiveBaseName.get()
            }

        }
    }
}

