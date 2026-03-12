import java.util.*;

class LRUCache extends LinkedHashMap<String,String>{

    int capacity;

    public LRUCache(int capacity){
        super(capacity,0.75f,true);
        this.capacity=capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<String,String> e){
        return size()>capacity;
    }
}

public class Problem10 {

    static LRUCache L1=new LRUCache(3);
    static HashMap<String,String> L2=new HashMap<>();
    static HashMap<String,String> DB=new HashMap<>();

    static String getVideo(String id){

        if(L1.containsKey(id)){
            System.out.println("L1 HIT");
            return L1.get(id);
        }

        if(L2.containsKey(id)){
            System.out.println("L2 HIT → promoted to L1");
            String data=L2.get(id);
            L1.put(id,data);
            return data;
        }

        System.out.println("L3 Database HIT");

        String data=DB.get(id);

        if(data!=null){
            L2.put(id,data);
        }

        return data;
    }

    public static void main(String[] args) {

        DB.put("video1","Movie A");
        DB.put("video2","Movie B");

        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video1"));
    }
}

