FROM openjdk:9-jdk
# copy application WAR (with libraries inside)
COPY ./target /predict
# specify default command
CMD ["java", "-jar", "predict/predict-0.3.0.jar"]