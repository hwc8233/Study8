# Java8新特性系列--访问权限修饰符



>   包访问权限可以把相关类聚到一个包下，以便它们能轻易地相互访问。包里的类给它们的包访问权限的成员赋予了相互访问的权限，所以你 “ 拥有 ” 了包内的程序代码。只能通过你所拥有的代码去访问你所拥有的其他代码，这样规定很有意义。构建包访问权限机制是将类聚集在包中的重要原因之一。在许多语言中，在文件中组织定义的方式是任意的，但是在 Java 中你被强制以一种合理的方式组织它们。另外，你可能会将不应该对当前包中的类具有访问权限的类排除在包外。

  类控制着有哪些代码可以有权限的访问自己的成员（成员变量及方法），而取得类成员访问权的方式是：

  1.public ：公开的，只要引用都可访问。
  2.private ：类内部使用，在类外部没有权限访问。
  3.protected ：继承权限，子类继承父类后，子类有权限访问父类的修饰符。
  4.default：默认访问权限，Java8新特性，没有关键字修饰默认就是default权限，该关键字修饰的类、常量或者方法只有在同一包名下才有访问权限。

# public

  当时用关键字public时，意味着紧跟public后的成员变量对于每个人都是可用的（有权限访问），类库的客户端程序员更是如此，例子：



```csharp
package com.mxu.demo;

public class PackageAuthorityDemo {
    
    public int index = 0;

    public PackageAuthorityDemo() {
        System.out.println("PackageAuthorityDemo Contructor");
    }
    
    public void printTest() {
        System.out.println("printTest");
    }
    
    void printTest2() {
        System.out.println("printTest2");
    }
}
```

   PackageAuthorityDemo 类在com.mxu.demo包名下。
   现在我们在外部类中调用PackageAuthorityDemo相关常量与函数。



```java
package com.mxu.demo2;

import com.mxu.demo.PackageAuthorityDemo;

public class AuthorityTest {
    
    public static void main(String[] args) {
        PackageAuthorityDemo pDemo = new PackageAuthorityDemo();
        pDemo.index = 1;
        //pDemo.printTest2();//The method printTest2() from the type PackageAuthorityDemo is not visible
    }
    
}
```

   输出



```undefined
PackageAuthorityDemo Contructor
```

  在com.mxu.demo外使用PackageAuthorityDemo时，你可以创建一个PackageAuthorityDemo 对象，因为它的构造器和类都是public的，也可以访问使用public修饰的int常量index，并对index常量进行赋值；但是你却无法访问printTest2()函数，因为该函数没有使用public修饰符修饰，所以在com.mxu.demo外，你无法对printTest2()函数进行访问；没有public修饰符修饰的常量或者函数是无法在包外进行访问的，这是编译器所禁止的。

# private

  当使用private关键字修饰一个类时，其他任何类都是没有权限来访问这个类的，包括在同一包名下的其他类；当使用private关键字修饰一个常量的时候，该常量只能在该类中使用（不管该类的修饰符是什么）；当使用private关键字修饰函数时，该函数也同样只能在该类中使用。
  private关键字修饰的常量或者函数常用于程序员自己在该类内部自己使用而不想外部人员使用的情况；使用private关键字修饰的常量或者方法，程序员可以自己在内部随意修改而不会影响外部客户端程序员的逻辑。例子：



```csharp
package com.mxu.demo;

class Water{
    
    private int index = 0;
    
    public int count = 0;
    
    private Water(){}
    
    static Water getWater() {
        System.out.println("getWater");
        return new Water();
    }
}

public class Alcohol {
    
    public static void main(String[] args) {
        //Water water = new Water();//The constructor Water() is not visible

        Water water = Water.getWater();
        //water.index = 2;//The field Water.index is not visible
        water.count = 1;
        
    }

}
```

输出



```undefined
getWater
```

  以上例子展示了private关键字的使用，可以控制创建对象，防止别人直接访问某个特定的构造器，在例子中，你无法通过构造器直接创建一个Water对象，必须使用Water的getWater()方法创建对象；使用private关键字修饰的常量同样如此；设计模式中工厂设计模式就是用类似的方法来创建对象的。

