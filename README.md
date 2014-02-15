是时候讨论这个Refactor利器了，最初看到这个重构的过程是从ThoughtWorks郑大晔校开始的，只是之前对于Java的另外一个编辑器Eclipse的坏感。。这些在目前已经不是很重要了，试试这个公司里面应用广泛的编辑器。

##开发过程##

开发的流程大致就是这样子的，测试先行算是推荐的。

    编写测试->功能代码->修改测试->重构
    
上次在和buddy聊天的时候，才知道测试在功能简单的时候是后行的，在功能复杂不知道怎么手手的时候是先行的。

##Intellij Idea重构##

开始之前请原谅我对于Java语言的一些无知，然后，看一下我写的Main函数：

	package com.phodal.learing;

	public class Main {

    	public static void main(String[] args) {
        	int c=new Cal().add(1,2);
        	int d=new Cal2().sub(2,1);
        	System.out.println("Hello,s");
        	System.out.println(c);
        	System.out.println(d);
    	}
	}
	
代码写得还好(自我感觉)，先不管Cal和Cal2两个类。大部分都能看懂，除了c,d不知道他们表达的是什么意思，于是。

###Rename##

**快捷键:Shift+F6**

**作用:重命名**

 - 把光标丢到int c中的c，按下shift+f6，输入result_add
 - 把光标移到int d中的d，按下shift+f6，输入result_sub

于是就有

	package com.phodal.learing;

	public class Main {

    	public static void main(String[] args) {
        	int result_add=new Cal().add(1,2);
        	int result_sub=new Cal2().sub(2,1);
        	System.out.println("Hello,s");
        	System.out.println(result_add);
        	System.out.println(result_sub);
    	}
	}
	
	
###Extract Method###

**快捷键:alt+command+m**

**作用:扩展方法**

- 选中System.out.println(result_add);
- 按下alt+command+m
- 在弹出的窗口中输入mprint

于是有了

    public static void main(String[] args) {
        int result_add=new Cal().add(1,2);
        int result_sub=new Cal2().sub(2,1);
        System.out.println("Hello,s");
        mprint(result_add);
        mprint(result_sub);
    }

    private static void mprint(int result_sub) {
        System.out.println(result_sub);
    }
    
似乎我们不应该这样对待System.out.println，那么让我们内联回去

###Inline Method###

**快捷键:alt+command+n**

**作用:内联方法**

- 选中main中的mprint
- alt+command+n
- 选中Inline all invocations and remove the method(2 occurrences) 点确定

然后我们等于什么也没有做了~~: 


    public static void main(String[] args) {
        int result_add=new Cal().add(1,2);
        int result_sub=new Cal2().sub(2,1);
        System.out.println("Hello,s");
        System.out.println(result_add);
        System.out.println(result_sub);
    }

似乎这个例子不是很好，但是够用来说明了。

###Pull Members Up###

开始之前让我们先看看Cal2类:

	public class Cal2 extends Cal {

    	public int sub(int a,int b){
        	return a-b;
   		}
	}
	
以及Cal2的父类Cal

	public class Cal {

    	public int add(int a,int b){
        	return a+b;
    	}

	}
	
最后的结果，就是将Cal2类中的sub方法，提到父类:

	public class Cal {

    	public int add(int a,int b){
        	return a+b;
    	}

    	public int sub(int a,int b){
        	return a-b;
    	}
	}
	
而我们所要做的就是鼠标右键

**Refactor-&gt;Pull Members Up**

###Pull Members Down###

将方法推迟到子类



###其他###

正在补充学习中

##结论##

Intellij Idea自带的重构功能似乎真的很强大，其他目前感觉很强大的东西，比如Live Template，丢到下次讨论。
