# SE-314 Lab 07: Test-First Programming II

## Project
A compact Java solution to the social-network inference tasks from Lab 07.

## Files
- `SocialNetwork.java`: constructs a graph linking each author to the users they mention, and ranks users by how many followers they have.
- `Tweet.java`: a lightweight tweet model that also extracts @mentions.
- `SocialNetworkTest.java`: a suite of ten JUnit 4 tests.
- `Main.java`: a demo program.
- `pom.xml`: the Maven build configuration.

## Running in IntelliJ IDEA
1. Open the `Lab07_SocialNetwork` folder as a project.
2. Let Maven download the dependencies (JUnit 4.13.2 is already declared in `pom.xml`).
3. Open `SocialNetworkTest.java` and press the green run icon next to the class or an individual test method.
4. Run `Main.java` to see an example graph.
5. From a terminal with Maven installed, use `mvn test` to run the tests and `mvn -q exec:java` to run the demo. The latter needs the Maven exec plugin added to the build; otherwise, just run `Main` from IntelliJ.

## Note on the specification
The spec's graph postcondition leaves the exact structure open. For that reason, the tests verify only the required evidence (that an author has mentioned a given user) rather than demanding one particular graph layout. Likewise, the order of influencers with tied follower counts is deliberately left unspecified.
