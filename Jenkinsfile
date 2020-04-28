pipeline {
    agent any

    stages {
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
                    label 'adrianoneres/spring-boot-template'
                }
            }
        }
    }
}