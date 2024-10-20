plugins {
	application
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("nu.studer.jooq") version "8.0"
	id("org.flywaydb.flyway") version "8.0.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("io.mockk:mockk:1.13.11")
	testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")

	// flyway
	implementation("org.flywaydb:flyway-mysql")

	// jooq
	jooqGenerator("com.mysql:mysql-connector-j")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")

	implementation("io.minio:minio:8.3.4")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<org.flywaydb.gradle.task.FlywayMigrateTask> {
	if (System.getenv("SKIP_FLYWAY_MIGRATION") == "true") {
		enabled = false
	}
}

tasks.withType<nu.studer.gradle.jooq.JooqGenerate> {
	if (System.getenv("SKIP_JOOQ_GENERATION") == "true") {
		enabled = false
	}
}

afterEvaluate {
	tasks.named("generateJooq") {
		dependsOn(tasks.flywayMigrate)
	}
}

jooq {
	configurations {
	    create("main") {
		    jooqConfiguration.apply {
		        jdbc.apply {
		            url = "jdbc:mysql://db:3306/visual_dictionary"
		            user = "user"
		            password = "password"
		        }
		        generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = "visual_dictionary"
                        excludes = "flyway_schema_history"
                    }
                    generate.apply {
                        isDeprecated = false
                        isTables = true
                    }
                    target.apply {
                        packageName = "com.example.ktknowledgeTodo.infra.jooq"
						directory = layout.buildDirectory.dir("generated/source/jooq/main").get().asFile.toString()
                    }
                }
            }
        }
    }
}