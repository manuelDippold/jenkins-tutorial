job('NodeJS Docker example') {
    scm {
        git('https://github.com/manuelDippold/jenkins-tutorial.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('manuel_dippold@web.de')
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
            repositoryName('mdippold/docker-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('fa63d66c-731b-467d-a5a0-bab36ec3db17')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
