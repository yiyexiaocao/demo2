package com.gl.demo.java8.demo5;

import com.gl.demo.java8.demo4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.gl.demo.java8.demo4.Dish.menu;

/**
 * @DESC 流 规约 reduce
 * @Author by gl on 2017/5/6.
 * @Date 2017/5/6 17:09
 */
public class Reducing {
    public static void main(String...args){

        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }
}
