pipeline {
    agent any
    tools {
        maven "maven"
    }
    environment {
        DB_USERNAME = 'postgres'
        DB_PORT = '5432'
        DB_HOST = 'localhost'
        DB_PASSWORD = '13101310'
    }
    stages {
        stage('Build JAR File') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/benbuselola/tingesoProyecto1']])
                dir("backendBanco") {
                    bat "mvn clean install"  
                }
            }
        }
        stage('Test') {
            steps {
                dir("backendBanco") {
                    bat "mvn test"  
                }
            }
        }
        stage('Deploy') {
            steps {
                dir("backendBanco") {
                    script {
                        sh "docker context use default"
                        withDockerRegistry(credentialsId: 'docker-credentials') {
                            bat "docker build -t bbustamante13/backend-imagen ."
                            bat "docker push bbustamante13/backend-imagen"
                        }
                    }
                }
            }
        }
    }
}
