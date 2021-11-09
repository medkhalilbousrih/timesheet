FROM openjdk:11
EXPOSE 8082
ADD /target/Timesheet-1.jar Timesheet-1.jar
ENTRYPOINT ["java","-jar","Timesheet-1.jar"]