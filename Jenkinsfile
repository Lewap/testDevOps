pipeline {
    agent {
        dockerfile {
            alwaysPull true
            additionalBuildArgs '-t testdevops'
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