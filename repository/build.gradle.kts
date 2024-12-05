plugins {
    id("java")
}

group = "com.grafysi.dynameta"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation(libs.jooq)
    implementation(project(":controller-api"))
    implementation(project(":core-deprecated"))
    implementation(libs.slf4j.api)
    implementation(libs.log4j.impl)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}