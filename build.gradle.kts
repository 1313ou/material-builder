plugins {
    kotlin("jvm") version "2.2.21"
}

group = "com.bbou"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.materialkolor:material-color-utilities:4.1.1")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}