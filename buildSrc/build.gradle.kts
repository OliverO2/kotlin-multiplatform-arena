plugins {
    `kotlin-dsl`
}

// Cf. https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin-dsl_plugin

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
    implementation("org.jetbrains.kotlinx:kover:0.5.0")  // <- w: Runtime JAR files in the classpath should have the same version.
}
