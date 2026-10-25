import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Small Tweet model sufficient for Lab 07. */
public final class Tweet {
    private static final Pattern MENTION = Pattern.compile("@([A-Za-z0-9_]+)");
    private final String author;
    private final String text;

    public Tweet(String author, String text) {
        if (author == null || text == null) {
            throw new IllegalArgumentException("author and text must not be null");
        }
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    /** Extracts usernames following @ signs. Username case is preserved. */
    public Set<String> getMentionedUsers() {
        Set<String> users = new HashSet<>();
        Matcher matcher = MENTION.matcher(text);
        while (matcher.find()) {
            users.add(matcher.group(1));
        }
        return Collections.unmodifiableSet(users);
    }

    @Override
    public String toString() {
        return "@" + author + ": " + text;
    }
}
