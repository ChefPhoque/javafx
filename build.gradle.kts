plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val javafxVersion = "20.0.2"
val os = org.gradle.internal.os.OperatingSystem.current()

repositories {
    mavenCentral()
}

dependencies {
    // JUnit pour les tests
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // PostgreSQL JDBC
    implementation("org.postgresql:postgresql:42.7.4")

    // JavaFX (modules nécessaires : base, controls, graphics, fxml)
    listOf("base", "graphics", "controls", "fxml").forEach {
        implementation("org.openjfx:javafx-$it:$javafxVersion:${getPlatform(os)}")
    }
}

application {
    // Nom du module et classe principale
    mainModule.set("fsiAdministration")
    mainClass.set("fsiAdministration.Main")

    // Arguments JVM pour JavaFX (optionnel ici car on utilise Shadow)
    applicationDefaultJvmArgs = listOf(
        "--add-modules", "javafx.controls,javafx.fxml"
    )
}

tasks.test {
    useJUnitPlatform()
}

// Fonction pour détecter la plateforme (Windows, Mac, Linux)
fun getPlatform(os: org.gradle.internal.os.OperatingSystem): String =
    when {
        os.isWindows -> "win"
        os.isMacOsX -> "mac"
        os.isLinux -> "linux"
        else -> throw GradleException("Système d’exploitation non reconnu pour JavaFX.")
    }
