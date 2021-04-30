package array;

public class RotateArray {

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 7};

        int[] res = rotateArray(arr, 4);
        for (int i : res) {
            System.out.print(i + ", ");
        }

    }

    public static int[] rotateArray(int[] arr, int k) {
        int[] rot = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int pos = (i + k) % arr.length;
            rot[pos] = arr[i];
        }

        return rot;
    }
}
