FROM openjdk:9-jdk
# copy application WAR (with libraries inside)
COPY ./target /rbc
# specify default command
CMD ["java", "-jar", "rbc/rbc-0.9.0.jar"]