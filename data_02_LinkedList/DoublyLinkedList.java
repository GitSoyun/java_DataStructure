package data_02_LinkedList;

import java.util.NoSuchElementException;

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
	
	
	// ==================== remove method: 데이터 삭제 ====================
	
	// 1) remove(): 맨 앞 데이터 삭제 ==> head
	// 2) remove(int index): 특정 위치의 데이터 삭제
	// 3) remove(Object value): 특정 데이터 삭제
	
	public E remove() {
		
		DoublyNode<E> headNode = head; // 삭제할 맨 앞 데이터
		
		// 데이터가 하나도 없을 경우
		if(headNode == null) {
			throw new NoSuchElementException(); // 예외발생
		}
		
		// 삭제한 데이터를 반환하기 위한 임시변수
		E element = headNode.data;
		
		// head노드의 다음 노드 생성
		DoublyNode<E> nextNode = head.next;
		
		// head노드의 데이터 모두 삭제
		head.data = null;
		head.next = null;
		
		// head의 다음 노드가 존재했음을 확인 후 prev 설정 ==> null일 경우 prev 접근 시 NullPointerException 발생
		if(nextNode != null) {
			nextNode.prev = null;
		}
		
		// 새로운 head 설정
		head = nextNode;
		size--; // 개수 감소
		
		// 하나 뿐인 데이터를 삭제한 경우 tail이 없으므로 null로 변환
		if(size == 0) {
			tail = null;
		}
		
		return element; // 삭제한 데이터
	}//remove
	
	
	@Override
	public E remove(int index) {
		
		// 데이터를 삭제할 범위가 올바르지 않은 경우
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // 예외발생
		}
		
		// 맨 앞부분의 데이터를 삭제할 경우
		if(index == 0) {
			E element = head.data;
			remove();
			return element;
		}
		
		DoublyNode<E> prevNode = search(index-1); // 삭제할 노드의 이전 노드
		DoublyNode<E> removedNode = prevNode.next; // 삭제할 노드
		DoublyNode<E> nextNode = removedNode.next; // 삭제할 노드의 다음 노드
		
		// 삭제할 데이터를 반환하기 위한 임시변수
		E element = removedNode.data;
		
		// 데이터 삭제
		prevNode.next = null;
		removedNode.next = null;
		removedNode.prev = null;
		removedNode.data = null;
		
		// 삭제한 노드의 다음 노드가 있는 경우
		if(nextNode != null) {
			nextNode.prev = null;
			nextNode.prev = prevNode;
			prevNode.next = nextNode;
		
		// 삭제한 노드의 다음 노드가 없는 경우 ==> 맨 뒤를 삭제한 경우
		} else {
			tail = prevNode;
		}
		size--; // 개수 감소
		
		return element; // 삭제한 데이터
	}//remove
	
	
	@Override
	public boolean remove(Object value) {
		
		DoublyNode<E> prevNode = head; // 삭제할 데이터의 이전 노드
		boolean hasValue = false; // 삭제 가능여부를 확인하기 위한 변수
		DoublyNode<E> x = head; // 삭제할 노드 변수
		
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
		} else if(!hasValue) {
			return false; // 삭제 실패
			
		// 삭제할 데이터가 head 외에 존재할 경우
		} else {
			DoublyNode<E> nextNode = x.next;
			
			//데이터 삭제
			prevNode.next = null;
			x.data = null;
			x.next = null;
			x.prev = null;
			
			// 삭제한 노드의 다음 노드가 있는 경우
			if(nextNode != null) {
				nextNode.prev = null;
				nextNode.prev = prevNode;
				prevNode.next = nextNode;
			
			// 삭제한 노드의 다음 노드가 없는 경우 ==> 맨 뒤를 삭제한 경우
			}else {
				tail = prevNode;
			}
			size--; // 개수 감소
			
			return true; // 삭제 성공
		}//else
		
	}//remove
	
	
	

}//class
