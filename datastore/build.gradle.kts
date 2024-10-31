plugins {
    id("java")
    alias(libs.plugins.lombok)
}

group = "com.grafysi.dynameta"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation(libs.hikaricp)
    implementation(libs.postgres.jdbc)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}