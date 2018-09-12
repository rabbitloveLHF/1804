package leave.java.排序.冒泡排序;

import java.util.Arrays;

/**
 * 冒泡排序
 * 
 * @author Administrator
 */
public class SortByBubble {
	public static void main(String[] args) {
		int[] a  = {1,8,5,38,59,20,11,58,21,66,79,64};
		sort(a);
	}

	public static void sort(int[] a) {
		/**
		 * 							    <--j		
		 * [1,8,5,38,59,20,11,58,21,66,79,64]
		 *  i--> 
		 *  i从前向后遍历
		 *  j从末尾往前,同时将最小值推到i位置
		 */
		//1.i从头到尾递增
		for(int i=0;i<a.length;i++){
			//flag用来记录是否进行位置交换,默认为false不发生位置交换-->用于减少循环次数
			boolean flag = false;
			//2.j从后往前递减,直到j=i,跳出循环
			for (int j=a.length-1;j>i;j--){
				//相邻两者,后面的值比前面的值比后面的大,交换两者的位置
				if(a[j-1]>a[j]){
					int t = a[j-1];
					a[j-1] = a[j];
					a[j] = t;
					//发生位置交换,改变flag的值
					flag = true;
				}
			}
			//3.判断是否发生位置交换,没有交换,跳出循环
			if(!flag){
				break;
			}
			System.out.println(Arrays.toString(a));
		}
		
	}
}










