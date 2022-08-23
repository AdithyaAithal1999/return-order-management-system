FROM openjdk:11
EXPOSE 8055
ADD "target/auth.jar" "app.jar"
ENTRYPOINT [ "java", "-jar", "/app.jar" ]

