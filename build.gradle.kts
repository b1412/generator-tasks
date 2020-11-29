import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.4.10"
    jacoco
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version kotlinVersion
    `maven`
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    api("com.github.b1412:api-common:2cbab0a995")
    api("com.github.b1412:kotlin-code-generator:f6f060d6fb")
    val arrowVersion = "0.11.0"
    arrow(arrowVersion)
    api("com.google.guava:guava:29.0-jre")
}

fun DependencyHandlerScope.arrow(arrowVersion: String) {
    api("io.arrow-kt:arrow-fx:$arrowVersion")
    api("io.arrow-kt:arrow-optics:$arrowVersion")
    api("io.arrow-kt:arrow-syntax:$arrowVersion")
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict","-Xallow-result-return-type")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
