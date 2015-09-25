/**
 * Created by user on 25.09.2015.
 */
import java.util.Arrays;
import java.util.Comparator;

public class RadixSort {
    private static int Digit (int num, int pos){
        return ((num >> (8*pos)) & 255);
    }

    public static <T> void Sort (final T[] array, Comparator <T> comp){
        if (array.length == 0)
            return;
        int maxBytes = Integer.SIZE /8, arrayLen = array.length;
        for (int i = 0; i < maxBytes; i++){
            int []tempArr = new int[256];
            for (int j = 0; j < arrayLen; j++){
                tempArr[Digit((Integer)array[j], i)]++;
            }
            for (int j = 1; j < 256; j++){
                tempArr[j] += tempArr[j-1];
            }
            Integer[] newArray = new Integer[arrayLen];
            for (int j = arrayLen-1; j >= 0; j--) {
                newArray[--tempArr[Digit((Integer) array[j], i)]] = (Integer) array[j];
            }
            for (int j = 0; j < arrayLen; j++){
                array[j] = (T)newArray[j];
            }
        }
    }
}
