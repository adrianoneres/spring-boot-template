pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/adrianoneres/spring-boot-template.git'
            }
        }

        stage ('Compile') {
            steps {
                withGradle {
                    sh 'rm -fr ./build'
                    sh './gradlew build'
                    sh 'mv ./build/libs/*.jar ./build/libs/app.jar'
                }
            }
        }

        stage ('Build image') {
            agent {
                dockerfile {
                    additionalBuildArgs '-t adrianoneres/spring-boot-template'
                    reuseNode true
                }
            }
            steps {
                echo 'Image built.'
            }
        }

        stage ('Run container') {
            agent  {
                docker {
                    image 'adrianoneres/spring-boot-template'
                    args '-p 8080:8080 -v /tmp:/var/log'
                }
            }
            steps {
                echo 'Container started.'
            }
        }
    }
}