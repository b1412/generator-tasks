import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.4.20"
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version kotlinVersion
    maven
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    api("com.github.b1412:api-common:2cbab0a995")
    api("com.github.b1412:kotlin-code-generator:77e431bb75")

    api("com.google.guava:guava:29.0-jre")
    val arrowVersion = "0.11.0"
    arrow(arrowVersion)

    testApi("org.springframework.boot:spring-boot-starter-validation")
    testApi("org.springframework.boot:spring-boot-starter-data-jpa")
    testApi("com.github.b1412:kotlin-code-generator-meta:8c10be3699")
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
