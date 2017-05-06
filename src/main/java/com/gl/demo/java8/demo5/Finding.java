package com.gl.demo.java8.demo5;

import com.gl.demo.java8.demo4.Dish;

import java.util.Optional;

import static com.gl.demo.java8.demo4.Dish.menu;

/**
 * @DESC 流 查找与匹配
 * @Author by gl on 2017/5/6.
 * @Date 2017/5/6 14:46
 */
public class Finding {

    public static void main(String[] args) {

        //anyMatch()
        boolean isVegetarianFriendlyMenu = menu.stream().anyMatch(Dish::isVegetarian);
        if(isVegetarianFriendlyMenu){
            System.out.println("Vegetarian friendly");
        }

        //allMatch(),noneMatch()
        boolean isHealthyMenu = menu.stream().allMatch((Dish dish) -> dish.getCalories()<1000);
        boolean isHealthyMenu2 = menu.stream().noneMatch((Dish dish) -> dish.getCalories()>=1000);
        System.out.println(isHealthyMenu);
        System.out.println(isHealthyMenu2);

        Optional<Dish> optional =menu.stream().filter(Dish::isVegetarian).findAny();
        optional.ifPresent((Dish d)-> System.out.println(d.getName()));


    }
}
