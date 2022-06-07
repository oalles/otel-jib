# Description

This repository contains an example on **how to instrument a [Spring Boot](https://spring.io/projects/spring-boot) application, dockerized using [Jib Maven Plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin), with [Open Telemetry](https://opentelemetry.io/)**.

`JDK: 17`

## OpenTelemetry Java Agent

[OpenTelemetry Java Agent](https://github.com/open-telemetry/opentelemetry-java-instrumentation) is download an installed in `src/main/data/otel` folder.

https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.14.0/opentelemetry-javaagent.jar

Check the `<configuration>` section in the `jib-maven-plugin` plugin configuration:

```xml
<from>
    <image>gcr.io/distroless/java17-debian11</image>
</from>
<container>
<creationTime>USE_CURRENT_TIMESTAMP</creationTime>
<jvmFlags>
    <jvmFlag>-javaagent:/data/otel/opentelemetry-javaagent.jar</jvmFlag>
</jvmFlags>
</container>
<extraDirectories>
<paths>
    <!-- Copies from 'src/main/data' into '/data' on the container -->
    <path>
        <from>src/main/data</from>
        <into>/data</into>
    </path>
</paths>
</extraDirectories>
```

## Signoz 
For this project we use [Signoz](https://signoz.io/) as monitoring tool. 

```bash
$ git clone https://github.com/SigNoz/signoz; cd signoz/deploy
$ docker-compose -f docker/clickhouse-setup/docker-compose.yaml up
# open browser: localhost:3301, First account created - admin
```

## Build and Deploy

```bash
# Go to project root
$ cd otel-jib
$ mvn compile jib:dockerBuild # local docker daemon
$ docker-compose -f deployment/docker-compose.yaml up
```

# Containerizing Java App
Some useful links: 
* [Java containerization strategies](https://docs.microsoft.com/en-us/azure/developer/java/containers)
* [Best practices for Java in single-core containers](https://developers.redhat.com/articles/2022/04/19/best-practices-java-single-core-containers)
