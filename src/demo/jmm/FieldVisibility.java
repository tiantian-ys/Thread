package demo.jmm;
/**
  *  @Time:     2020/8/10  14:46
  *  @author：  Mr.tiantian
  *             演示可见性会导致的问题
  */
public class FieldVisibility {


        int a = 1;
        int b = 2;


    private void change() {
        a = 3;
        b = a;
    }
    private void print() {
        System.out.println("a:"+a+"-b:"+b);
    }



    public static void main(String[] args) {

        while (true){
        FieldVisibility fieldVisibility =new FieldVisibility();
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     fieldVisibility.change();
                 }

             }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    fieldVisibility.print();
                }

            }).start();
            if( fieldVisibility.a == 3 && fieldVisibility.b == 2){
        System.out.println("打印出来了");
                fieldVisibility.print();
        System.exit(0);        System.exit(1);
            }
        }
      }

}
