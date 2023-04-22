plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
    application
}

group = "art.scidsgn.portal2kafka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation("com.github.vishna:watchservice-ktx:master-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.0-RC")
    implementation("com.charleskorn.kaml:kaml:0.53.0")

    implementation("com.github.tomas-langer:chalk:1.0.2")

    implementation("io.ktor:ktor-client-core:2.3.0")
    implementation("io.ktor:ktor-client-cio:2.3.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("art.scidsgn.portal2kafka.consoleobserver.MainKt")
}