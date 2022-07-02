buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Kotlin.kotlinGradlePlugin)
        classpath(Testing.kotlinxCoroutinesTest)
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
