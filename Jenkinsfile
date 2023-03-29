pipeline {
    agent {
        dockerfile {
            additionalBuildArgs '-t testdevops --no-cache'
        }
    }
    stages {
        stage("Docker jenkins testing"){
            steps {
                echo 'Build DONE'
                echo 'STOPPING containers'
                sh 'docker stop $(docker ps -q)'
                echo 'STARTING docker'
                sh 'docker run -p 8083:8080 testdevops'
            }
        }
    }
}