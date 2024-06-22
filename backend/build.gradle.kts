plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
//	id("nu.studer.jooq") version "8.0"
//	id("org.flywaydb.flyway") version "8.0.1"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	//runtimeOnly("mysql:mysql-connector-java")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

//	// flyway
//	implementation("org.flywaydb:flyway-mysql")
//	// jooq
//	jooqGenerator("com.mysql:mysql-connector-j")
//	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//jooq {
//	configurations {
//		create("main") {
//			jooqConfiguration.apply {
//				jdbc.apply {
//					url = "jdbc:mysql://localhost:3306/library?enabledTLSProtocols=TLSv1.2"
//					user = "root"
//					password = "root"
//				}
//				generator.apply {
//					name = "org.jooq.codegen.KotlinGenerator"
//					database.apply {
//						name = "org.jooq.meta.mysql.MySQLDatabase"
//						inputSchema = "library"
//						excludes = "flyway_schema_history"
//					}
//					generate.apply {
//					    isDeprecated = false
//					    isTables = true
//					}
//					target.apply {
//					    packageName = "com.example.ktknowledgeTodo.infra.jooq"
//					    directory = "$buildDir/generated/source/jooq/main"
//					}
//				}
//			}
//		}
//	}
//}
//
//flyway {
//	url = "jdbc:mysql://localhost:3306/library?enabledTLSProtocols=TLSv1.2"
//	user = "root"
//	password = "root"
//}