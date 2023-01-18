# javarush-telegrambot
JavaRush Telegram bot. Project written on the basis of [the course](https://javarush.ru/groups/posts/2935-java-proekt-ot-a-do-ja-pishem-realjhnihy-proekt-dlja-portfolio) 

# How it works 
Based on MVP Scope, we can specify next behaviours (here and after Telegram User, which is using JavaRush Telgegram bot will call User):
- User can subscribe on group of posts
- User can view list of group subscriptions on which user subscribes
- User can unsubscribe from group of posts
- User can set an inactive bot and do not receive notifications
- User can restart getting notifications
## Find new posts workflow
The workflow of finding new posts and send them to subscribers can be viewed here:
![Find_New_Posts_WF](https://user-images.githubusercontent.com/16310793/119827993-6c22ec80-bf02-11eb-8759-83bea483db93.png)

## Deployment
Deployment process as easy as possible:
Required software:
- terminal for running bash scripts
- docker
- docker-compose

to deploy application, switch to needed branch and run bash script:

$ bash start.sh ${bot_username} ${bot_token}

# Local development
For local development and testing, use `docker-compose-test.yml`. 
Run command: 
```shell
docker-compose -f docker-compose-test.yml up
```
Next step, is to run SpringBoot app with configured **Edit Configuration** in which two env vars are provided: 

`bot.token=${BOT_TOKEN};bot.username=${BOT_USERNAME}`

And add VM Options: 

`-Dspring.profiles.active=test `

 With these configurations - run SpringBoot main method.

# Technological stack
- SpringBoot as a skeleton framework
- Spring Scheduler as a task manager
- MySQL database as a database for saving user and subscription info
- Flyway database migration tool
- Telegram-bot SpringBoot starter
- Spring Data starter
- Unirest - lib for working with REST calls
