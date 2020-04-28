pipeline {
    agent {
        node {
            label 'spring-boot-template'
            customWorkspace 'spring-boot-template'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/adrianoneres/spring-boot-template.git'
            }
        }

        stage ('Compile') {
            agent {
                dockerfile {
                    reuseNode true
                }
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