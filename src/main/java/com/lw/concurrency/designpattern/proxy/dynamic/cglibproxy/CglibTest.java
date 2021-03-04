package com.lw.concurrency.designpattern.proxy.dynamic.cglibproxy;

import net.sf.cglib.core.DebuggingClassWriter;


public class CglibTest {
    public static void main(String[] args) {

        try {


            //JDK 是采用实现的方式，必须要求代理的目标对象一定要实现一个接口
            //CGLib 采用继承 是覆盖父类方法
            //目的：都是生成一个新的类，去实现增强代码逻辑的功能

            //JDK Proxy 对于用户而言，必须要有一个接口实现，目标类相对来说复杂
            //CGLib 可以代理任意一个普通的类，没有任何要求

            //CGLib 生成代理逻辑更复杂，效率,调用效率更高，生成一个包含了所有的逻辑的FastClass，不再需要反射调用
            //JDK Proxy生成代理的逻辑简单，执行效率相对要低，每次都要反射动态调用

            //CGLib 有个坑，CGLib代理会忽略final方法

//            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"E://cglib_proxy_classes");

            Customer obj = (Customer) new CGlibMeipo().getInstance(Customer.class);
            System.out.println("=========》"+obj);
            obj.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
