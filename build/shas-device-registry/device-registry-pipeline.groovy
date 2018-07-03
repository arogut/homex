try {
    timeout(time: 20, unit: 'MINUTES') {
        node("maven") {
            stage("Checkout") {
                checkout scm
            }
            stage("Build") {
                def appVersion = version()
                sh "mvn clean verify"
                sh "cp shas-device-registry/target/ShasDeviceRegistry-${appVersion}-exec.jar build/shas-device-registry/app.jar"
            }
            stage("Build Image") {
                sh "oc start-build shas-device-registry-docker --from-dir=build/shas-device-registry -n shas --follow"
            }
        }
    }
} catch (err) {
    echo "in catch block"
    echo "Caught: ${err}"
    currentBuild.result = 'FAILURE'
    throw err
}

def version() {
    def matcher = readFile('pom.xml') =~ '<version>(.+?)</version>'
    matcher ? matcher[0][1] : null
}