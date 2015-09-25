/**
 * Created by user on 20.09.2015.
 */
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Random;

class ComparatorInt implements Comparator <Integer>{
    @Override
    public int compare (Integer int1, Integer int2) {
        return int1.compareTo(int2);
    }
}

public class ArrTest {
    private static final int ARRAY_LEN = 10;

    private static <T> void PrintArray (final T[] array){
        for (int i = 0; i < ARRAY_LEN - 1; i++)
            System.out.print(array[i]+"  ");
        System.out.println();
    }

    public static <T> boolean IsSorted (final T[] array, Comparator <T> comp){
        //PrintArray(array);
        ShellSort.Sort(array, comp);
        Assert.assertTrue(array.length == ARRAY_LEN);
        //PrintArray(array);
        for (int i = 0; i < array.length - 2; i++){
            if (comp.compare(array[i], array[i+1]) > 0)
                return false;
        }
        return true;
    }

    public static <T> boolean ContainsAll (final T[] array, Comparator <T> comp){
        final T[] newArray = Arrays.copyOf(array, ARRAY_LEN);
        //PrintArray(newArray);
        ShellSort.Sort(newArray, comp);
        Assert.assertTrue(newArray.length == ARRAY_LEN);
        //PrintArray(newArray);
        for (int i = 0; i < ARRAY_LEN - 1; i++){
            int j;
            for (j = 0; j < ARRAY_LEN - 1; j++){
                if (comp.compare(newArray[j], array[i]) == 0)
                    break;
            }
            if (j == (ARRAY_LEN - 1))
                return false;
        }
        return true;
    }

    private static Integer[] GenerateArr (final int arrayLen){
        final Random rand = new Random(System.currentTimeMillis());
        final Integer[] array = new Integer[arrayLen];
        for (int i = 0; i < ARRAY_LEN; i++)
            array[i] = rand.nextInt(100);
        return array;
    }

    @Test
    public void MustBeSorted(){
        ComparatorInt comp = new ComparatorInt();
        final Integer[] array = GenerateArr(ARRAY_LEN);
        Assert.assertTrue(IsSorted(array, comp));
    }

    @Test
    public void MustContainsAll(){
        ComparatorInt comp = new ComparatorInt();
        final Integer[] array = GenerateArr(ARRAY_LEN);
        Assert.assertTrue(ContainsAll(array, comp));
    }
}
