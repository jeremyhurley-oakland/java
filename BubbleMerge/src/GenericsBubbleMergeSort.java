import java.util.*;

public class GenericsBubbleMergeSort {
    
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for(int j = 0; j < n - i -1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    // swap temp and array[i]
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int middle = left + (right - left) / 2;

            // Recursive call for the first and second halves
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            // Merge the sorted halves together
            merge(arr, left, middle, right);
        }
    }

    public static <T extends Comparable<T>> void merge(T[] arr, int left, int middle, int right) {
        // Sizes of the two individual subarrays for merge
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays for merge
        T[] leftArr = Arrays.copyOfRange(arr, left, left + 1);
        T[] rightArr = Arrays.copyOfRange(arr, middle + 1, middle + 1 + n2);

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, middle + 1, rightArr, 0, n2);
        
        // Initial indexes of the subarrays and merged subarray
        int i = 0, j = 0, k = left;
        // Merge the temp arrays back into the array
        while (i < n1 && j < n2) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int size = 10000;
        Integer[] randomArr = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            randomArr[i] = random.nextInt(1000);
        }

        Integer[] arrayToSort1 = Arrays.copyOf(randomArr, size);
        Integer[] arrayToSort2 = Arrays.copyOf(randomArr, size);

        long startTime, endTime;

        startTime = System.nanoTime();
        mergeSort(arrayToSort1, 0, arrayToSort1.length - 1);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Time: " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        bubbleSort(arrayToSort2);
        endTime = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (endTime - startTime) / 1000000.0 + " ms");


    }
}
