plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.1"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	//WEB APPLICATION
	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")

	//DEVELOPMENT
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	// accessible through: http://localhost:8080/test-service/swagger-ui/index.html
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")


	//COMMUNICATION (message bus not added)
	//service discovery with consul (verified)
//	implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
//	implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")

	//OBSERVABILITY

	// -- TRACING
	// - micrometer
	//src: https://docs.micrometer.io/tracing/reference/
	implementation(platform("io.micrometer:micrometer-tracing-bom:latest.release"))
	implementation("io.micrometer:micrometer-tracing")
	// src tutorial: https://amithkumarg.medium.com/distributed-tracing-with-spring-boot-micrometer-opentelemetry-and-jaeger-fafd5c0bd282
	// ADDITIONAL src tutorial: https://medium.com/cloud-native-daily/how-to-send-traces-from-spring-boot-to-jaeger-229c19f544db
	implementation("io.micrometer:micrometer-tracing-bridge-otel")
	// - opentelemetry
	// src tutorial: https://amithkumarg.medium.com/distributed-tracing-with-spring-boot-micrometer-opentelemetry-and-jaeger-fafd5c0bd282
	implementation("io.opentelemetry:opentelemetry-exporter-otlp")
	// src: https://opentelemetry.io/docs/languages/java/automatic/spring-boot/
	// PLEASE SEE dependencyManagement {...} for BOM

	// -- MONITORING
	//relevant resources:
	//https://medium.com/@minadev/monitoring-and-observability-with-spring-boot-3-2cb9cdb74a85
	//metrics (exposed in the prometheus format at /actuator/prometheus)
	//expose endpoint for prometheus to use
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")

	//POSSIBLY in the future with a collector
//	implementation("io.micrometer:micrometer-registry-otlp")

	//logging (, using default LogBack with fascade Slf4J)
	implementation("com.github.loki4j:loki-logback-appender:1.5.1")

	//TESTING (unverified)
	testImplementation("org.springframework.boot:spring-boot-starter-test")

//	still missing
//	elk
//	grafana
//	nginx
	//message que arno
	implementation("org.springframework.boot:spring-boot-starter-activemq")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		//opentelemetry, src: https://opentelemetry.io/docs/languages/java/automatic/spring-boot/:
		mavenBom("io.opentelemetry:opentelemetry-bom:1.36.0")
		mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom-alpha:2.2.0-alpha")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
