package leave.java.面向对象;
/**
 * 测试静态方法和静态变量被子类继承后的情况
 * @author Administrator
 */
public class TestStatic {
	public static void main(String[] args) {
		System.out.println(B.a);
		B.a = 2;
		System.out.println(A.a);
		B b = new B();
		System.out.println();
		B.hello();
	}
}
class A{
	static int a = 1;
	final int c = 6;
	static final int d = 7;
	public void say(){}
	static void hello(){
		System.out.println("hello");
	}
}

class B extends A{
	static int a = 7;
	@Override
	public void say() {
	}
	
	static void hello(){
		System.out.println("66666");
	}
}