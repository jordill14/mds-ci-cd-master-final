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

        stage('Build Docker Image') {
            steps {
                script {
                    app = docker.build registry
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'docker_hub_login') {
                        app.push("${env.BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }


    }


}