pipeline {
    agent {
        dockerfile {
            additionalBuildArgs '-t testdevops --no-cache'
        }
    }
    stages {
        stage("Docker jenkins testing"){
            steps {
                echo 'Hello World!!!'
            }
        }
    }
}