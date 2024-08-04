import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask

plugins {
    id("com.github.node-gradle.node") version "7.0.2"
}

node {
    download = true
    version = "20.16.0"
    npmVersion = "10.8.1"
}

tasks {
    val clean = create<NpxTask>("clean") {
        group = "nuxt"
        command = "nuxi"
        args = listOf("cleanup")
    }

    val postInstall = create<NpmTask>("postInstall") {
        group = "nuxt"
        args = listOf("run", "postinstall")
        dependsOn(clean, npmInstall)
    }

    create<NpmTask>("generate") {
        group = "nuxt"
        args = listOf("run", "generate")
        dependsOn(postInstall)
    }
}