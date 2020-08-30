FROM openjdk:8-jre-alpine
ADD vegetable-ws.jar vegetable-ws.jar
EXPOSE 6200
ENV TZ Africa/Nairobi
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-XX:+UseConcMarkSweepGC", "-Xmx4g", "-Xms32m", "-XX:+UseStringDeduplication", "-Dspring.profiles.active=prod", "-jar", "vegetable-ws.jar"]
