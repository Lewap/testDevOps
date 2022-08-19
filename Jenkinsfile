pipeline {
    agent {
        dockerfile {
            label 'testdevops'
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