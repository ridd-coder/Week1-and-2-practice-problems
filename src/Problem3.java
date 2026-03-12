import java.util.*;

class DNSEntry {
    String ipAddress;
    long expiryTime;

    public DNSEntry(String ipAddress, int ttlSeconds) {
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

class DNSCache {

    private HashMap<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;
    private int maxSize = 100;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                System.out.println("Cache HIT");
                return entry.ipAddress;
            } else {
                System.out.println("Cache EXPIRED");
                cache.remove(domain);
            }
        }

        misses++;
        System.out.println("Cache MISS → Query upstream");

        String newIP = queryUpstreamDNS(domain);
        put(domain, newIP, 5); // TTL = 5 seconds (example)

        return newIP;
    }

    private void put(String domain, String ip, int ttl) {

        if (cache.size() >= maxSize) {
            String firstKey = cache.keySet().iterator().next();
            cache.remove(firstKey); // simple eviction
        }

        cache.put(domain, new DNSEntry(ip, ttl));
    }

    private String queryUpstreamDNS(String domain) {
        Random rand = new Random();
        return "172.217.14." + rand.nextInt(255);
    }

    public void getCacheStats() {
        int total = hits + misses;
        double hitRate = (total == 0) ? 0 : (hits * 100.0 / total);

        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }
}

public class Problem3 {

    public static void main(String[] args) throws Exception {

        DNSCache dns = new DNSCache();

        System.out.println("IP: " + dns.resolve("google.com"));
        System.out.println("IP: " + dns.resolve("google.com"));

        Thread.sleep(6000); // wait for TTL expiry

        System.out.println("IP: " + dns.resolve("google.com"));

        dns.getCacheStats();
    }
}

