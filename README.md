# TestAssignment

This test assignment is split into 2 tests. JUnit is used to run them.

## Command to run tests
`mvn clean test` - will run both tests locally in Google Chrome.

## Different configurations to run tests

### Locally
`mvn clean test -Dbrowser=firefox` - will run tests in Firefox.

### Remote (potentially)
```
docker-compose up
mvn clean test -Dmode=remote
```

- will pull docker images for selenium hub and chrome nodes (make sure you have docker installed)
- will run tests in docker container

## Run single test
`mvn -Dtest=GoogleTest#testGoogleSearch test`
