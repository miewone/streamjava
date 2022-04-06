package stream;

import java.util.Arrays;

/**
 * Created by wgPark on 2022-04-06.
 */
public class intAraayTest {
    public static void main(String[] args)
    {
        int test[] = {1,2,3,4,5,6};

        int testz =  Arrays.stream(test).max().getAsInt();
        System.out.println(testz);



    }
}
