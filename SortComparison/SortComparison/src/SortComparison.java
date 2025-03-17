import java.util.*;

public class SortComparison {

    // Merge sort implementation
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, left, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left +1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n1; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArray[i++];
        }

        while (j < n2) {
            arr[k++] = rightArray[j++];
        }
    }

    // Bubble sort implementation
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
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

    public static void main(String[] args) {
        int size = 10000;
        int[] randomArray = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt(1000);
        }

        int[] arrayToSort1 = randomArray.clone();
        int[] arrayToSort2 = randomArray.clone();

        long startTime = System.nanoTime();
        mergeSort(arrayToSort1, 0, arrayToSort1.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Merge Sort Time: " + (endTime - startTime) + "nanoseconds");

        startTime = System.nanoTime();
        bubbleSort(arrayToSort2);
        endTime = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (endTime - startTime) + "nanoseconds");
    }
}
