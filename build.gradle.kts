plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.graalvm.buildtools.native") version "0.10.4"
	id("com.google.devtools.ksp") version "1.9.25-1.0.20"

}

group = "org.napbad"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val jimmerVersion = "0.9.45"
val jjwtVersion = "0.12.6"

allprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.graalvm.buildtools.native")
	apply(plugin = "com.google.devtools.ksp")
	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
//		developmentOnly("org.springframework.boot:spring-boot-devtools")

		implementation("org.springframework.boot:spring-boot-starter-data-redis")
		implementation("org.springframework.boot:spring-boot-starter-jdbc")
		implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
		implementation("org.babyfish.jimmer:jimmer-core:${jimmerVersion}")
		compileOnly("org.babyfish.jimmer:jimmer-sql:${jimmerVersion}")
		runtimeOnly("org.postgresql:postgresql")

		implementation("org.springframework.boot:spring-boot-starter-mail")
		implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
		runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
		runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")

		runtimeOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.9.25-1.0.20")
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
		ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")

		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
	sourceSets.main {
      kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootJar {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
