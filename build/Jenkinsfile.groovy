try {
    timeout(time: 20, unit: 'MINUTES') {
        node("maven") {
            stage("Checkout") {
                git url: "${SOURCE_REPOSITROY_URL}", branch: "${SOURCE_REPOSITROY_REF}"
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