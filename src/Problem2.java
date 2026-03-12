import java.util.*;

class FlashSaleInventory {

    // productId -> stock count
    private HashMap<String, Integer> stock = new HashMap<>();

    // productId -> waiting list
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public FlashSaleInventory() {
        stock.put("IPHONE15_256GB", 100);
        waitingList.put("IPHONE15_256GB", new LinkedList<>());
    }

    // check stock
    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    // purchase item
    public synchronized String purchaseItem(String productId, int userId) {

        int currentStock = stock.getOrDefault(productId, 0);

        if (currentStock > 0) {
            stock.put(productId, currentStock - 1);
            return "Success, " + (currentStock - 1) + " units remaining";
        } else {
            Queue<Integer> queue = waitingList.get(productId);
            queue.add(userId);
            return "Added to waiting list, position #" + queue.size();
        }
    }
}

public class Problem2 {

    public static void main(String[] args) {

        FlashSaleInventory manager = new FlashSaleInventory();

        System.out.println("Stock: " + manager.checkStock("IPHONE15_256GB") + " units available");

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));

        // simulate stock sold out
        for(int i = 0; i < 100; i++){
            manager.purchaseItem("IPHONE15_256GB", i);
        }

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 99999));
    }
}

