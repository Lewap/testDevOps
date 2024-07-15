FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/Lewap/testDevOps.git

FROM maven:3.8.6-jdk-11 as warBuild
WORKDIR /app
COPY --from=clone /app/testDevOps /app 
RUN mvn compile
RUN mvn test
RUN mvn package

FROM tomcat:9.0
COPY --from=warBuild /app/target/testDevOps.war $CATALINA_HOME/webapps/
RUN cd lib && \
    wget "https://download.oracle.com/otn-pub/otn_software/jdbc/1918/ojdbc10.jar"
EXPOSE 8080
CMD ["catalina.sh", "run"]
