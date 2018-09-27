git add --all
git commit -m "Deploying avitec"
git push avi master
git push heroku master
heroku deploy:jar --jar build/libs/avitec-0.0.1-SNAPSHOT.war --app avitec-api


