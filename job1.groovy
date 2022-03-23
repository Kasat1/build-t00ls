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

job("MNTLAB-akasatau-child2-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
        choiceType('SINGLE_SELECT')
    }
  }
    scm {
    git { gitUrl
        branch '$BRANCH_NAME'
      }
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

job("MNTLAB-akasatau-child3-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
        choiceType('SINGLE_SELECT')
    }
  }
    scm {
    git { gitUrl
         branch '$BRANCH_NAME'
      }
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

job("MNTLAB-akasatau-child4-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
        choiceType('SINGLE_SELECT')
    }
  }
    scm {
    git { gitUrl
                  branch '$BRANCH_NAME'
      }
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
}
