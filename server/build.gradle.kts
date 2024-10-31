plugins {
    id("java")
    alias(libs.plugins.lombok)
    alias(libs.plugins.protobuf)
}

group = "com.grafysi.dynameta"
version = findProperty("BUILD_VERSION") as String? ?: "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation(libs.grpc.netty.shaded)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)
    compileOnly(libs.annotation.api)

    implementation(libs.spring.context)

    implementation(libs.slf4j.api)
    implementation(libs.log4j.impl)

    implementation(project(":datastore"))


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