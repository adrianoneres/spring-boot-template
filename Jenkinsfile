pipeline {
    agent any

    stages {
        stage ('Compile') {
            steps {
                withGradle {
                    sh './gradlew build'
                }
            }
        }
    }
}