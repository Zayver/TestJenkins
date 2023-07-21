pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            some-label: test-pod
        spec:
          containers:
          - name: gradle
            image: gradle:latest
            command:
            - cat
            tty: true
        '''
      retries 2
    }
  }
  stages {
    stage('Checkout code') {
      steps {
        git(url: 'https://github.com/Zayver/TestJenkins', branch: 'master')
      }
    }

    stage('Run tests') {
      steps {
        sh 'gradle test'
      }
    }
  }
}
