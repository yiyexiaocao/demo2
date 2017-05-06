package com.gl.demo.java8.demo5;

import com.gl.demo.java8.demo4.Dish;

import java.util.Arrays;
import java.util.List;

import static com.gl.demo.java8.demo4.Dish.menu;
import static java.util.stream.Collectors.toList;

/**
 * @DESC 流 映射
 * @Author by gl on 2017/5/6.
 * @Date 2017/5/6 11:45
 */
public class Mapping {

    public static void main(String[] args) {
        // map
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);
        List<Integer> dishNamesLength = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNamesLength);

        List<String> words = Arrays.asList("Hello", "World", "GeLiang");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

        // flatMap
        List<String> strList = words.stream()
                .flatMap(str -> Arrays.stream(str.split("")))
                .distinct()
                .collect(toList());
        System.out.println(strList);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap((Integer i) -> numbers2.stream()
                                .map((Integer j) -> new int[]{i, j})
                        )
                        .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                        .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));

        System.out.println("--------------another methord-----------");

        List<int[]> pairs2 =
                numbers1.stream()
                        .flatMap((Integer i) -> numbers2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));

    }
}

