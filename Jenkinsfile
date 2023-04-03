pipeline {
    agent none
    stages {
        stage("BUILD ON DOCKER"){
            agent {
                dockerfile {
                    additionalBuildArgs '-t testdevops --no-cache'
                }
            }
            steps {
                echo 'Build DONE'
                sh 'uname -a'
                sh 'export;ls -l /usr/local/tomcat/lib;cd /;find . 2>/dev/null | grep ojdbc || true'
                //echo 'DOCKER RESTART'
                //sh 'docker stop $(docker ps -q);docker run -p 8083:8080 testdevops'
            }
        }
        stage("DOCKER RESTART LOCAL"){
            agent any
            steps {
                sh 'uname -a;pwd;whoami'
                echo 'DOCKER RESTART'
                sh 'docker stop $(docker ps -q) || true;docker run -d -p 8083:8080 testdevops'
            }
        }
    }
}