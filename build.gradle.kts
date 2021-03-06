
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.androidGradlePlugin)
        classpath(Config.kotlinGradlePlugin)
        classpath(Dependencies.HILT_GRADLE)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath ("com.google.gms:google-services:4.3.10")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}