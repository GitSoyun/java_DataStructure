package data_02_LinkedList;

import data_00_interface.List;

public class DoublyLinkedList<E> implements List<E> {
	
	private DoublyNode<E> head; // 시작 노드
	private DoublyNode<E> tail; // 끝 노드
	private int size; // 데이터 개수
	
	
	// 생성자: 초기화 값
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// 특정 위치의 노드를 반환하는 method
	// 이중(양방향)의 경우 효율성을 위해 해당 위치가 head와 tail중 어디에 가까운지 판별 후 검색방향을 정함
	private DoublyNode<E> search(int index) {
		
		// 해당 위치가 올바른 범위가 아닐 경우
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // 예외발생
		}
		
		// 해당 위치가 tail에 가까울 경우
		if(index > size/2) {
			DoublyNode<E> x = tail; // tail노드부터 시작
			// 뒤부터 앞으로 검색
			for(int i = size-1; i > index; i--) {
				x = x.prev; // x노드의 이전 노드를 저장
			}
			return x; // 해당 위치의 노드
		
		// 해당 위치가 head 에 가까울 경우
		} else {
			DoublyNode<E> x = head; // head노드부터 시작
			// 앞부터 뒤로 검색
			for(int i = 0; i < index; i++) {
				x = x.next; // x 노드의 다음 노드를 저장
			}
			return x; // 해당 위치의 노드
		}//else
	}//search
	

}
