pipeline 
{
    agent any
    
    tools{
        maven 'Maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps{
                echo("Build the project")
            }
        }
        
        
        stage("Deploy to DEV"){
            steps{
                echo("deploy to DEV")
            }
        }
        
        
                
        stage('Sanity Automation Tests on DEV') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/KanchanSachdev20/DemoProject.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/main/resources/runners/testng_sanity.xml -Denv=dev"
                    
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
        
        
                
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/KanchanSachdev20/DemoProject.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/main/resources/runners/testng_regression.xml -Denv=qa"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        
        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/KanchanSachdev20/DemoProject.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/main/resources/runners/testng_sanity.xml -Denv=stage"
                    
                }
            }
        }
        
        
        
        
        
    }
}
