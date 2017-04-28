package com.gl.demo.java8.demo3;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @DESC 复合Lambda
 * @Author by gl on 2017/4/22.
 * @Date 2017/4/22 17:52
 */
public class ComplexLambdas {




    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(
                new ComplexLambdas.Apple(80,"green","CN"),
                new ComplexLambdas.Apple(155, "green","VVD"),
                new ComplexLambdas.Apple(155, "green","USA"),
                new ComplexLambdas.Apple(120, "red","USA"));

        /**
         * 比较器复合
         * comparing(),reversed(),thenComparing()
         */
        //重量倒序
        inventory.sort(Comparator.comparing(Apple :: getWeight).reversed());
        System.out.println(inventory);

        //重量倒序，如果重量相同时按照国家排序
        inventory.sort(Comparator.comparing(Apple :: getWeight).reversed().thenComparing(Apple::getCountry));
        System.out.println(inventory);

        /**
         * 谓词复合
         * negate() 非
         * and() 和
         * or() 或
         *
         */
        Predicate<Apple> redApple = (Apple a) -> a.getColor().equals("red");
        //不是红苹果集合
        inventory = inventory.stream().filter(redApple.negate()).collect(Collectors.toList());
        System.out.println(inventory);

        //红苹果 且 大于150克
        Predicate<Apple> redAndHeavyApple = redApple.and((Apple a) -> a.getWeight()>150);

        //要么是红苹果且大于150克  要么是绿苹果
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and((Apple a) -> a.getWeight()>150).or((Apple a)-> a.getColor().equals("green"));


        /**
         * 函数复合
         *
         * andThen()
         * compose()
         *
         */

        Function<Integer,Integer> f = x -> x+1;
        Function<Integer,Integer> g = x -> x*2;
        Function<Integer,Integer> h = f.andThen(g);
        Function<Integer,Integer> h2 = f.compose(g);
        System.out.println(h.apply(1));
        System.out.println(h2.apply(1));

    }

    public static class Apple {
        private int weight;
        private String color ;

        private String country;

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Apple(int weight, String color, String country) {
            this.weight = weight;
            this.color = color;
            this.country = country;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }

}

