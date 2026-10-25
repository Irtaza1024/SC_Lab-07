import java.util.*;
public class VerifyLab {
  private static int passed = 0;
  private static void check(String name, Runnable test) {
    try { test.run(); passed++; System.out.println("PASS: " + name); }
    catch (Throwable t) { System.out.println("FAIL: " + name + " -> " + t); throw new RuntimeException(t); }
  }
  public static void main(String[] args) {
    check("1 Empty tweet list", () -> { if (!SocialNetwork.guessFollowsGraph(Collections.emptyList()).isEmpty()) throw new AssertionError(); });
    check("2 Tweets without mentions", () -> { if (!SocialNetwork.guessFollowsGraph(Arrays.asList(new Tweet("A","hello"),new Tweet("B","world"))).isEmpty()) throw new AssertionError(); });
    check("3 Single mention", () -> { if (!SocialNetwork.guessFollowsGraph(Collections.singletonList(new Tweet("Ernie","@Bert"))).get("Ernie").contains("Bert")) throw new AssertionError(); });
    check("4 Multiple mentions", () -> { if (!SocialNetwork.guessFollowsGraph(Collections.singletonList(new Tweet("A","@B @C"))).get("A").containsAll(Arrays.asList("B","C"))) throw new AssertionError(); });
    check("5 Repeated mentions across tweets", () -> { if (SocialNetwork.guessFollowsGraph(Arrays.asList(new Tweet("A","@B"),new Tweet("A","@B @C"))).get("A").size()!=2) throw new AssertionError(); });
    check("6 Empty graph influencers", () -> { if (!SocialNetwork.influencers(Collections.emptyMap()).isEmpty()) throw new AssertionError(); });
    check("7 User with no followers", () -> { Map<String,Set<String>> m=new HashMap<>(); m.put("A",Collections.emptySet()); if (!SocialNetwork.influencers(m).isEmpty()) throw new AssertionError(); });
    check("8 Single influencer", () -> { Map<String,Set<String>> m=new HashMap<>(); m.put("A",Collections.singleton("B")); if (!SocialNetwork.influencers(m).equals(Collections.singletonList("B"))) throw new AssertionError(); });
    check("9 Multiple influencers ordered", () -> { Map<String,Set<String>> m=new HashMap<>(); m.put("A",new HashSet<>(Arrays.asList("B","C"))); m.put("D",new HashSet<>(Arrays.asList("B","C"))); m.put("E",Collections.singleton("B")); List<String> r=SocialNetwork.influencers(m); if (!r.get(0).equals("B") || r.size()!=2 || !r.get(1).equals("C")) throw new AssertionError(r); });
    check("10 Tied influencer ordering", () -> { Map<String,Set<String>> m=new HashMap<>(); m.put("A",new HashSet<>(Arrays.asList("B","C"))); List<String> r=SocialNetwork.influencers(m); if (r.size()!=2 || !r.containsAll(Arrays.asList("B","C"))) throw new AssertionError(r); });
    System.out.println("Result: " + passed + "/10 checks passed.");
    Main.main(new String[0]);
  }
}
