node{
    def DOCKER_IMAGE_VERSION = ""

    stage("clean workspace") {
        deleteDir()
    }

    stage("git checkout") {
        checkout scm

        def GIT_COMMIT = sh(returnStdout: true, script: "git rev-parse HEAD").trim().take(7)
        DOCKER_IMAGE_VERSION = "${BUILD_NUMBER}-${GIT_COMMIT}"
    }
   stage('Run Container on Server'){
   try{
    sh "docker-compose down"
    sh "docker system prune"
    sh "docker-compose up --build -d"
   }
    catch (e) {
               error "Service update failed"
           }
      }
}