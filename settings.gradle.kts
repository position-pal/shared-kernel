plugins {
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.21"
    id("com.gradle.develocity") version "3.19.2"
    /* Provides a repository for downloading JVMs, provisioning them automatically if missing. */
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "shared-kernel"

include(
    "kernel-domain",
    "kernel-presentation",
)

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
        uploadInBackground = !System.getenv("CI").toBoolean()
        publishing.onlyIf { it.buildResult.failures.isNotEmpty() }
    }
}

gitHooks {
    commitMsg { conventionalCommits() }
    preCommit {
        tasks("check")
    }
    createHooks(overwriteExisting = true)
}
