package test;

import java.io.*;

/**
 * Created by yujingyang on 2017/4/26.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        File f = new File("/Users/yujingyang/Downloads/psb.gif");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String str = bufferedReader.readLine();
        while (str!=null){
            System.out.println(str);
            if(str.contains("var"))
                System.out.println("get script");
            str = bufferedReader.readLine();
        }
    }
}
