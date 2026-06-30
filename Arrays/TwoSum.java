import java.util.Arrays;
import java.util.HashMap;

class TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{};
    }

    public static void main(String[] args) {

        int[] nums = {2, 7, 5, 11};
        int target = 79;

        int[] ans = twoSum(nums, target);

        System.out.println(Arrays.toString(ans));
    }
}