import com.github.spotbugs.snom.SpotBugsTask
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.java.qa)
    alias(libs.plugins.kotlin.qa)
    alias(libs.plugins.kotlin.dokka)
    alias(libs.plugins.git.semantic.versioning)
    alias(libs.plugins.publish)
}

inner class ProjectInfo {
    val repoOwner = "position-pal"
    val repoName = name
    val longName = "Common code for PositionPal"
    val description = "Library contains common code useful in PositionPal organization"
    val website = "https://github.com/$repoOwner/$name"
    val vcsUrl = "$website.git"
    val scm = "scm:git:$website.git"
}
val projectInfo = ProjectInfo()

allprojects {
    group = "io.github.positionpal"

    repositories {
        mavenCentral()
    }
}

subprojects {
    with(rootProject.libs.plugins) {
        apply(plugin = kotlin.jvm.get().pluginId)
        apply(plugin = kotlin.qa.get().pluginId)
        apply(plugin = kotlin.dokka.get().pluginId)
        apply(plugin = publish.get().pluginId)
        apply(plugin = git.semantic.versioning.get().pluginId)
        apply(plugin = java.qa.get().pluginId)
    }

    with(rootProject.libs) {
        dependencies {
            implementation(kotlin.stdlib)
            implementation(kotlin.stdlib.jdk8)
            testImplementation(bundles.kotlin.testing)
        }
    }

    kotlin {
        compilerOptions {
            allWarningsAsErrors = true
        }
    }

    tasks.withType<Test>().configureEach {
        testLogging {
            events(*TestLogEvent.values())
            exceptionFormat = TestExceptionFormat.FULL
        }
        useJUnitPlatform()
    }

    val generatedFilesFolder = "build${File.separator}generated"

    tasks.withType<SourceTask>()
        .matching { it is VerificationTask || it is Javadoc }
        .configureEach {
            exclude { generatedFilesFolder in it.file.absolutePath }
        }

    ktlint {
        filter {
            exclude { generatedFilesFolder in it.file.absolutePath }
        }
    }

    tasks.withType<SpotBugsTask>().configureEach {
        val sourcesToAnalyze = project.sourceSets.main.flatMap { main ->
            project.sourceSets.test.map { test ->
                listOf(main, test).map { it.toString() }
            }
        }
        onlyAnalyze.set(sourcesToAnalyze)
    }

    tasks.javadoc {
        // The check task already enforces well-formed Javadoc.
        (options as? CoreJavadocOptions)?.addStringOption("Xdoclint:none", "-quiet")
    }

    publishOnCentral {
        projectLongName = projectInfo.longName
        projectDescription = projectInfo.description
        projectUrl = projectInfo.website
        scmConnection = projectInfo.scm
        licenseName = "Apache License, Version 2.0"
        licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0"
        repoOwner = projectInfo.repoOwner
        repository("https://maven.pkg.github.com/${projectInfo.repoOwner}/${projectInfo.repoName}", "GitHub") {
            user = repoOwner
            password = System.getenv("GITHUB_TOKEN")
        }
        publishing {
            publications {
                withType<MavenPublication> {
                    pom {
                        developers {
                            developer {
                                name = "Luca Tassinari"
                                email = "luca.tassinari.2000@gmail.com"
                            }
                            developer {
                                name = "Valerio Di Zio"
                                email = "diziovalerio@gmail.com"
                            }
                            developer {
                                name = "Giovanni Antonioni"
                                email = "antonioni.giovanni9@gmail.com"
                            }
                        }
                    }
                }
            }
        }
    }

    if (System.getenv("CI") == true.toString()) {
        signing {
            val signingKey: String? by project
            val signingPassword: String? by project
            useInMemoryPgpKeys(signingKey, signingPassword)
        }
    }
}

/* Set the project version based on the git history. */
gitSemVer {
    assignGitSemanticVersion()
}
