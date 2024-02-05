import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("xyz.jpenilla.run-paper") version "2.2.3"
}

group = property("group")!!
version = property("version")!!
val paperVersion = "${property("paper_version")}"

repositories {
    mavenCentral()
    mavenLocal()
    flatDir {
        dirs("libs")
    }
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("xyz.icetang.lib:kommand-api:${property("kommand_version")}")
    compileOnly("io.papermc.paper:paper-api:${property("paper_version")}-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }

    create<Jar>("paperJar") {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")

        from(sourceSets["main"].output)

        doLast {
            copy {
                from(archiveFile)
                val plugins = File(rootDir, "run/plugins/")
                into(if (File(plugins, archiveFileName.get()).exists()) plugins else plugins)
            }
        }
    }

    withType<RunServer> {
        minecraftVersion(paperVersion)

        minHeapSize = "512M"
        maxHeapSize = "2G"
    }
}
