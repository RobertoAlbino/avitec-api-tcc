git add --all
git commit -m "Deploy Cota Easy Heroku"
git push origin master
git push heroku master
gradlew build
heroku deploy:jar --jar build/libs/cotaeasy-0.0.1-SNAPSHOT.war

