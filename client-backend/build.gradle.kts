plugins {
    kotlin("jvm")
}

group = "org.napbad.clientbackend"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {



    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
