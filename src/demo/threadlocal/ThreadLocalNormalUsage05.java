package demo.threadlocal;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
  *  @Time:     2020/8/13  16:10
  *  @author：  Mr.tiantian
  *             ThreadLocal 使用二
  */
public class ThreadLocalNormalUsage05 {
    public static void main(String[] args) {
        new ServiceSet().procee();
    }
}

class ServiceSet{
        public void procee(){
            User user = new User("李四");
            UertThreadLocal.threadLocal.set(user);
            new Service1().procee();
            System.out.println("ServiceSet:设置成功了");
        }
}
class Service1{
    public void procee(){
        User user =  UertThreadLocal.threadLocal.get();
        System.out.println("Service1:"+user.name);
        new Service2().procee();
    }


}
class Service2{
    public void procee(){
        User user =  UertThreadLocal.threadLocal.get();
        System.out.println("Service2:"+user.name);
        new Service3().procee();
    }
}
class Service3{
    public void procee(){
        User user =  UertThreadLocal.threadLocal.get();
        System.out.println("Service3:"+user.name);
    }
}


class UertThreadLocal{
    public  static ThreadLocal<User> threadLocal = new ThreadLocal<>();
}

class User{
    public User(String name) {
        this.name = name;
    }

    public String  name;

}
