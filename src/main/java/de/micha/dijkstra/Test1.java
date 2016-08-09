package de.micha.dijkstra;

/**
 * Created by micha on 08.08.16.
 */
public class Test1 {


    private static String a;
    private static String b;

    public Test1() {
    }

    public Test1(String s) {
        a= s;
    }

    public static void main(String[] args) {
        new Test1("katze").foo();
        new Test1().foo();
    }


    private void foo(){
        System.out.println("a:"+a);

        a= "hallo";b="welt";
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
