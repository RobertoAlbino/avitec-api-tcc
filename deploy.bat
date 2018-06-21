git add --all
git commit -m "Deploy Controle Java Backend Heroku"
git push origin master
git push heroku master
gradlew build
heroku deploy:jar --jar build/libs/controle-0.0.1-SNAPSHOT.war

