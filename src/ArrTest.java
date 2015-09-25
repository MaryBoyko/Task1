/**
 * Created by user on 20.09.2015.
 */
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class ComparatorInt implements Comparator <Integer>{
    @Override
    public int compare (Integer int1, Integer int2){
        return int1.compareTo(int2);
    }
}

public class ArrTest {
    private static final int ARRAY_LEN = 10;

    private static <T> void PrintArray (final T[] array){
        for (int i = 0; i < array.length - 1; i++)
            System.out.print(array[i]+"  ");
        System.out.println();
    }

    public static <T> boolean IsSorted (Sorting <T> sorting, final T[] array, Comparator <T> comp){
        //PrintArray(array);
        sorting.Sort(array, comp);
        if (array.length == 0)
            return true;
        //PrintArray(array);
        for (int i = 0; i < array.length - 2; i++){
            if (comp.compare(array[i], array[i+1]) > 0)
                return false;
        }
        return true;
    }

    public static <T> boolean ContainsAll (Sorting <T> sorting, final T[] array, Comparator <T> comp){
        final T[] newArray = Arrays.copyOf(array, array.length);
        if (newArray.length == 0 && array.length == 0)
            return true;
        if (newArray.length != array.length)
            return false;
        final int []tempArr = new int[array.length];
        for (int i = 0; i < array.length; i++){
            tempArr[i] = 0;
            for (int j = 0; j < array.length; j++){
                if (comp.compare(array[i], array[j]) == 0)
                    tempArr[i]++;
            }
        }
        //PrintArray(newArray);
        sorting.Sort(newArray, comp);
        //PrintArray(newArray);
        for (int i = 0; i < array.length; i++){
            int j, temp = 0;
            for (j = 0; j < array.length; j++){
                if (comp.compare(newArray[j], array[i]) == 0)
                    temp++;
            }
            if (temp != tempArr[i])
                return false;
        }
        return true;
    }

    private static Integer[] GenerateArr (final int arrayLen){
        final Random rand = new Random(System.currentTimeMillis());
        final Integer[] array = new Integer[arrayLen];
        for (int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(100);
        return array;
    }

    @Test
    public void MustBeSorted(){
        ComparatorInt comp = new ComparatorInt();
        final Integer[] array1 = GenerateArr(ARRAY_LEN);
        Assert.assertTrue(IsSorted(ShellSort::Sort, array1, comp));

        final Integer[] array2 = GenerateArr(ARRAY_LEN);
        Assert.assertTrue(IsSorted(RadixSort::Sort, array2, comp));
    }

    @Test
    public void MustContainsAll(){
        ComparatorInt comp = new ComparatorInt();
        final Integer[] array1 = GenerateArr(ARRAY_LEN);
        Assert.assertTrue(ContainsAll(ShellSort::Sort, array1, comp));

        final Integer[] array2 = GenerateArr(ARRAY_LEN);
        Assert.assertTrue(ContainsAll(RadixSort::Sort, array2, comp));
    }

    @Test
    public void SortedAscendingArrayCase(){
        Comparator comp = new ComparatorInt();
        final Integer[] sortedAscArr1 = new Integer[ARRAY_LEN];
        for (int i = 0; i < ARRAY_LEN; i++)
           sortedAscArr1[i] = i;
        Assert.assertTrue(IsSorted(ShellSort::Sort, sortedAscArr1, comp));
        Assert.assertTrue(ContainsAll(ShellSort::Sort, sortedAscArr1, comp));

        final Integer[] sortedAscArr2 = new Integer[ARRAY_LEN];
        for (int i = 0; i < ARRAY_LEN; i++)
            sortedAscArr2[i] = i;
        Assert.assertTrue(IsSorted(RadixSort::Sort, sortedAscArr2, comp));
        Assert.assertTrue(ContainsAll(RadixSort::Sort, sortedAscArr2, comp));
    }

    @Test
    public void SortedDescendingArrayCase(){
        Comparator comp = new ComparatorInt();
        final Integer[] sortedDescArr1 = new Integer[ARRAY_LEN];
        for (int i = 0; i < ARRAY_LEN; i++)
            sortedDescArr1[i] = ARRAY_LEN - i;
        Assert.assertTrue(IsSorted(ShellSort::Sort, sortedDescArr1, comp));
        Assert.assertTrue(ContainsAll(ShellSort::Sort, sortedDescArr1, comp));

        final Integer[] sortedDescArr2 = new Integer[ARRAY_LEN];
        for (int i = 0; i < ARRAY_LEN; i++)
            sortedDescArr2[i] = ARRAY_LEN - i;
        Assert.assertTrue(IsSorted(RadixSort::Sort, sortedDescArr2, comp));
        Assert.assertTrue(ContainsAll(RadixSort::Sort, sortedDescArr2, comp));
    }

    @Test
    public void EmptyArrayCase(){
        Comparator comp = new ComparatorInt();
        final Integer[] emptyArr = new Integer[0];
        Assert.assertTrue(IsSorted(ShellSort::Sort, emptyArr, comp));
        Assert.assertTrue(ContainsAll(ShellSort::Sort, emptyArr, comp));

        Assert.assertTrue(IsSorted(RadixSort::Sort, emptyArr, comp));
        Assert.assertTrue(ContainsAll(RadixSort::Sort, emptyArr, comp));
    }
}
