package pract1;

import java.util.function.Consumer;

public class StringConsumer implements Consumer<String[]>{
    @Override
    public void accept(String[] arr) {
        long maxx = 0;
        for (int i = 0; i < arr.length; ++i)
            if (arr[i].chars().distinct().count() > maxx)
                maxx = arr[i].chars().distinct().count();

        for (int i = 0; i < arr.length; ++i)
            if (arr[i].chars().distinct().count() == maxx) {
                System.out.println(arr[i]);
                return;
            }
    }
    public static void main(String args[]) {
        Consumer<String[]> test = new StringConsumer();
        String[] strs = {"abcd", "aaaa", "cdfwa", "sddfsg"};
        test.accept(strs);
    }
}
