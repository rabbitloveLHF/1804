package leave.java.BigDecimal.BigInteger;

import java.math.BigDecimal;

public class Test {
	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal("6.354572");
		BigDecimal bd1 = bd.setScale(5);
		System.out.println(bd1);
		BigDecimal bd2 = bd.setScale(3, BigDecimal.ROUND_HALF_EVEN);
		System.out.println(bd2);
	}
}
