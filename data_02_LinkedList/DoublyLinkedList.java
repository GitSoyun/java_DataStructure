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
	
	
	// ==================== add method: 데이터 추가 ====================
	
	// 1) addFirst(E value): 맨 앞에 추가 ==> size가 0일 경우 호출해주기 위해 먼저 구현
	// 2) addLast(E value): 맨 뒤에 추가 ==> 내장객체 add()의 기본 값
	// 3) add(int index, E value): 특정 위치(중간)에 추가 ==> 내장객체에선 add(int index, E element) 오버로
	
	public void addFirst(E value) {
		DoublyNode<E> newNode = new DoublyNode<E>(value); // 추가할 데이터의 새 노드 생성
		newNode.next = head; // 새 노드의 다음 노드로 head노드 연결
		
		// 데이터가 존재했음을 확인 후 prev 지정 ==> null일 경우 prev 접근 시 NullPointerException 발생
		if(head != null) {
			head.prev = newNode;
		}
		
		head = newNode; // head노드를 새 노드로 변경
		size++; // 데이터 개수 증가
		
		// 다음 연결할 노드가 없는 경우 ==> 데이터가 없는 상태에서 처음 추가할 경우
		if(head.next == null) {
			tail = head;
		}
	}//addFirst
	
	
	@Override
	public boolean add(E value) { // 기본 내장객체
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		DoublyNode<E> newNode = new DoublyNode<E>(value); // 새 노드 생성
		
		// 데이터가 없는 상태에서 처음 추가할 경우
		if(size == 0) {
			addFirst(value);
			return;
		}
		
		tail.next = newNode; // tail노드의 다음 노드로 새로운 노드 연결
		newNode.prev = tail; // 새 노드의 이전 노드를 tail로 연결
		tail = newNode; // tail노드를 새 노드로 변경
		size++; // 개수 증가
	}//addLast
	
	
	@Override
	public void add(int index, E value) {
		
		// 데이터를 추가할 위치가 올바른 범위가 아닐 경우
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException(); // 예외발생
		}
		
		// 맨 앞부분에 데이터를 추가할 경우
		if(index == 0) {
			addFirst(value);
			return;
		}
		
		// 맨 뒷부분에 데이터를 추가할 경우
		if(index == size) {
			addLast(value);
			return;
		}
		
		// 추가할 위치의 이전 노드 
		DoublyNode<E> prevNode = search(index-1);
		
		// 추가할 위치의 기존 노드
		DoublyNode<E> nextNode = prevNode.next;
		
		// 추가할 노드
		DoublyNode<E> newNode = new DoublyNode<E>(value);
		
		// 연결 끊기
		prevNode.next = null;
		nextNode.prev = null;
		
		// 추가할 노드 연결
		prevNode.next = newNode;
		newNode.prev = prevNode;
		newNode.next = nextNode;
		nextNode.prev = newNode;

		size++; // 개수 증가
	}//add
	
	
	
	
	

}//class
