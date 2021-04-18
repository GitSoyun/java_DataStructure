package data_02_LinkedList;

import java.util.NoSuchElementException;

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
		
		return x; // 해당 위치의 노드
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
		SinglyNode<E> prevNode = search(index-1);
		
		// 추가할 위치의 노드
		SinglyNode<E> nextNode = prevNode.next;
		
		// 추가할 노드
		SinglyNode<E> newNode = new SinglyNode<E>(value);
		
		prevNode.next = null; // 추가할 위치의 이전 노드가 가리키는 다음 노드를 끊어줌
		prevNode.next = newNode; // 새 노드를 다음 노드로 연결
		newNode.next = nextNode; // 새노드의 다음 노드를 연결
		size++; // 데이터 개수 증가
	}//add
	
	
	// ==================== remove method: 데이터 삭제 ====================
	
	// 1) remove(): 맨 앞 데이터 삭제
	// 2) remove(int index): 특정 위치의 데이터 삭제
	// 3) remove(Object value): 특정 데이터 삭제
	
	public E remove() {
		
		SinglyNode<E> headNode = head; // 삭제할 맨 앞 데이터
		
		// 리스트에 데이터가 없을 경우
		if(headNode == null) {
			throw new  NoSuchElementException(); // 예외발생
		}
		
		// 삭제한 데이터를 반환하기 위한 임시변수
		E element = headNode.data;
		
		// head노드의 다음 노드 생성
		SinglyNode<E> nextNode = head.next;
		
		// head노드의 데이터 모두 삭제
		head.data = null;
		head.next = null;
		
		// 삭제 후 새로운 head 설정
		head = nextNode;
		size--; 
		
		// 하나 뿐인 데이터를 삭제한 경우 tail이 없으므로 삭제
		if(size == 0) {
			tail = null;
		}
		
		return element; // 삭제한 데이터
	}//remove
	
	
	@Override
	public E remove(int index) {
		
		// 맨 앞부분의 데이터를 삭제할 경우
		if(index == 0) {
			return remove();
		}
		
		// 데이터를 삭제할 범위가 올바르지 않은 경우
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // 예외발생
		}
		
		SinglyNode<E> prevNode = search(index-1); // 삭제할 데이터의 이전 노드
		SinglyNode<E> removedNode = prevNode.next; // 삭제할 데이터 노드
		SinglyNode<E> nextNode = removedNode.next; // 삭제할 데이터의 다음 노드
		
		// 삭제할 데이터를 반환하기 위한 임시변수
		E element = removedNode.data;
		
		// 이전 노드와 삭제할 데이터의 다음 노드를 연결
		prevNode.next = nextNode;
		
		// 데이터 삭제
		removedNode.data = null;
		removedNode.next = null;
		size--;
		
		return element; // 삭제한 데이터
	}//remove
	
	
	@Override
	public boolean remove(Object value) {
		
		SinglyNode<E> prevNode = head; // 삭제할 데이터의 이전 노드
		boolean hasValue = false; // 삭제 가능여부를 확인하기 위한 변수
		SinglyNode<E> x = head; // 데이터를 검색하기 위한 노드 변수
		
		// head부터 삭제할 데이터 검색
		for(; x != null; x = x.next) {
			// x노드의 데이터가 value와 일치할 경우
			if(value.equals(x.data)) {
				hasValue = true; // 삭제 가능 확인
				break;
			}
			prevNode = x;
		}//for
		
		// 삭제할 데이터가 head일 경우
		if(x.equals(head)) {
			remove();
			return true; // 삭제 완료
		
		// 삭제할 데이터가 존재하지 않을 경우
		} else if (!hasValue) {
			return false; // 삭제 실패
		
		// 삭제할 데이터가 head 외에 존재할 경우
		} else {
			prevNode.next = x.next; // 이전 노드와 삭제할 데이터의 다음 노드를 연결
			
			// 데이터 삭제
			x.data = null; 
			x.next = null;
			size--;
			
			return true; // 삭제 성공
		}//else
		
	}//remove
	
	
	// ==================== get method: 데이터 반환 ====================
	
	@Override
	public E get(int index) {
		// search method가 특정 위치의 노드를 반환하므로 이를 이용
		return search(index).data;
	}//get
	
	
	// ==================== set method: 데이터 교체 ====================
	
	@Override
	public void set(int index, E value) {
		// search method를 이용해 교체할 노드를 찾음
		SinglyNode<E> replaceNode = search(index);
		replaceNode.data = null; // 해당 노드의 데이터 초기화
		replaceNode.data = value; // 해당 노드의 데이터 교체
	}//set
	
	
	// ==================== indexOf method: 데이터의 위치 반환 ====================
	
	@Override
	public int indexOf(Object value) {
		int index = 0; // 초기화 값
		SinglyNode<E> x = head; // 데이터를 검색하기 위한 노드 변수
		
		// head부터 데이터 검색
		for(; x != null; x = x.next) {
			// x노드의 데이터가 value와 일치할 경우
			if(value.equals(x.data)) {
				// 해당 데이터의 위치 반환
				return index;
			}
			index++; // index가 계속 증가
		}//for
		
		// 해당 데이터가 존재하지 않을 경우 -1 반환
		return -1;
	}//indexOf
	
	
	// ==================== contains method: 데이터 존재 여부 반환 ====================
	
	@Override
	public boolean contains(Object value) {
		// index가 0 이상일 경우 해당 데이터가 존재함
		return indexOf(value) >= 0;
	}//contains
	
	
	// ==================== size method: 데이터 개수 반환 ====================
	
	// 변수가 private형이기 때문에 외부에서 접근 불가 ==> size의 값을 반환해주는 메소드 필요
	
	@Override
	public int size() {
		return size;
	}//size
	
	
	// ==================== isEmpty method: 데이터가 비어있는지 확인 ====================
	
	@Override
	public boolean isEmpty() {
		return size == 0; // 데이터 개수가 0일 경우 true 반환
	}//isEmpty
	
	
	// ==================== clear method: 모든 데이터 삭제 ====================
	
	@Override
	public void clear() {
		SinglyNode<E> x = head;
		
		for(; x != null;) {
			SinglyNode<E> nextNode = x.next;
			x.data = null;
			x.next = null;
			x = nextNode;
		}
		
		head = tail = null;
		size = 0; // 데이터 개수 0
	}//clear
	
}//class
