package leave.java.排序.选择排序;

import java.util.Arrays;

/**
 * 选择排序
 * @author Administrator
 */
public class SortByChoice {
	public static void main(String[] args) {
		int[] a  = {1,8,5,38,59,20,11,58,21,66,79,64};
		sort(a);
	}

	public static void sort(int[] a) {
		/*	  j-->i+1	
		 * [1,8,5,38,59,20,11,58,21,66,79,64]
		 *  m
		 *  i-->
		 */
		//1.遍历整个数组
		for(int i=0;i<a.length;i++){
			//2.筛选出本次循环最小值,并将它放到最开头,即i位置-->效果:保证i位置是最小值
			//2.1 假设i位置已经是最小值,用m存储本次循环最小值的位置
			
			int m = i;
			
			//2.2遍历数组,从[i+1,a.length)中查找最小值的位置
			for(int j=i+1;j<a.length;j++){
				if(a[j]<a[m]){
					m = j;
				}
			}
			//2.3交换m和i位置的值
			int t = a[m];
			a[m] = a[i];
			a[i] = t;
			System.out.println(Arrays.toString(a));
			
		}
	}
}
