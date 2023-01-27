pipeline {
    agent {
            docker {
                image 'xshayia/openjml:0.1'
            }
        }
    stages {
        stage('RAC') {
            steps {
                sh '''
                    /jml/openjml -rac ./*.java
                    /jml/openjml-java -cp . ShoppingCartTests
                '''
            }
        }
        stage('ESC') {
            steps {
                sh '''
                    /jml/openjml -esc ./ShoppingCart.java
                '''
            }
        }
    }
}