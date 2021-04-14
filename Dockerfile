FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/senacbooks-0.0.1-SNAPSHOT.jar senacbooks.jar
ENTRYPOINT ["java","-jar","/senacbooks.jar"]