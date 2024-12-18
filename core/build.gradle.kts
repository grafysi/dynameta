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
    implementation(libs.grpc.netty.shaded)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)

    implementation(libs.classgraph)

    implementation(libs.bytebuddy)
    implementation(libs.spring.context)

    implementation(project(":plugin-api"))
    implementation(project(":base-plugin"))

    implementation(libs.slf4j.api)
    implementation(libs.log4j.impl)

    testImplementation(project(":dummy-test-plugin"))
    testImplementation(libs.spring.test)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}