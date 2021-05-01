package array;

public class RemoveDuplicateArray {

    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        var length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i] == nums[i - 1]) {
                removeItem(i, nums);
                length--;
                i--;
            }
        }

        return  length;
    }

    private static void removeItem(final int n, final int[] nums) {
        var length = nums.length;
        // Shift all numbers one place left.
        for (int i = n + 1; i < length; i++) {
            nums[i - 1] = nums[i];
        }
    }

    public static int removeDuplicates(int arr[], int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        // To store index of next unique element
        int j = 0;

        // Doing same as done in Method 1
        // Just maintaining another updated index i.e. j
        for (int i = 0; i < n-1; i++)
            if (arr[i] != arr[i+1])
                arr[j++] = arr[i];

        arr[j++] = arr[n-1];

        return j;
    }

    public static void main (final String[] args) {
        int arr[] = {1, 2, 2, 3, 4, 4, 4, 5, 5};
        int n = arr.length;

        n = removeDuplicates2(arr);

        // Print updated array
        for (int i = 0; i < n; i++)
            System.out.print(arr[i]+" ");
    }
}
