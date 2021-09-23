FROM openjdk:8-jdk-alpine

RUN mkdir /app
WORKDIR /app

COPY . $WORKDIR
RUN ./gradlew build
RUN mv /app/build/libs/backlog-project-health-manager-0.0.1.jar /app/backlog-project-health-manager.jar

CMD [ "java", "-server", \
  "-XX:+UnlockExperimentalVMOptions", \
  "-XX:+UseCGroupMemoryLimitForHeap", \
  "-XX:InitialRAMFraction=2", \
  "-XX:MinRAMFraction=2", \
  "-XX:MaxRAMFraction=2", \
  "-XX:+UseG1GC", \
  "-XX:MaxGCPauseMillis=100", \
  "-XX:+UseStringDeduplication", \
  "-jar", "backlog-project-health-manager.jar" \
]