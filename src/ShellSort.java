/**
 * Created by user on 22.09.2015.
 */

public class ShellSort {
    public static <T> void Sort (final T[] array, Comparator <T> comp){
        int arrayLen = array.length;
        for (int step = arrayLen/2; step > 0; step /= 2)
            for (int i = step; i < arrayLen-1; i++) {
                int j;
                T temp = array[i];
                for (j = i; j >= step; j -= step) {
                    if (comp.compare(temp, array[j-step]) < 0)
                        array[j] = array[j-step];
                    else break;
                }
                array[j] = temp;
            }
        return;
    }
}
