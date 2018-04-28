try {
    timeout(time: 20, unit: 'MINUTES') {
        node("maven") {
//            stage("Checkout") {
//                git url: "${GIT_SOURCE_URL}", branch: "${GIT_SOURCE_REF}"
//            }
            stage("Build") {
                def userInput = input(
                        id: 'userInput', message: 'Let\'s promote?', parameters: [
                        [$class: 'TextParameterDefinition', defaultValue: 'uat', description: 'Environment', name: 'env'],
                        [$class: 'TextParameterDefinition', defaultValue: 'uat1', description: 'Target', name: 'target']
                ])
                echo ("Env: "+userInput['env'])
                echo ("Target: "+userInput['target'])
                sh "mvn -f ${env.WORKSPACE}/pom.xml clean verify"
            }
        }
    }
} catch (err) {
    echo "in catch block"
    echo "Caught: ${err}"
    currentBuild.result = 'FAILURE'
    throw err
}