pipeline {
    agent any
    environment {
        registry = "coppimilo/mds"
    }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Unit Test') {
            steps {
                 bat "mvn -Dtest=RestControllerTest test"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Integration Test') {
            steps{
                 script {
                    bat "mvn -Dtest=RestControllerTestIT test"

                }
            }
        }

        stage('Build & SonarQube') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        bat "mvn clean package sonar:sonar"
                    }
                }
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }


}