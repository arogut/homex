try {
    timeout(time: 20, unit: 'MINUTES') {
        node("maven") {
            stage("Checkout") {
                git url: "${SOURCE_REPOSITORY_URL}", branch: "${SOURCE_REPOSITORY_REF}"
            }
            stage("Build") {
                sh "mvn clean verify"
            }
        }
    }
} catch (err) {
    echo "in catch block"
    echo "Caught: ${err}"
    currentBuild.result = 'FAILURE'
    throw err
}