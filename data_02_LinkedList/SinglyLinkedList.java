package data_02_LinkedList;

import data_00_interface.List;

public class SinglyLinkedList<E> implements List<E> {
	
	private SinglyNode<E> head; // 노드의 시작부분
	private SinglyNode<E> tail; // 노드의 끝부분
	private int size; // 데이터 개수(=연결된 노드의 개수)
	
	// 생성자: 객체가 생성될 때 가지고 있어야 할 초기화 값
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// 특정 위치에 데이터 추가, 삭제, 검색을 위해 노드의 시작부분부터 해당 위치까지 찾는 method
	private SinglyNode<E> search(int index){
		
		// 해당 위치가 올바른 범위가 아닐 경우
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // 예외발생
		}
		
		SinglyNode<E> x = head; // head 노드부터 시작
		
		for(int i = 0; i < index; i++) {
			x = x.next; // x노드의 다음 노드를 x 변수에 저장
		}
		
		return x;
	}//search
	

}//class
