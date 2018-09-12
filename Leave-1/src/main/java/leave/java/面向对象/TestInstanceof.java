package leave.java.面向对象;
/**
 * 测试instanceof
 * @author Administrator
 */
public class TestInstanceof {
	public static void main(String[] args) {
		B1 b1 = new B1();
		System.out.println(b1.b);
		b1.b=3;
		System.out.println(b1.b);
		A1 a1 = new A1();
		System.out.println(a1.b);
		System.out.println();
		if(b1 instanceof B1){
			System.out.println(6666);
		}
		System.out.println(A1.a);
		
	}
}
class A1 {
	static int a=2;
	int b=1;
}
class B1 extends A1{
	
}