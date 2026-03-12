import java.util.*;

class TokenBucket {
    int maxTokens = 1000;
    double tokens = maxTokens;
    double refillRate = 1000.0 / 3600; // tokens per second
    long lastRefill = System.currentTimeMillis();

    synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        double seconds = (now - lastRefill) / 1000.0;

        tokens = Math.min(maxTokens, tokens + seconds * refillRate);
        lastRefill = now;

        if (tokens >= 1) {
            tokens--;
            return true;
        }
        return false;
    }
}

public class Problem6 {

    static HashMap<String, TokenBucket> clients = new HashMap<>();

    public static boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket());

        TokenBucket bucket = clients.get(clientId);

        if (bucket.allowRequest()) {
            System.out.println("Allowed request");
            return true;
        } else {
            System.out.println("Denied: Rate limit exceeded");
            return false;
        }
    }

    public static void main(String[] args) {
        checkRateLimit("abc123");
        checkRateLimit("abc123");
    }
}


