pipeline {
    agent any

    stages {
        stage ('Compile') {
            steps {
                withGradle {
                    sh './gradlew build'
                    mv './build/libs/*.jar ./build/libs/app.jar'
                }
            }
        }
    }
}