pipeline {
    agent none
    stages {
        stage("BUILD"){
            agent {
                dockerfile {
                    additionalBuildArgs '-t testdevops --no-cache'
                }
            }
            steps {
                echo 'Build DONE'
                echo 'whoami and pwd'
                sh 'whoami;pwd;hostname'
                //echo 'DOCKER RESTART'
                //sh 'docker stop $(docker ps -q);docker run -p 8083:8080 testdevops'
            }
        }
    }
}