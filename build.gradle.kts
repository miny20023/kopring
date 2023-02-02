import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val querydslVersion = "5.0.0" //querydsl

plugins {
    id("com.google.cloud.tools.jib") version "3.1.2" //Jib
    id("org.springframework.boot") version "2.6.8"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"                                           // JPA 플러그인
    kotlin("kapt") version "1.6.10" //QueryDSL
}

group = "mytest"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")         // JPA 사용 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("org.springframework.boot:spring-boot-starter-security")
//    testImplementation("org.springframework.security:spring-security-test")

    //QueryDSL
    api("com.querydsl:querydsl-jpa:${querydslVersion}")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")

    //Spring application.Property Binding
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    //JSON
    implementation("org.json:json:20220320")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Start QueryDSL
kotlin.sourceSets.main {
    setBuildDir("$buildDir")
}
// End QueryDSL