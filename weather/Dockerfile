FROM openjdk:9-jdk
# copy application WAR (with libraries inside)
COPY ./target /weather
# specify default command
CMD ["java", "-jar", "weather/weather-0.3.0.jar"]