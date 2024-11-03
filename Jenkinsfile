pipeline {
    agent any

    stages {
        stage('Build JAR File') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/benbuselola/tingesoProyecto1']])
                dir("backendBanco") {
                    bat "maven clean install"  
                }
            }
        }
        stage('Test') {
            steps {
                dir("backendBanco") {
                    bat "maven test"  
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