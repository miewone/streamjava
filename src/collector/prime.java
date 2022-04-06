package collector;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * Created by wgPark on 2022-04-06.
 */
public class prime {
    public boolean isPrime(int candidate)
    {
        return IntStream.range(2,candidate)
                .noneMatch(i -> candidate % i ==0);
    }
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.range(2,n)
                .boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }
    public static void main(String[] args)
    {
        prime pri = new prime();
        Map<Boolean, List<Integer>> ts = pri.partitionPrimes(1000000);
        System.out.println(ts);
    }
}
