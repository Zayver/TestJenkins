pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            some-label: some-label-value
        spec:
          containers:
          - name: gradle
            image: gradle:latest
            command:
            - cat
            tty: true
          - name: busybox
            image: busybox
            command:
            - cat
            tty: true
        '''
      retries 2
    }

  }
  stages {
    stage('Code checkout') {
      steps {
        git(url: 'https://github.com/Zayver/TestJenkins', branch: 'master')
      }
    }

    stage('Run tests') {
      steps {
        container(name: 'gradle') {
          sh 'gradle test'
        }
      }
    }
    stage('Build docker image'){
      steps{
        sh 'docker build . -t zayver/testjenkins'
      }
    }

    stage('Deploy to kubernetes'){
      script{
        kubernetesDeploy(configs: yaml '''
          apiVersion: apps/v1
          kind: Deployment
          metadata:
            name: jkk
            labels:
              app: spring-jkk
          spec:
            replicas: 3 #The number of pods to be created in the Kubernetes cluster for the React.js application container
            selector:
              matchLabels:
                app: spring-jkk
            template:
              metadata:
                labels:
                  app: spring-jkk
              spec:
                containers:
                - name: spring-jkk #The name of the react.js application container
                  image: zayver/testjenkins:latest #The Docker image for building the React.js application container
                  imagePullPolicy: IfNotPresent
                  ports:
                  - containerPort: 8080 #The port for the React.js application   container
          '''
          ,
          yaml
          '''
          apiVersion: v1
          kind: Service
          metadata:
            name: jkk-service #The name of the Kubernetes Service to be created in the Kubernetes cluster
          spec:
            selector:
              app: spring-jkk
            type: LoadBalancer #Type of the Kubernetes Service
            ports:
            - protocol: TCP
              port: 3000 #Service port
              targetPort: 8080 #The port for the React.js application container
          '''
          )
      }
    }

  }
}
