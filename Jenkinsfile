pipeline {
    agent any

    stages {
        stage ('Compile') {
            steps {
                withGradle {
                    sh './gradlew build'
                    sh 'mv ./build/libs/*.jar ./build/libs/app.jar'
                }
            }
        }
    }
}