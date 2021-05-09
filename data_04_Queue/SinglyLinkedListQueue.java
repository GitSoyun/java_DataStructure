package data_04_Queue;

import data_00_interface.Queue;

public class SinglyLinkedListQueue<E> implements Queue<E> {
	
	private SinglyNode<E> head; // 시작 노드
	private SinglyNode<E> tail; // 끝 노드
	private int size; // 데이터 개수(=연결된 노드의 개수)
	
	// 생성자: 객체가 생성될 때 가지고 있어야 할 초기화 값
	public SinglyLinkedListQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	

}
