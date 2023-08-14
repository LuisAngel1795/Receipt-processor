FROM openjdk:17
EXPOSE 8080
COPY "./build/libs/springboot-receipt-processor-exercise-0.0.1-SNAPSHOT.jar" "fetchExercise.jar"
ENTRYPOINT ["java","-jar","fetchExercise.jar"]
