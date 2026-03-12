import java.util.*;

public class Problem9 {

    static void findTwoSum(int[] nums,int target){

        HashMap<Integer,Integer> map=new HashMap<>();

        for(int i=0;i<nums.length;i++){

            int complement=target-nums[i];

            if(map.containsKey(complement)){
                System.out.println("Pair found: "
                        +complement+" + "+nums[i]);
            }

            map.put(nums[i],i);
        }
    }

    public static void main(String[] args) {

        int[] transactions={500,300,200,700};

        findTwoSum(transactions,500);
    }
}

