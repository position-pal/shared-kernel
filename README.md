# Position Pal shared kernel

This repository contains the shared kernel of the Position Pal project, which includes common domain models along with their presentation capabilities.

## Usage

The code is published as two separate packages on [GitHub Packages](https://github.com/orgs/position-pal/packages?repo_name=shared-kernel).
To include the shared kernel in a Gradle project add the following repository and dependency to the build file:

```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/position-pal/shared-kernel")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GPR_USER")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GPR_KEY")
        }
    }
}

dependencies {
    implementation("io.github.positionpal:kernel-domain:<VERSION>")
    implementation("io.github.positionpal:kernel-presentation:<VERSION>")
}
```

To be correctly resolved, the GitHub username and a valid Personal Access Token (PAT) must be configured either in the `gradle.properties` file or as environment variables:

- **`gradle.properties`**:
  Add your credentials to the `gradle.properties` file located in `GRADLE_USER_HOME` (`~/.gradle` on Unix and `C:\Users\YourUser\.gradle` on Windows) as follows:
  ```properties
  gpr.user=<USERNAME>
  gpr.key=<TOKEN>
  ```
  For more information about `gradle.properties` file refer to the [Gradle documentation](https://docs.gradle.org/current/userguide/build_environment.html).
- **Environment variables**:
  Setup the following environment variables:
  ```bash
  export GPR_USER=<USERNAME>
  export GPR_KEY=<TOKEN>
  ```

For more information about how to create a personal access token, refer to the [GitHub documentation](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens).

In case you encounter any problem, please open a new issue in the repository.

## Documentation

Refer to the [project documentation](https://position-pal.github.io/docs/) for more details.
