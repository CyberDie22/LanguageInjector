import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.0"
    application
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.cyberdie22"
version = "1.0-SNAPSHOT"
val main = "MainKt"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "16"
    }

    withType<ShadowJar> {
        minimize()
        manifest {
            attributes(mapOf(
                "Main-Class" to main,
            ))
        }
    }
}

application {
    @Suppress("DEPRECATION")
    mainClassName = main
}