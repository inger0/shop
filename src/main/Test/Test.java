import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yujingyang on 2017/4/14.
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Test1 test1 = new Test1(new Test1());
        Test1 test2 = test1.clone();
        System.out.println(test1.str == test2.str);
    }
}
