package search;

public class Linear {

    /**
     * @param arr Array to be searched
     * @param x   Value to be searched for
     * @return index of target or -1 if not found
     */
    public static int sequentialSearch(int arr[], int x) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }

    public static int jumpSearch(int[] arr, int x) {
        int l = arr.length;
        int skip = (int) Math.floor(Math.sqrt(l));

        for (int i = 0; i < l; i += skip) {
            if (x <= arr[i])
                for (int j = i; j >= Math.max(i - skip, 0); j--) {
                    if (arr[j] == x)
                        return j;
                }

        }

        return -1;
    }

    int binarySearch(int arr[], int x) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] < x)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sort = { 1, 5, 9, 13, 15, 18, 27, 29, 35, 38, 42, 46, 48, 52, 56, 58, 62 };
        int search = 13;
        System.out.println(sequentialSearch(sort, search));
        System.out.println(jumpSearch(sort, search));
    }
}