package demo.deadlock;

import java.util.Random;

/**
 * @Time: 2020/8/11 14:56
 *
 * @author： Mr.tiantian 活锁,因为两个线程互相谦让对方线程导致
 *
 *           解决方案:添加随机数 根据随机数决定是否谦让    禁止无限制谦让线程
 *
 */
public class LiveLock {

  /** 行为动作类 */
  static class Action {
    private Person person;

    public Action(Person person) {
      this.person = person;
    }

    public Person getPerson() {
      return person;
    }

    public void setPerson(Person person) {
      this.person = person;
    }

    public synchronized void eat() {
      // System.out.println();
      System.out.printf("%s吃完了!", person.name);
    }
  }

  /** 人物类 */
  static class Person {
    public String name;
    private boolean isHungry;

      public Person(String name) {
          this.name = name;
          this.isHungry = true;
      }

      public void eating(Action action,Person person){

       while (isHungry){
           if(action.person != this){
               try {
                   Thread.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           Random random = new Random();
           if(person.isHungry && random.nextInt(10) < 9 ){
               System.out.println(name + ": 亲爱的" + person.name + "你先吃吧");
               action.setPerson(person);
               continue;
           }
           action.eat();
           isHungry =false;
           System.out.printf("%s吃完了!",name);
           action.setPerson(person);
       }
      }

  }

  public static void main(String[] args) {
      Person personA =new Person("张三");

      Person personB =new Person("李四");

      Action action =new Action(personA);




      new Thread(new Runnable() {
        @Override
        public void run() {
      personA.eating(action,personB);
        }
    }).start();

      new Thread(new Runnable() {
          @Override
          public void run() {
              personB.eating(action,personA);
          }
      }).start();
  }
}
