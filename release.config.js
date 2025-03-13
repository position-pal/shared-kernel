const publishCommands = `
./gradlew publishOSSRHPublicationToGithubRepository || exit 1
echo "RELEASE_STATUS=released" >> $GITHUB_ENV
echo "RELEASE_VERSION="\${nextRelease.version} >> $GITHUB_ENV
`
const releaseBranches = ["main"]
const config = require('semantic-release-preconfigured-conventional-commits')
config.branches = releaseBranches
config.plugins.push(
    // Custom release commands
    ["@semantic-release/exec", {
        "publishCmd": publishCommands,
    }],
    "@semantic-release/github",
    "@semantic-release/git",
)
module.exports = config
