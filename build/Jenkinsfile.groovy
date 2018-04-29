try {
    timeout(time: 20, unit: 'MINUTES') {
        node("maven") {
            stage("Checkout") {
                checkout scm
            }
            stage("Build") {
                sh "mvn clean verify"
            }
            stage("Build Image") {
//              build device registry TODO: change it
                sh "oc start-build shas-device-registry-docker --from-file=shas-device-registry/target/ShasDeviceRegistry-0.0.1-SNAPSHOT.jar -n shas-int"
            }
            stage("Deploy") {
                openshiftDeploy deploymentConfig: "shas-device-registry", namespace: "shas-int"
            }
        }
    }
} catch (err) {
    echo "in catch block"
    echo "Caught: ${err}"
    currentBuild.result = 'FAILURE'
    throw err
}