package data_02_LinkedList;

public class DoublyNode<E> {
	
	E data;
	DoublyNode<E> next; // 다음 연결할 노드를 가리키는 reference 변수
	DoublyNode<E> prev; // 이전 연결할 노드를 가리키는 reference 변수
	
	
	// 생성자
	DoublyNode(E data){
		this.data = data;
		this.next = null;
		this.prev = null;
	}

}
