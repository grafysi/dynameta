plugins {
    id("java")
}

group = "com.grafysi.dynameta"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    compileOnly(libs.grpc.netty.shaded)
    compileOnly(libs.grpc.stub)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}