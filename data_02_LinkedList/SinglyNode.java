package data_02_LinkedList;

public class SinglyNode<E> {

	E data;
	SinglyNode<E> next; // ���� ������ ��带 ����Ű�� reference ����
	
	// ������
	SinglyNode(E data){
		this.data = data;
		this.next = null;
	}
	
}
