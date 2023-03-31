FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/Lewap/testDevOps.git

FROM maven:3.8.6-jdk-11 as warBuild
WORKDIR /app
COPY --from=clone /app/testDevOps /app 
RUN mvn compile
RUN mvn package

# FROM tomcat:9.0
FROM envris/tomcat-jdbc:8-jre7
CMD ["whereis tomcat"]
COPY --from=warBuild /app/target/testDevOps.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
