package data_04_Queue;

public class SinglyNode<E> {
	
	E data;
	SinglyNode<E> next; // ���� ������ ��带 ����Ű�� reference ����
	
	// ������
	SinglyNode(E data){
		this.data = data;
		this.next = null;
	}
}
