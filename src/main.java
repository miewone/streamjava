import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Created by wgPark on 2022-04-05.
 */
public class main {
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

//      1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
        List<Transaction> sortin2011 = transactions.stream().filter(tran -> tran.getYear()==2011).sorted(Comparator.comparing(Transaction::getYear)).collect(toList());
        System.out.println(sortin2011);
//      2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> td = transactions.stream().map(tr -> tr.getTrader().getCity()).distinct().collect(toList());
        System.out.println(td);
//      3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<String> traders = transactions.stream().filter(tr -> tr.getTrader().getCity().equals("Cambridge")).map(trade -> trade.getTrader().getName()).sorted().collect(toList());
        System.out.println(traders);
//      4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
//      5. 밀라노에 거래자가 있는가?
        List<String> ts = transactions.stream().filter(tr -> tr.getTrader().getCity().equals("Milan")).map(p -> p.getTrader().getName()).collect(toList());
        System.out.println(ts);
//      6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
        transactions.stream().filter(city->city.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
//      7. 전체 트랜잭션 중 최댓값은 얼마인가?
        Optional<Integer> maxs = transactions.stream().map(Transaction::getYear).reduce(Integer::max);
//      8. 전체 트랜잭션 중 최솟값은 얼마인가


        System.out.println("total:");
        double total = transactions.stream().collect(averagingInt(Transaction::getValue));
        System.out.println(total);

        IntSummaryStatistics menuSt = transactions.stream().collect(summarizingInt(Transaction::getValue));
        System.out.println(menuSt.getMax());

        String transString = transactions.stream().map(tz -> tz.getTrader().getName()).distinct().sorted().collect(joining(","));
        System.out.println(transString);

        int totalvalues = transactions.stream().collect(reducing(0,Transaction::getValue,Integer::sum));
        System.out.println(totalvalues);

        Map<Trader,List<Transaction>> tes = transactions.stream().collect(groupingBy(Transaction::getTrader,filtering(trans -> trans.getValue()>50,toList())));
        System.out.println(tes);
    }
}

class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}

class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + "," +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}