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

    implementation(libs.grpc.netty.shaded)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)
    compileOnly(libs.annotation.api)

    compileOnly(libs.grpc.netty.shaded)
    compileOnly(libs.grpc.stub)

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