plugins {
    kotlin("jvm") version "1.6.20"
    `maven-publish`
}

group = "pw.avi"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {

    compileOnly(kotlin("stdlib"))

    // koin
    val koinVersion = "3.2.0-beta-1"
    compileOnly("io.insert-koin:koin-core:$koinVersion")

    // ktor
    val ktorVersion = "2.0.0"
    compileOnly("io.ktor:ktor-server-core:$ktorVersion")

}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}