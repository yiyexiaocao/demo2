package com.gl.demo.java8.demo5;

import javax.sound.midi.Track;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @DESC 交易员 交易 相关实例
 * @Author by gl on 2017/5/6.
 * @Date 2017/5/6 15:33
 */
public class PuttingIntoPractice {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1.找出2011年发生的所有交易，并按交易额排序（从低到高）
        List<Transaction> tr2011List = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(tr2011List);

        // 2.交易员都在哪些不同的城市工作过
        List<String> cityList = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cityList);

        // 3.查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> combridgeTrList = transactions.stream()
                .map(t -> t.getTrader())
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(combridgeTrList);

        // 4.返回所有交易员的姓名字符串，按字母顺序排序
        String traderNameStr = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderNameStr);

        // 5.有没有交易员是在米兰工作的
        Boolean inMilan = transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        System.out.println(inMilan);

        // 6.打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7.所有交易中，最高的交易额是多少
        int maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println(maxValue);

        // 8.找到交易额最小的交易
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(t -> System.out.println(t));

        Optional<Transaction> smallestTransaction =
                transactions.stream()
                        .reduce((t1, t2) ->
                                t1.getValue() < t2.getValue() ? t1 : t2);
        smallestTransaction.ifPresent(t -> System.out.println(t.getValue()));

        // 9.把所有在米兰的交易员的城市改成剑桥
        transactions.stream().map(Transaction::getTrader)
                .filter(t -> "Milan".equals(t.getCity()))
                .forEach(t -> t.setCity("Cambridge"));
        System.out.println(transactions);
        System.out.println(9.0%1 == 0);
    }
}
