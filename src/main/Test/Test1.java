/**
 * Created by yujingyang on 2017/4/15.
 */
public class Test1 implements Cloneable{
    Test1 str;
    public Test1(Test1 str){

        this.str=str;
    }

    public Test1(){

    }
    public Test1 clone() throws CloneNotSupportedException{
        Test1 test1 = (Test1)super.clone();
        test1.str = test1.str.clone();
        return test1;
    }
}