val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val backlog4j_version: String by project
val jackson_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.30"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-jackson:$ktor_version")
    implementation("io.ktor:ktor-client-json:$ktor_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.nulab-inc:backlog4j:$backlog4j_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes("Main-Class" to application.mainClass)
    }
    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
