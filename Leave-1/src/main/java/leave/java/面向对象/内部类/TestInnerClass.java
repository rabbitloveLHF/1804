package leave.java.面向对象.内部类;

import leave.java.面向对象.内部类.C.D;
import leave.java.面向对象.内部类.C1.D1;

public class TestInnerClass {
	public static void main(String[] args) {
		C c = new C();
		D d = c.new D();
		
		D1 d1 = new D1();
		
		//局部内部类-->方法内定义的类
		E e = new E();
		W w = e.a();
	}
}




class C1{
	static class D1{
		
	}
}
class C {
	class D{
	}
}
interface W{}
class E {
	W a(){
		class Inner1 extends C{
			
		}
		class Inner implements W{
		}
		Inner i = new Inner();
		return i;
	}
}



















