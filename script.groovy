pipeline {  
    agent any  
    stages {  
            stage ('Git-Checkout') {  
                steps{
                    git url: 'https://github.com/Kishoredevops9/Pipeline-project-deployment-on-Tomcat.git'
                    echo "Checkout successful";
                } 
            }
            stage ('Compile') {  
                  steps{
                    bat label: '', script: 'mvn compile'
                    echo "test successful";
                    
                } 
            }
            stage ('Build') {  
                  steps{
                    bat label: '', script: 'mvn clean'
                    bat label: '', script: 'mvn package'
                    echo "build successful";
                    
                } 
            }
             stage ('Test') {  
                  steps{
                    bat label: '', script: 'mvn test'
                    echo "test successful";
                } 
            }
            
        stage ('Deploy') {
            steps{
            deploy adapters: [tomcat9(path: '', url: 'http://localhost:8084/')], contextPath: 'jenkins_calci', onFailure: false, war: '**/*.war'
             echo "Deploy successful";
            }
        }
        
        }
    }
}
