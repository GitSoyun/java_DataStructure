package data_02_LinkedList;

import data_00_interface.List;

public class SinglyLinkedList<E> implements List<E> {
	
	private SinglyNode<E> head; // 시작 노드
	private SinglyNode<E> tail; // 끝 노드
	private int size; // 데이터 개수(=연결된 노드의 개수)
	
	// 생성자: 객체가 생성될 때 가지고 있어야 할 초기화 값
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// 특정 위치에 데이터 추가, 삭제, 검색을 위해 시작 노드부터 해당 위치까지 찾는 method
	private SinglyNode<E> search(int index){
		
		// 해당 위치가 올바른 범위가 아닐 경우
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // 예외발생
		}
		
		SinglyNode<E> x = head; // head 노드부터 시작
		
		for(int i = 0; i < index; i++) {
			x = x.next; // x노드의 다음 노드를 x변수에 저장
		}
		
		return x;
	}//search
	
	
	// ==================== add method: 데이터 추가 ====================
	
	// 1) addFirst(E value): 처음 위치에 추가 ==> size가 0일 경우 호출해주기 위해 먼저 구현
	// 2) addLast(E value): 마지막 위치에 추가 ==> 내장객체 add()의 기본 값
	// 3) add(int index, E value): 특정 위치(중간)에 추가 ==> 내장객체에선 add(int index, E element) 오버로딩
	
	public void addFirst(E value) {
		SinglyNode<E> newNode = new SinglyNode<E>(value); // 추가할 데이터의 새 노드 생성
		newNode.next = head; // 새 노드의 다음 노드로 head노드 연결
		head = newNode; // head노드를 새 노드로 변경
		size++; // 데이터 개수 증가
		
		// 다음 연결할 노드가 없는 경우 ==> 데이터가 없는 상태에서 처음 추가할 경우
		if(head.next == null) {
			tail = head;
		}
	}//addFirst
	
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		SinglyNode<E> newNode = new SinglyNode<E>(value); // 새 노드 생성
		
		// 데이터가 없는 상태에서 처음 추가할 경우
		if(size == 0) {
			addFirst(value);
			return;
		}
		
		tail.next = newNode; // tail노드의 다음 노드로 새로운 노드 연결
		tail = newNode; // tail노드를 새 노드로 변경
		size++; // 데이터 개수 증가
	}// addLast
	
	
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
		SinglyNode<E> prev_Node = search(index-1);
		
		// 추가할 위치의 노드
		SinglyNode<E> next_Node = prev_Node.next;
		
		// 추가할 노드
		SinglyNode<E> newNode = new SinglyNode<E>(value);
		
		prev_Node.next = null; // 추가할 위치의 이전 노드가 가리키는 다음 노드를 끊어줌
		prev_Node.next = newNode; // 새 노드를 다음 노드로 연결
		newNode.next = next_Node; // 새노드의 다음 노드를 연결
		size++; // 데이터 개수 증가
	}//add
	
	
	

}//class
