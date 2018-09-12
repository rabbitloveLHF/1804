package leave.java.面向对象;

public class TestNewObject {
	public static void main(String[] args) {
		B2 b = new B2();
	}
}
class A2{
	static int a1 = 0;
	static {
		//1.System.out.println(a2);不允许访问,说明静态块执行在实例变量之前
		System.out.println("A2的静态块!!!");
		System.out.println("A2的静态成员a1="+a1);//2.1允许
	}
	public A2() {
		System.out.println("A2的构造!!!");
		System.out.println("A2的实例成员a2="+a2);
	}
	int a2 = 1;
	
}
class B2 extends A2{
	static {
		//2.2 System.out.println(b1);不允许访问,说明静态块和静态变量,谁在前面执行谁
		System.out.println("B2的静态块");
	}
	static int  b1 = 3;
	int b2 = 4;
	public B2(){
		//super();默认有
		System.out.println("B2的构造");
	}
}