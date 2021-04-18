package data_02_LinkedList;

public class SinglyNode<E> {

	E data;
	SinglyNode<E> next; // 다음 연결할 노드를 가리키는 reference 변수
	
	// 생성자
	SinglyNode(E data){
		this.data = data;
		this.next = null;
	}
	
}
