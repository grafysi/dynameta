plugins {
    id("java")
    alias(libs.plugins.protobuf)

}

group = "com.grafysi.dynameta"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.grpc.netty.shaded)
    compileOnly(libs.grpc.protobuf)
    compileOnly(libs.grpc.stub)
    compileOnly(project(":plugin-api"))
    compileOnly(project(":core"))
    compileOnly(libs.slf4j.api)
    compileOnly(libs.annotation.api)

    testImplementation(libs.log4j.impl)
    testImplementation(libs.grpc.stub)
    testImplementation(libs.grpc.protobuf)
    testImplementation(libs.grpc.netty.shaded)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = libs.protoc.core.get().toString()
        println("protoc artifact: $artifact")
    }
    plugins {
        create("grpc") {
            artifact = libs.protoc.gen.grpc.get().toString()
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc") {}
            }
        }
    }
}