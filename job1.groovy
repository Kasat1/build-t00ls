def gitUrl = 'https://github.com/Kasat1/build-t00ls/tree/main'

job('MNTLAB-akasatau-main-build-job') {
    description 'This is main-build-job'
    parameters {
    	stringParam('BRANCH_NAME')
        activeChoiceParam('JOB_TO_EXEC'){
        	choiceType('CHECKBOX')
            groovyScript{
            	script('["MNTLAB-akasatau-child1-build-job","MNTLAB-akasatau-child2-build-job","MNTLAB-akasatau-child3-build-job","MNTLAB-akasatau-child4-build-job"]')
            fallbackScript()
            }
        }
    }
}

job("MNTLAB-akasatau-child1-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
        choiceType('SINGLE_SELECT')
    }
  }
    scm {
    git { gitUrl 
         branch '$BRANCH_NAME'
        }
    steps{
    	maven('clean install')
    }  
  steps {
    shell('''
cd home-task/target/
tar cvf "$BRANCH_NAME_dsl_script.tar.gz" *.jar
''')
  }  
}

job("MNTLAB-TEST"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
      description('Branch name')
        choiceType('SINGLE_SELECT')
        groovyScript {
        script('''
def command = "git ls-remote -h $gitURL"
def proc = command.execute()
proc.waitFor()
if ( proc.exitValue() != 0 ) {
  println "Error, ${proc.err.text}"
  System.exit(-1)
}
def branches = proc.in.text.readLines()
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
}
