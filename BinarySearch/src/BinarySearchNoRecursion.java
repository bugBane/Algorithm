public class BinarySearchNoRecursion {
    public static void binarySearchNoRecursion(int[] arr, int value) {
        if (value >= arr[arr.length - 1]) {
            System.out.println("下标为：" + ((arr.length - 1)));
            return;
        }
        if (value <= arr[0]) {
            System.out.println("下标为：0");
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == value) {
                System.out.println("下标为：" + mid);
                return;
            } else if (arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        binarySearchNoRecursion(arr, 1);
    }
}
