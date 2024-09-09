import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Sorting {

    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array or comparator is null.");
        }
        if (arr.length <= 1) {
            return;
        }
        int length = arr.length;
        T data = null;
        // for i from end to start
        for (int i = length - 1; i > 0; i--) {
            int max = i;
            // for j from start to i
            for (int j = i - 1; j >= 0; j--) {
                if (comparator.compare(arr[j], arr[max]) > 0) {
                    max = j;
                }
            }
            // swap arr[max] and arr[i]
            data = arr[max];
            arr[max] = arr[i];
            arr[i] = data;
        }
    }

    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array or comparator is null.");
        }
        if (arr.length <= 1) {
            return;
        }
        int length = arr.length;
        T data = null;
        for (int i = 0; i < length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                // bubble arr[j] by swapping down
                data = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = data;
                j--;
            }
        }
    }

    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array or comparator is null.");
        }
        if (arr.length <= 1) {
            return;
        }
        int end = arr.length - 1;
        int lastSwap = -1;
        T data = null;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int j = 0; j < end; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    // bubble arr[j] by swapping up
                    swapped = true;
                    data = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = data;
                    lastSwap = j;
                }
            }
            end = lastSwap;
        }
    }

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array or comparator is null.");
        }
        // edge cases
        if (arr.length <= 1) {
            return;
        }
        int length = arr.length;
        int midIdx = length / 2;
        T[] leftArr = (T[]) new Object[midIdx];
        // not always the same length
        T[] rightArr = (T[]) new Object[arr.length - leftArr.length];
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[i];
        }
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[i + leftArr.length];
        }
        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);
        int leftIdx = 0;
        int rightIdx = 0;
        int currIdx = 0;
        while (leftIdx < leftArr.length && rightIdx < rightArr.length) {
            if (comparator.compare(leftArr[leftIdx], rightArr[rightIdx]) <= 0) {
                arr[currIdx] = leftArr[leftIdx];
                leftIdx++;
            } else {
                arr[currIdx] = rightArr[rightIdx];
                rightIdx++;
            }
            currIdx++;
        }
        while (leftIdx < leftArr.length) {
            arr[currIdx] = leftArr[leftIdx];
            currIdx++;
            leftIdx++;
        }
        while (rightIdx < rightArr.length) {
            arr[currIdx] = rightArr[rightIdx];
            currIdx++;
            rightIdx++;
        }
    }

    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null.");
        }
        if (arr.length <= 1) {
            return;
        }
        // array w 19 lists
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        // finding max num
        int max = Math.abs(arr[0]);
        for (int num : arr) {
            if (num == Integer.MIN_VALUE) {
                max = Integer.MAX_VALUE;
            }
            if (Math.abs(num) > max) {
                max = Math.abs(num);
            }
        }
        // calculate k (num of digits in largest num)
        int k = 0;
        while (max > 0) {
            max /= 10;
            k++;
        }
        int length = arr.length;
        // ones, tens, hundreds, etc.
        int base = 1;
        // sort into buckets
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < length; j++) {
                int b = (arr[j] / base) % 10 + 9;
                buckets[b].add(arr[j]);
            }
            // buckets -> array
            int idx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[idx] = bucket.remove(0);
                    idx++;
                }
            }
            base *= 10;
        }
    }

    public static int[] heapSort(List<Integer> data) {
        // create heap from array w buildheap (automatic bc priorityqueue)
        // for i = 0 to arr.length, pop from heap into array at i
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(data);
        int[] arr = new int[data.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.remove();
        }
        return arr;
    }
}
