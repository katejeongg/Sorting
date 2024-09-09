import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author KATE JEONG
 * @version 1.0
 * @userid kjeong40
 * @GTID 903886263
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class Sorting {

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is
     *                                            null
     */
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

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is
     *                                            null
     */
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

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for bubble sort. You
     * MUST implement bubble sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is
     *                                            null
     */
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

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is
     *                                            null
     */
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

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws IllegalArgumentException if the array is null
     */
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

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws IllegalArgumentException if the data is null
     */
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