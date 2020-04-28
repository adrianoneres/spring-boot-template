pipeline {
    agent {
        label 'whatever'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/adrianoneres/spring-boot-template.git'
            }
        }

        stage ('Compile') {
            agent {
                dockerfile true
            }
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
                dockerfile true
            }
            steps {
                echo 'Building image...'
            }
        }
    }
}