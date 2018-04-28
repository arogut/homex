try {
    timeout(time: 20, unit: 'MINUTES') {
        node("maven") {
//            stage("Checkout") {
//                git url: "${GIT_SOURCE_URL}", branch: "${GIT_SOURCE_REF}"
//            }
            stage("Build") {
                sh "mvn -f ../pom.xml clean verify"
            }
        }
    }
} catch (err) {
    echo "in catch block"
    echo "Caught: ${err}"
    currentBuild.result = 'FAILURE'
    throw err
}