package cn.aezo.designpattern.c20_iterator.t0_generic;

/**
 * 基于链表(每个元素包含两部分, 一部分用于存储本身的值, 另一部分用于存储此链表的下一个元素的引用)实现容器
 */
public class MyLinkedList {
	Node head = null; // 第一个元素(如果集合中元素个数大于1, 则一直会有下一个元素的引用)
	Node tail = null; // 最后一个元素(此元素的下一个元素引用永远为空)
	int size = 0;

	public void add(Object o) {
		Node n = new Node(o, null);

		// 添加元素, 判断此元素是否是第一个元素
		if(head == null) {
			head = n;
			tail = n;
		}

		tail.setNext(n);
		tail = n;
		size ++;
	}
	
	public int size() {
		return size;
	}

	/**
	 * 链表的数据结构
	 */
	public static class Node {
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

	public static void main(String[] args) {
		MyLinkedList myLinkedList = new MyLinkedList();
		myLinkedList.add("hello...");
		System.out.println(myLinkedList.size());
	}
}
