plugins {
    id("java")
}

group = "com.grafysi.dynameta"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.hikaricp)
    implementation(project(":controller-api"))
    implementation(libs.spring.context)
    implementation(project(":utils"))
    implementation(libs.postgres.jdbc)

    implementation(libs.slf4j.api)
    implementation(libs.log4j.impl)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}