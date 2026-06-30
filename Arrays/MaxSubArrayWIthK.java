public class MaxSubArrayWithK {

    public static int MaxSubArrayWithK(int[] arr, int K) {

        int windowSum = 0;

        for (int i = 0; i < K; i++) {
            windowSum += arr[i];
        }

        int max_Sum = windowSum;

        for (int i = K; i < arr.length; i++) {

            windowSum += arr[i];
            windowSum -= arr[i - K];

            if (windowSum > max_Sum) {
                max_Sum = windowSum;
            }
        }

        return max_Sum;
    }

    public static void main(String[] args) {

        int[] arr = {2, 3, 4, 5, 6, 7, 8};
        int K = 3;

        System.out.println("Max Subarray Sum: " + MaxSubArrayWithK(arr, K));
    }
}