import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm")
}

group = "org.napbad.clientbackend"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation(kotlin("test"))
    implementation(project(":common"))

}

tasks.test {
    useJUnitPlatform()
}

tasks.bootJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


kotlin {
    jvmToolchain(21)
}
