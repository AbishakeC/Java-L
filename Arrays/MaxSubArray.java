public class MaxSubArray {
    public  static int MaxSubArray(int [] nums){

        int max_sum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int num : nums){
            currentSum += num;

            if(currentSum > max_sum){
                max_sum = currentSum;
            }

            if (currentSum <0) {
              currentSum = 0;                
            }
        }
        return max_sum;
    }

    public  static int MinSubArray(int [] nums){
          
        int Min_Value = Integer.MAX_VALUE;
        int currentSum = 0;

        for (int num : nums){
            currentSum += num;

            if (currentSum < Min_Value) {
                Min_Value = currentSum;
            }

            if(currentSum > 0){
               currentSum = 0;
            }
        }
        return Min_Value;
    }

    public static void main(String[] args) {
        int [] nums = {1,-2,4,-1,3,5,-12,1,-2};
        System.out.println("max Subarray:"+ MaxSubArray(nums));
        System.out.println("Min Subarray :" + MinSubArray(nums));
    }
}
