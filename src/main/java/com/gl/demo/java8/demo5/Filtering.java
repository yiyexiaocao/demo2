package com.gl.demo.java8.demo5;

import com.gl.demo.java8.demo4.Dish;

import java.util.Arrays;
import java.util.List;

import static com.gl.demo.java8.demo4.Dish.menu;
import static java.util.stream.Collectors.toList;

/**
 * @DESC 流 筛选和切片
 * @Author by gl on 2017/5/6.
 * @Date 2017/5/6 11:37
 */
public class Filtering {

    public static void main(String[] args) {

        // fileter()筛选
        menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList())
                .forEach(System.out::println);
        System.out.println("-----------------------");

        // Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        System.out.println("-----------------------");

        // limit()
        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList())
                .forEach(System.out::println);
        System.out.println("-----------------------");

        //skip
        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .skip(2)
                .collect(toList())
                .forEach(System.out::println);

    }
}
