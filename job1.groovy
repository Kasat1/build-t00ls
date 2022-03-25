def gitUrl = 'https://github.com/Kasat1/build-t00ls/tree/main'
    
job('MNTLAB-akasatau-main-build-job') {
  description 'This is Main job'

  parameters {
    gitParam('BRANCH_NAME') {
      type 'BRANCH'
      defaultValue 'origin/akasatau'
    }
    activeChoiceReactiveParam('Job_name') {
           description('Choice from multiple parameters')
           choiceType('CHECKBOX')
           groovyScript {
               script('return ["MNTLAB-akasatau-child1-build-job", "MNTLAB-akasatau-child2-build-job", "MNTLAB-akasatau-child3-build-job", "MNTLAB-akasatau-child4-build-job"]')
           }
    }
  }
  
  scm {
    git {
      remote {
          url {gitUrl}
      }
      branch '$BRANCH_NAME'
    }
  }
  steps {
       downstreamParameterized {
               trigger('$Job_name') {
                 block {
                    buildStepFailure('FAILURE')
                    failure('FAILURE')
                    unstable('UNSTABLE')
                 }
                 parameters {
                     currentBuild()
                 }
           }
       }
   }
}
def JOBS = ["MNTLAB-akasatau-child1-build-job", "MNTLAB-akasatau-child2-build-job", "MNTLAB-akasatau-child3-build-job", "MNTLAB-akasatau-child4-build-job"]
for(job in JOBS) {


mavenJob(job) {
  description 'Child jobs'
  parameters {
    gitParam('BRANCH_NAME') {
      description 'Jenkins Task6 child jobs'
      type 'BRANCH'
    }
    activeChoiceReactiveParam('Job_name') {
           description('Multiple choice')
           choiceType('CHECKBOX')
           groovyScript {
               script('return ["MNTLAB-akasatau-child1-build-job", "MNTLAB-akasatau-child2-build-job", "MNTLAB-akasatau-child3-build-job", "MNTLAB-akasatau-child4-build-job"]')
           }
    }
  }  

  scm {
    git {
      remote {
          url {gitUrl}
      }
      branch '$BRANCH_NAME'
    }
  }

  triggers {
    scm 'H/60 * * * *'
  }

  rootPOM 'home-task/pom.xml'
  goals 'clean install'
  postBuildSteps {
        shell('java -cp home-task/target/BuildArtifact-1.0.jar com.test.Project >> home-task/target/output.log')
        shell('tar -cvf "$(echo $BRANCH_NAME | cut -d "/" -f 2)_dsl_script.tar.gz" home-task/target/*.jar home-task/target/output.log')
    }
  
  publishers {
        archiveArtifacts('*.tar.gz')
    }
}
}
