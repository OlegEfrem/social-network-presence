# About
* This is Social Network Presence app implementation of the requirements described [here](TASK.md);

# Available endpoints
Rest API ToBeAdded;

# Highlights
## Libraries, Frameworks & Plugins
* Dependencies are defined [here](build.sbt) and
* plugins [here](/project/plugins.sbt);
* Testing layer uses: [scala test](http://www.scalatest.org/) for defining test cases;
* Plugins configured for the project are: [scala-style](http://www.scalastyle.org/) for code style checking,
[scalafmt](https://scalameta.org/scalafmt/) for code formatting and [sbt-updates](https://github.com/rtimush/sbt-updates) for keeping up the dependencies up to date;
* Note: [s-coverage](https://github.com/scoverage/sbt-scoverage) for code test coverage was removed as it is not compatible with scala 2.13.1;

# Implementation considerations
* main entry point (prior to adding rest api) into the code is: [NetworkPresenceService](/src/main/scala/com/hooyu/sonep/domain/service/NetworkPresenceService.scala);
*  same architecture principles were implemented here as in [movies_rest_service](https://github.com/OlegEfrem/movies_rest_service#architecturedesigncoding-principles)

# TODOs
* add rest api, example of REST APIs implemented are:
  *  akka-http: [bank-account](https://github.com/OlegEfrem/bank-account), [currency_converter](https://github.com/OlegEfrem/currency_converter), [movies_rest_service](https://github.com/OlegEfrem/movies_rest_service);
  *  play: [snakes-ladders-play](https://github.com/OlegEfrem/snakes-ladders-play);
* add BDD style [User-stories](https://github.com/OlegEfrem/social-network-presence/blob/master/TASK.md#user-stories) test for the rest-api;

# Run tests
 ```bash
 sbt dependencyUpdates scalafmtCheck clean test
 ```
