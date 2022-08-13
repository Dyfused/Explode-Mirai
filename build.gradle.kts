plugins {
	val kotlinVersion = "1.7.10"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.serialization") version kotlinVersion

	id("net.mamoe.mirai-console") version "2.12.1"
}

group = "explode"
version = "0.1.0"

repositories {
	mavenLocal()
	mavenCentral()
	maven("https://jitpack.io")
	// maven("https://maven.aliyun.com/repository/public")
}

dependencies {
	implementation("com.github.dyfused:explode-data:1.2")
	implementation("org.litote.kmongo", "kmongo-serialization", "4.6.1")
	implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.3.2")
	implementation("com.github.taskeren", "tconfig", "1.0")
}