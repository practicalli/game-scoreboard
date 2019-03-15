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

## License

Copyright ©  FIXME
