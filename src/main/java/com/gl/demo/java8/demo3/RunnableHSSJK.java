package com.gl.demo.java8.demo3;


import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @DESC 函数式接口
 * @Author by gl on 2017/4/6.
 * @Date 2017/4/6 15:35
 */
public class RunnableHSSJK {

    static Runnable r1 = () -> System.out.println("hello world 1");

    static Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("hello world 2");
        }
    };

    public static void process(Runnable r){
        r.run();
    }


    public static void main(String[] args) {

          //Lambda表达式引用的局部变量必须是最终的（ final）或事实上最终的
//        int portNumber = 1337;
//        Runnable r = () -> System.out.println(portNumber);
//        portNumber = 31337;


        process(r1);
        process(r2);

        process(() -> System.out.println("hello world 3"));

        //常用的 Predicate,Function,Consumer,
        //常用的 Predicate,Function,Consumer,
        //常用的 Predicate,Function,Consumer,

        //java.util.function.Predicate<T>接口定义了一个名叫test的抽象方法，它接受泛型T对象，并返回一个boolean
        Predicate<String> nonEmptyStrPredicate = (String str) -> !str.isEmpty();
        List<String> strList = filter(Arrays.asList("Lambda" ,"in","Action",""),nonEmptyStrPredicate);
        System.out.println(strList);

        //java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型T的对象，没有返回（void） 。你如果需要访问类型T的对象，并对其执行某些操作，就可以使用这个接口
        forEachAndHandle(Arrays.asList(1,3,4,5,6),(Integer i)-> System.out.println(i));
        forEachAndHandle(Arrays.asList("Lambda" ,"in","Action"),(String s)-> System.out.println(s));


        //java.util.function.Function<T, R>接口定义了一个叫作apply的方法，它接受一个泛型T的对象，并返回一个泛型R的对象
        List<Integer> list = map(Arrays.asList("Lambda" ,"in","Action"),(String s)->s.length());
        System.out.println(list);


    }

    /**
     * Predicate 例子
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    public  static <T> List<T> filter(List<T> list,Predicate<T> p){

        List<T> result = new ArrayList<T>();

        for(T t: list){
            if(p.test(t)){
                result.add(t);
            }
        }
        return  result;
    }

    /**
     * Consumer例子
     * @param list
     * @param c
     * @param <T>
     * @return
     */
    public static <T> void forEachAndHandle(List<T> list , Consumer<T> c){
        for(T t:list){
            if(t instanceof Integer){
                c.accept(t);
            }
        }
    }


    /**
     * Function 例子
     * @param list
     * @param f
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<R>();
        for(T t:list){
            result.add(f.apply(t));
        }
        return  result;
    }



}
