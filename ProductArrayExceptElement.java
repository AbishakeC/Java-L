
import java.util.Arrays;

class ProductArrayExceptElement{
    public static int[] ProductExcept(int [] nums){

        int n = nums.length;
        int[] result = new int[n];

        result[0]=1;

        //prefix array
        for (int i = 1; i < n; i++) {
            result[i] = result[i-1] * nums[i-1];    
        }

        int suffix = 1;

        for (int i = n-1;i >=0; i--){
            
            result[i] *=suffix;
            suffix *= nums[i];
        }


        return result;

    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(ProductExcept(nums)));
    }
}