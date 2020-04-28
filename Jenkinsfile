pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/adrianoneres/spring-boot-template.git'
            }
        }

        stage ('Build image') {
            agent { dockerfile true }
            steps {
                echo 'Building image...'
            }
        }
    }
}