import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberOperations {
    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20.5);
        numbers.add(30.121);
        numbers.add(40.7f);
        numbers.add(50L);
        numbers.add(60.3);
        numbers.add(70);
        numbers.add(80.1);
        numbers.add(90);
        numbers.add(100.9);
        numbers.add(new BigDecimal("123.66666666666666666666"));

        System.out.println("Оригінальні числа:");
        for (Number num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("\nЧисла у форматі цілих:");
        for (Number num : numbers) {
            System.out.print(num.intValue() + " ");
        }
        System.out.println();

        System.out.println("\nОкруглені числа:");
        for (Number num : numbers) {
            BigDecimal rounded;
            if (num instanceof BigDecimal) {
                rounded = ((BigDecimal) num).setScale(0, RoundingMode.HALF_UP);
            } else {
                rounded = new BigDecimal(num.toString()).setScale(0, RoundingMode.HALF_UP);
            }
            System.out.print(rounded + " ");
        }
        System.out.println();

        System.out.println("\nЧисла з 2 знаками після коми:");
        for (Number num : numbers) {
            System.out.printf("%.2f ", num.doubleValue());
        }
        System.out.println();

        List<Integer> integers = new ArrayList<Integer>();
        List<Double> doubles = new ArrayList<Double>();
        List<Float> floats = new ArrayList<Float>();
        List<Long> longs = new ArrayList<Long>();
        List<BigDecimal> big = new ArrayList<>();

        for (Number num : numbers) {
            if (num instanceof Integer) {
                integers.add((Integer) num);
            } else if (num instanceof Double) {
                doubles.add((Double) num);
            } else if (num instanceof Float) {
                floats.add((Float) num);
            } else if (num instanceof Long) {
                longs.add((Long) num);
            } else if (num instanceof BigDecimal){
                big.add((BigDecimal) num);
            }
        }

        System.out.println("\nРозділення за типами:");
        System.out.println("Integers: " + integers);
        System.out.println("Doubles: " + doubles);
        System.out.println("Floats: " + floats);
        System.out.println("Longs: " + longs);
        System.out.println("BigDecimal: " + big);
        Number max = numbers.get(0);
        for (Number num : numbers) {
            if (num.doubleValue() > max.doubleValue()) {
                max = num;
            }
        }
        System.out.println("\nНайбільше число: " + max);

        Number i = numbers.get(0);
        Number j = numbers.get(numbers.size()-1);

        BigDecimal first = new BigDecimal(i.toString());
        BigDecimal last = new BigDecimal(j.toString());

        BigDecimal res = first.divide(last, 10, RoundingMode.HALF_UP);
        System.out.println("ділення" + res);
        System.out.println("ділення" + i);
        System.out.println("ділення" + j);
        System.out.println("ділення" + first);
        System.out.println("ділення" + last);


        System.out.println("\nДемонстрація BigDecimal:");
        BigDecimal bd1 = new BigDecimal("0.1");
        BigDecimal bd2 = new BigDecimal("0.2");
        BigDecimal sum = bd1.add(bd2);
        System.out.println("0.1 + 0.2 = " + sum);
        System.out.println("0.1 + 0.2 з округленням до 2 знаків = " +
                sum.setScale(2, RoundingMode.HALF_UP));
    }
}