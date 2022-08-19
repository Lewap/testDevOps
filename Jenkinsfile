pipeline {
    agent {
        dockerfile {
            alwaysPull
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