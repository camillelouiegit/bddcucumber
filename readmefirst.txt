=====================================================

Running scripts via mvn

mvn test -DsuiteXmlFile=XmlFileName.xml

e.g.

mvn test -DsuiteXmlFile=JoinParticipant.xml



=====================================================

after cloning:

git pull

=Then=

git checkout dev

=====================================================

###Pulling updates:

git add .
git stash

=verify if stashed successfully=

git pull origin dev
git stash apply

=CHECK IF THERE ARE CONFLICTS, else, fix the merge conflicts=

=====================================================

###Pulling updates after someone has pushed: 

git add .
git stash

=verify if stashed successfully=

git pull origin dev
git stash apply

=CHECK IF THERE ARE CONFLICTS, else, fix the merge conflicts=

=====================================================

###Pushing commits: 

git add .
git stash

=verify if stashed successfully=

git pull origin dev
git stash apply

=CHECK IF THERE ARE CONFLICTS, else, fix the merge conflicts=

git add .
git status

=verify if changes were staged successfully=

git commit -m "message/description/updates"
git push origin dev

=then, verify in github if pushed successfully=