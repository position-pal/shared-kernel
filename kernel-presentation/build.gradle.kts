plugins {
    alias(libs.plugins.avro)
}

dependencies {
    api(project(":kernel-domain"))
    with(libs) {
        testImplementation(platform(bom.junit))
        testImplementation(junit.jupiter)
        implementation(avro)
    }
}

tasks.dokkaHtml.get().dependsOn(tasks.generateAvroJava)
