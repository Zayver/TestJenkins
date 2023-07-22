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
          - name: docker
            image: docker:latest
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
        container(name:'docker'){
          sh 'docker build . -t zayver/jenkinstest'
        }
      }
    }

    stage('Deploy to kubernetes'){
      steps{
        script{
          kubernetesDeploy(configs: "deployment.yaml", "service.yaml")
       }
      }
    }

  }
}
