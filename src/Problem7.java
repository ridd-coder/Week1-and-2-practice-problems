import java.util.*;

public class Problem7 {

    static HashMap<String,Integer> frequency = new HashMap<>();

    static void updateFrequency(String query) {
        frequency.put(query, frequency.getOrDefault(query,0)+1);
    }

    static List<String> search(String prefix) {

        PriorityQueue<Map.Entry<String,Integer>> pq =
                new PriorityQueue<>((a,b)->b.getValue()-a.getValue());

        for(String q:frequency.keySet()){
            if(q.startsWith(prefix)){
                pq.add(Map.entry(q,frequency.get(q)));
            }
        }

        List<String> result = new ArrayList<>();

        for(int i=0;i<10 && !pq.isEmpty();i++){
            result.add(pq.poll().getKey());
        }

        return result;
    }

    public static void main(String[] args) {

        updateFrequency("java tutorial");
        updateFrequency("javascript");
        updateFrequency("java download");
        updateFrequency("java tutorial");

        System.out.println(search("jav"));
    }
}

