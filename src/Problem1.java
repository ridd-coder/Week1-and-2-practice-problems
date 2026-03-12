import java.util.*;

class UsernameChecker {

    // username -> userId
    private HashMap<String, Integer> users = new HashMap<>();

    // username -> attempt frequency
    private HashMap<String, Integer> attempts = new HashMap<>();

    // constructor with some existing users
    public UsernameChecker() {
        users.put("john_doe", 101);
        users.put("admin", 102);
        users.put("alex", 103);
    }

    // check availability
    public boolean checkAvailability(String username) {

        // track attempts
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        return !users.containsKey(username);
    }

    // suggest alternatives
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;
            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    // find most attempted username
    public String getMostAttempted() {

        String most = null;
        int max = 0;

        for (String name : attempts.keySet()) {
            if (attempts.get(name) > max) {
                max = attempts.get(name);
                most = name;
            }
        }

        return most + " (" + max + " attempts)";
    }
}

public class Problem1 {
    public static void main(String[] args) {

        UsernameChecker checker = new UsernameChecker();

        System.out.println("john_doe available? " + checker.checkAvailability("john_doe"));
        System.out.println("jane_smith available? " + checker.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe: " + checker.suggestAlternatives("john_doe"));

        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");

        System.out.println("Most attempted username: " + checker.getMostAttempted());
    }
}

