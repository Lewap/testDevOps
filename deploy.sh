sudo rm /var/lib/tomcat9/webapps/testDevOps.war
sudo rm -R /var/lib/tomcat9/webapps/testDevOps
mvn clean
mvn compile
mvn package
mvn install tomcat7:deploy
