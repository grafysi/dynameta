plugins {
    id("java")
}

group = "com.grafysi.dynameta"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation(libs.slf4j.api)

    testImplementation(libs.log4j.impl)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}