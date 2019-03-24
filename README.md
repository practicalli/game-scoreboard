# game-scoreboard

A Games Scoreboard API to track scores for one or more games and provide all data around scores, to be used for game leaderboards, etc.

Basic features
- all scores for a game
- top x scores
- bottom x scores
- create a new game scoreboard
- add a score to a game scoreboard


## Usage

### Run the application locally

`lein ring server`

### Run the tests

`lein test`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`



## Deploy to heroku

```
git push heroku master
```

### Running the uberjar as a java app

Add a web directive to run a suitable java command line to launch the application via the uberjar that Heroku builds from source.

```
web: java $JVM_OPTS -cp target/server.jar clojure.main -m game-scoreboard.handler $PORT
```

### Projects using lein-ring plugin
```
web: lein ring server-headless $PORT
```

## License

Copyright ©  FIXME
