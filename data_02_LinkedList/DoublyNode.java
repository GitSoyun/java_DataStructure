package data_02_LinkedList;

public class DoublyNode<E> {
	
	E data;
	DoublyNode<E> next; // ���� ������ ��带 ����Ű�� reference ����
	DoublyNode<E> prev; // ���� ������ ��带 ����Ű�� reference ����
	
	
	// ������
	DoublyNode(E data){
		this.data = data;
		this.next = null;
		this.prev = null;
	}

}
