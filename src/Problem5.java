import java.util.*;

class AnalyticsSystem {

    // page -> visit count
    private HashMap<String, Integer> pageViews = new HashMap<>();

    // page -> unique users
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();

    // traffic source -> count
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    // process incoming event
    public void processEvent(String url, String userId, String source) {

        // update page views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // update unique visitors
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        // update traffic source
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    // show dashboard
    public void getDashboard() {

        System.out.println("Top Pages:");

        PriorityQueue<Map.Entry<String,Integer>> pq =
                new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        int rank = 1;
        while (!pq.isEmpty() && rank <= 10) {

            Map.Entry<String,Integer> entry = pq.poll();
            String url = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(url).size();

            System.out.println(rank + ". " + url +
                    " - " + views + " views (" + unique + " unique)");

            rank++;
        }

        System.out.println("\nTraffic Sources:");

        int total = 0;
        for (int c : trafficSources.values())
            total += c;

        for (String source : trafficSources.keySet()) {

            int count = trafficSources.get(source);
            double percent = (count * 100.0) / total;

            System.out.println(source + ": " +
                    String.format("%.1f", percent) + "%");
        }
    }
}

public class Problem5 {

    public static void main(String[] args) {

        AnalyticsSystem system = new AnalyticsSystem();

        system.processEvent("/article/breaking-news","user_123","Google");
        system.processEvent("/article/breaking-news","user_456","Facebook");
        system.processEvent("/sports/championship","user_789","Direct");
        system.processEvent("/sports/championship","user_111","Google");
        system.processEvent("/article/breaking-news","user_222","Google");

        system.getDashboard();
    }
}

