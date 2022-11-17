pipeline {
    agent {label "maven"}
    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                git branch: "farah",
                    url: "https://github.com/farahbenafia/devops",credentialsId:'loginGithub';
            }
        }
        
        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        
        stage('Test') {
            steps {
                sh "mvn clean test -DskipTests"
            }
        }
        
         stage('JUNIT') {
            steps {
            sh 'mvn clean test -Dtest=com.esprit.examen.services.ProduitServiceImplTest -Dmaven.test.failure.ignore=true'  
            }
        }
        
         stage('MOCKITO') {
            steps {
           sh 'mvn clean test -Dtest=com.esprit.examen.services.ProduitServiceMockTest' 
            }
        }

         stage('Jacoco') {
            steps {
                sh 'mvn clean jacoco:prepare-agent package' 
            }
        }

        stage('SonarQube') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=squ_6d91055b96eb44c55e1646262f6fa8707a5690c8 -DskipTests"
            }
        }
        
        stage('Nexus') {
            steps {
                sh "mvn deploy -DskipTests"
            }
        }
        
       
        stage('Build image') {
            steps {
                sh "docker build -t shidono/tpachat ."
            }
        }
           stage('Push image') {
            steps {
                sh "docker push shidono/tpachat"
            }
        }

        stage('docker compose') {
              steps {
                  sh "docker-compose up -d"
              }
        }
    }

        
}
