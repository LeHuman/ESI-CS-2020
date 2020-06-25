package sort;

public class sort {

    public void bubble(int array[]) {
boolean swapped = false;
do {
    swapped = false;
    for (int i = 1; i < array.length; i++) {
        if (array[i - 1] > array[i]) {
            // Swap elements
            int swap = array[i - 1];
            array[i - 1] = array[i];
            array[i] = swap;
            swapped = true;
        }
    }
} while (swapped);
    }
}