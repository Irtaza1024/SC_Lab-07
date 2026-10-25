# SE-314 Lab 07 — Test-First Programming II

## Project
A small Java implementation of the social-network inference tasks in Lab 07.

## Files
- `SocialNetwork.java`: builds the author-to-mentioned-users graph and ranks users by follower count.
- `Tweet.java`: minimal tweet model and @mention extraction.
- `SocialNetworkTest.java`: ten JUnit 4 test cases.
- `Main.java`: sample program.
- `pom.xml`: Maven project configuration.

## Run in IntelliJ IDEA
1. Open the `Lab07_SocialNetwork` folder as a project.
2. Allow Maven to load dependencies; JUnit 4.13.2 is declared in `pom.xml`.
3. Open `SocialNetworkTest.java` and click the green run icon beside the class or test methods.
4. Run `Main.java` to view a sample graph.
5. If using Terminal with Maven installed: `mvn test` and `mvn -q exec:java` (the latter requires adding the Maven exec plugin, or simply run Main in IntelliJ).

## Important spec note
The graph postcondition is underdetermined: tests check the required evidence (author mentions a user) rather than asserting an unnecessarily exact graph structure. Tied influencer order is intentionally not fixed.
