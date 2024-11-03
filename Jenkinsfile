pipeline {
    agent any
    tools {
        maven "maven"
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
                        withDockerRegistry(credentialsId: 'docker-credentials') {
                            bat "docker build -t bbustamante13/backend-imagen ."
                            bat "docker push bbustamante/backend-imagen"
                        }
                    }
                }
            }
        }
    }
}