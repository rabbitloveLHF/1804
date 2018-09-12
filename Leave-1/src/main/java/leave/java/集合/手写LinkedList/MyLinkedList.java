package leave.java.集合.手写LinkedList;
public class MyLinkedList {
	private Node head;
	private Node tail;
	private int size;
	
	class Node{
		Object value;
		Node prev;
		Node next;
	}
	
	public void add(Object obj){
		//1.创建一个Node对象
		Node node = new Node();
		//2.给Node的属性赋值  value prev next
		//3.给List的属性赋值 head tail size
		node.value=obj;
		//2.1链表中没有数据,这是第一个添加的数据
		if(size==0){
			node.prev = node;
			node.next = node;
			head = node;
			tail = node;
			size++;
			return;
		}
		//2.2链表中有数据
		
		//4.node和tail互相引用
		node.prev = tail;
		tail.next = node;
		
		//5.node和head互相引用
		node.next = head;
		head.prev = node;
		
		//6.node作为了新的尾部节点
		tail = node;
		size++;
	}
	
	public Object get(int i){
		//1.如果是头和尾
		if(i==0){
			return head.value;
		}else if(i==size-1){
			return tail.value;
		}
		//2.不是头尾,从头开始或者从尾开始,一个个挨着找
		Node node ;//用于保存找到的节点,从头开始找
		
		//通过j来控制循环次数,从头开始遍历
		//当j=i的时,说明就找到了要找的位置,结束
		//如果不是头尾
		//[n1,n2,n3,n4]
		
		if(i<size/2){
			//如果是前面一半,从前往后找
			node = head;
			for(int j=1;j<=i;j++){
				node = node.next;//从前往后
			}
		}else{
			//如果是后面一半,从后往前找
			node = tail;
			for(int j=size-2;j>=i;j--){
				node = node.prev;//从后往前
			}
		}
		return node.value;
	}
	
	public int size(){
		return size;
	}
}
