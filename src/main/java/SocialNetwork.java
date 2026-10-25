import java.util.*;

/**
 * Infers a simple social network from tweets.
 * An edge author -> mentioned user is evidence that the author follows that user.
 */
public final class SocialNetwork {
    private SocialNetwork() { }

    /**
     * Returns a graph mapping each author who mentions at least one user to the
     * set of users mentioned by that author. Tweets with no mentions add no entry.
     *
     * @param tweets tweets to analyze
     * @return author-to-mentioned-users graph
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (Tweet tweet : tweets) {
            Set<String> mentioned = tweet.getMentionedUsers();
            if (!mentioned.isEmpty()) {
                graph.computeIfAbsent(tweet.getAuthor(), key -> new HashSet<>())
                     .addAll(mentioned);
            }
        }
        return graph;
    }

    /**
     * Returns users with at least one follower, ordered by descending follower count.
     * Users tied on follower count may appear in either order.
     *
     * @param followsGraph map from author to users that author follows
     * @return users ordered from most followers to fewest; zero-follower users omitted
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> followerCounts = new HashMap<>();
        for (Set<String> followedUsers : followsGraph.values()) {
            for (String followed : followedUsers) {
                followerCounts.merge(followed, 1, Integer::sum);
            }
        }
        List<String> result = new ArrayList<>(followerCounts.keySet());
        result.sort((a, b) -> Integer.compare(followerCounts.get(b), followerCounts.get(a)));
        return result;
    }
}
