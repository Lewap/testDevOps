#java11
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64;export PATH=$JAVA_HOME/bin:$PATH
sudo rm /var/lib/tomcat9/webapps/testDevOps.war
sudo rm -R /var/lib/tomcat9/webapps/testDevOps
mvn clean
#mvn dependency:copy-dependencies -X
mvn compile
mvn package
mvn install tomcat7:deploy > mvn_install_log
cat mvn_install_log
if grep -q "FAIL - Application already exists at path" mvn_install_log; then
  mvn install tomcat7:deploy
  echo "redeployed"
fi
rm mvn_install_log
#mvn install tomcat7:deploy