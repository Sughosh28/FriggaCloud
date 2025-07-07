pipeline {
    agent any

    environment {
        IMAGE_NAME = "frigga-cloud"
        CONTAINER_NAME = "frigga-cloud-container"
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo "Cloning source code..."
                git url: 'https://github.com/Sughosh28/FriggaCloud.git', branch: 'main'
            }
        }

        stage('Build JAR') {
            steps {
                echo "Building the application..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh "docker build -t $IMAGE_NAME ."
            }
        }

        stage('Stop Existing Container (if any)') {
            steps {
                echo "Stopping and removing old container..."
                sh """
                    docker stop $CONTAINER_NAME || true
                    docker rm $CONTAINER_NAME || true
                """
            }
        }

        stage('Run New Container') {
            steps {
                echo "Running new Docker container..."
                sh "docker run -d --name $CONTAINER_NAME -p 80:8080 $IMAGE_NAME"
            }
        }
    }
}
