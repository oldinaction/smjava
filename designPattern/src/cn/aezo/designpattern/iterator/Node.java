package cn.aezo.designpattern.iterator;

/**
 * 链表的数据结构
 */
public class Node {
	private Object data; // 值本身
	private Node next; // 下一个元素的引用

	public Node(Object data, Node next) {
		super();
		this.data = data;
		this.next = next;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
}
