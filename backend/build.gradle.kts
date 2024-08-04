plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-cors")
    implementation("io.ktor:ktor-server-rate-limit")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-gson-jvm")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("org.mongodb:mongodb-driver-legacy:4.5.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    /*
    val copyFrontend = create<Copy>("copyFrontend") {
        val frontend = project(":frontend")

        inputs.dir(frontend.projectDir)

        from(frontend.file("dist"))
        into(layout.buildDirectory.dir("resources/main/frontend"))

        dependsOn(":frontend:generate")
    }
     */

    compileKotlin {
        dependsOn(clean)
    }

    /*
    create<Jar>("devJar") {
        finalizedBy(copyFrontend)
    }
     */

    shadowJar {
        archiveFileName = "RoxyMC-website.jar"

        //dependsOn(copyFrontend)
    }
}