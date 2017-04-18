package com.gl.demo.java8.demo1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @DESC 描述
 * @Author by gl on 2017/4/11.
 * @Date 2017/4/11 14:02
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = getLowCaloricDishesNamesInJava8(Dish.menu);
        System.out.println(list);

        List<String> threeHightCallist = threeHighCaloricDishNames(Dish.menu);
        System.out.println(threeHightCallist);

        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        //流只能遍历一次
        //s.forEach(System.out::println);

        List<Dish> vMenu = Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        //System.out.println(JSONArray.toJSONString(vMenu));

        //筛选
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream().filter( a -> a%2==0)
                .distinct()
                .forEach(System.out::println);

        List<Dish> dishes = Dish.menu.stream().filter(p -> p.getCalories()>300)
                .skip(2)
                .collect(Collectors.toList());
       // System.out.println(JSONArray.toJSONString(dishes));


    }


    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){

        return dishes.stream().filter(d -> d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    public static List<String> threeHighCaloricDishNames(List<Dish> dishes){
        return dishes.stream().filter(d -> d.getCalories()>300)
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
    }
}
