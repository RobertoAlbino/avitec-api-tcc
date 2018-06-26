git add --all
git commit -m "Deploy Cota Easy Heroku"
git push origin master
git push heroku master
heroku deploy:jar --jar build/libs/cota-easy-0.0.1-SNAPSHOT.war

