package demo.background;

import java.util.Iterator;
import java.util.Vector;

public class demo {
  public static void main(String[] args) {

   // Test test =  new Test();
       Vector syn = new Vector();

      syn.add(1);
      syn.add(2);
/*      Iterator iterator = syn.iterator();
      while (iterator.hasNext()) {
          System.out.println(iterator.next());
      }*/
      ;
    System.out.println(syn.indexOf(3));
  }
}
class Test{
    public  static int a = 1;

    public static  void sta(){


            System.out.println("打印");

    }

    public static  void sta2(){

       sta();
    }
}
