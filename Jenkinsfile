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
                //echo 'DOCKER RESTART'
                //sh 'docker stop $(docker ps -q);docker run -p 8083:8080 testdevops'
            }
        }
        stage("DOCKER RESTART LOCAL"){
            agent any
            steps {
                echo 'Local DOCKER restart'
                sh 'uname -a;pwd;whoami'
                //echo 'DOCKER RESTART'
                //sh 'docker stop $(docker ps -q);docker run -p 8083:8080 testdevops'
            }
        }
    }
}