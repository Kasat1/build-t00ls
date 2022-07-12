job("day6/MNTLAB-akasatau-main-build-job"){ 
  parameters {
    stringParam('BRANCH_NAME', 'jenkins-dsl', 'Branche name')
    activeChoiceParam('CHILD_JOBS_NAMES') {
      description('User can choose jobs for execution')
        choiceType('CHECKBOX')
        groovyScript {
        script('["MNTLAB-akasatau-child1-build-job", "MNTLAB-akasatau-child2-build-job", "MNTLAB-akasatau-child3-build-job", "MNTLAB-akasatau-child4-build-job"]')
        fallbackScript()
      }
    }
  }  
//   concurrentBuild()
  steps {
    triggerBuilder {
      configs {
        blockableBuildTriggerConfig {
          projects('$CHILD_JOBS_NAMES')
          block {
            buildStepFailureThreshold('FAILURE')
            unstableThreshold('UNSTABLE')
            failureThreshold('FAILURE')
          }
          configs {
            predefinedBuildParameters {
              properties('BRANCH_NAME=$BRANCH_NAME')
              textParamValueOnNewLine(false)
            }
          } 
        }
      }
    } 
  }
}

job("day6/MNTLAB-akasatau-child1-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
      description('Branch name')
        choiceType('SINGLE_SELECT')
        groovyScript {
        script('''def gitURL = "https://github.com/ovsyankinaa/jenkins-dsl.git"
def command = "git ls-remote -h $gitURL"
def proc = command.execute()
proc.waitFor()
if ( proc.exitValue() != 0 ) {
  println "Error, ${proc.err.text}"
  System.exit(-1)
}
def branches = proc.in.text.readLines().collect {
  it.replaceAll(/[a-z0-9]*\\trefs\\/heads\\//, '')
}
return branches
''')
        fallbackScript()
      }
    }
  }  
  steps {
    shell('sleep 20')
  }  
}

job("day6/MNTLAB-akasatau-child2-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
      description('Branch name')
        choiceType('SINGLE_SELECT')
        groovyScript {
        script('''def gitURL = "https://github.com/ovsyankinaa/jenkins-dsl.git"
def command = "git ls-remote -h $gitURL"
def proc = command.execute()
proc.waitFor()
if ( proc.exitValue() != 0 ) {
  println "Error, ${proc.err.text}"
  System.exit(-1)
}
def branches = proc.in.text.readLines().collect {
  it.replaceAll(/[a-z0-9]*\\trefs\\/heads\\//, '')
}
return branches
''')
        fallbackScript()
      }
    }
  }  
  steps {
    shell('sleep 20')
  }  
}
  
job("day6/MNTLAB-akasatau-child3-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
      description('Branch name')
        choiceType('SINGLE_SELECT')
        groovyScript {
        script('''def gitURL = "https://github.com/ovsyankinaa/jenkins-dsl.git"
def command = "git ls-remote -h $gitURL"
def proc = command.execute()
proc.waitFor()
if ( proc.exitValue() != 0 ) {
  println "Error, ${proc.err.text}"
  System.exit(-1)
}
def branches = proc.in.text.readLines().collect {
  it.replaceAll(/[a-z0-9]*\\trefs\\/heads\\//, '')
}
return branches
''')
        fallbackScript()
      }
    }
  }  
  steps {
    shell('sleep 20')
  }  
}

job("day6/MNTLAB-akasatau-child4-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
      description('Branch name')
        choiceType('SINGLE_SELECT')
        groovyScript {
        script('''def gitURL = "https://github.com/ovsyankinaa/jenkins-dsl.git"
def command = "git ls-remote -h $gitURL"
def proc = command.execute()
proc.waitFor()
if ( proc.exitValue() != 0 ) {
  println "Error, ${proc.err.text}"
  System.exit(-1)
}
def branches = proc.in.text.readLines().collect {
  it.replaceAll(/[a-z0-9]*\\trefs\\/heads\\//, '')
}
return branches
''')
        fallbackScript()
      }
    }
  }  
  steps {
    shell('sleep 20')
  }  
}