# protected

  继承访问权限，protected关键字对于继承更有意义，对于不想让外部程序员或者客户端程序员访问的内容我们通常使用private关键字就比较理想，但是在实际项目中，经常会有想把一个事物尽量对外隐藏，而允许派生类的成员访问，这个时候protected关键字就会使我们的最佳选择了。
  关键字protected表示 "就类的用户而言，这是private的，但是对于任何继承它的子类或者同一包中的类，它是可以访问的。 "protected也提供了包访问权限。
  尽管可以创建 protected 属性，但是最好的方式是将属性声明为 private 以一直保留更改底层实现的权利。然后通过 protected 控制类的继承者的访问权限。
  例子：



```java
package com.mxu.demo;

class Fruit {
    private String name;

    protected void setName(String fruitName) {
        this.name = fruitName;
    }

    Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " 我是一个水果，我的名称是：" + name;
    }

}

public class Apple extends Fruit {

    private int number;

    public Apple(String name, int fruitNum) {
        super(name);
        this.number = fruitNum;
    }

    public void changeNum(String name, int fruitNum) {
        setName(name);
        this.number = fruitNum;
    }

    @Override
    public String toString() {
        return "序号：" + number + ": " + super.toString();
    }

    public static void main(String[] args) {
        Apple fruit = new Apple("Apple", 1);
        System.out.println(fruit);
        fruit.changeNum("Peach", 2);
        System.out.println(fruit);
    }
    
}
```

  输出：



```undefined
序号：1:  我是一个水果，我的名称是：Apple
序号：2:  我是一个水果，我的名称是：Peach
```

   在子类Apple的changeNum()方法可以调用父类Fruit的setName()方法，这就是protected的意义所在，只在子类中可以调用父类中的protected关键字修饰的函数或常量，其他非继承类无法调用protected修饰的函数或常量。

# default（friendly）

  包访问权限是只有在同一包下的类才能进行相互的访问，不在同一包下是无法进行访问的。
  例子：
  同在一个包下：



```java
package com.mxu.demo;

public class PackageAuthorityDemo2 {

    public static void main(String[] args) {
        PackageAuthorityDemo packageAuthorityDemo = new PackageAuthorityDemo();
        packageAuthorityDemo.printTest();
        packageAuthorityDemo.printTest2();
    }
}
```

  输出



```undefined
PackageAuthorityDemo Contructor
printTest
printTest2
```

  不在一个包下：



```java
package com.mxu.demo2;

import com.mxu.demo.PackageAuthorityDemo;

public class AuthorityTest2 {
    public static void main(String[] args) {
        PackageAuthorityDemo packageAuthorityDemo = new PackageAuthorityDemo();
        packageAuthorityDemo.printTest();
        //packageAuthorityDemo.printTest2();//The method printTest2() from the type PackageAuthorityDemo is not visible
    }
}
```

  输出



```undefined
PackageAuthorityDemo Contructor
printTest
```

  通过以上例子，我们可以看到在同一个包下PackageAuthorityDemo2 类可以访问PackageAuthorityDemo 的printTest2()对象，不在同一包下的AuthorityTest2 则不能访问PackageAuthorityDemo 的printTest2()对象。
  [包权限的意义](https://links.jianshu.com/go?to=https%3A%2F%2Fwww.jb51.net%2Farticle%2F48304.htm)：

>   在Java中只有单继承，如果要让一个类赋予新的特性，通常是使用接口来实现，在C++中支持多继承，允许一个子类同时具有多个父类的接口与功能，在其他语言中，让一个类同时具有其他的可复用代码的方法叫做mixin。新的Java 8 的这个特新在编译器实现的角度上来说更加接近Scala的trait。 在C#中也有名为扩展方法的概念，允许给已存在的类型扩展方法，和Java 8的这个在语义上有差别。

  [各关键字权限区别](https://links.jianshu.com/go?to=https%3A%2F%2Fwww.cnblogs.com%2Fmingforyou%2Fp%2F5254307.html):

| 作用域            | 当前类 | 同package | 子孙类 | 其他package |
| ----------------- | ------ | --------- | ------ | ----------- |
| public            | √      | √         | √      | √           |
| protected         | √      | √         | √      | ×           |
| friendly(default) | √      | √         | ×      | ×           |
| private           | √      | ×         | ×      | ×           |

  以上内容主要参考[OnJava8](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2FLingCoder%2FOnJava8)
  感谢您的阅读，祝您学习愉快！