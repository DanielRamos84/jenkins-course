job('NodeJS Docker example') {
    scm {
        git('https://github.com/DanielRamos84/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DRamos')
            node / gitConfigEmail('dramos.qa+jenkins-dsl@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('dramos84/docker-node-js')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
