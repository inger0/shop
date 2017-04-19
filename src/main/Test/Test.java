import com.google.common.collect.Maps;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by yujingyang on 2017/4/14.
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println(BCrypt.hashpw("123456",BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("123456", "$2a$10$EyBDS07w9zzcikQfiVchEuuis6afOZfM5uXnjb/.ri4ukeS7x57yK"));
        System.out.println(("$2a$10$EyBDS07w9zzcikQfiVchEuuis6afOZfM5uXnjb/.ri4ukeS7x57yK".compareTo(BCrypt.hashpw("123456", "$2a$10$EyBDS07w9zzcikQfiVchEuuis6afOZfM5uXnjb/.ri4ukeS7x57yK")) == 0));
    }
}
