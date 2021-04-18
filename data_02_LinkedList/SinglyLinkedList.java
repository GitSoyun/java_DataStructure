package data_02_LinkedList;

import data_00_interface.List;

public class SinglyLinkedList<E> implements List<E> {
	
	private SinglyNode<E> head; // ����� ���ۺκ�
	private SinglyNode<E> tail; // ����� ���κ�
	private int size; // ������ ����(=����� ����� ����)
	
	// ������: ��ü�� ������ �� ������ �־�� �� �ʱ�ȭ ��
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// Ư�� ��ġ�� ������ �߰�, ����, �˻��� ���� ����� ���ۺκк��� �ش� ��ġ���� ã�� method
	private SinglyNode<E> search(int index){
		
		// �ش� ��ġ�� �ùٸ� ������ �ƴ� ���
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // ���ܹ߻�
		}
		
		SinglyNode<E> x = head; // head ������ ����
		
		for(int i = 0; i < index; i++) {
			x = x.next; // x����� ���� ��带 x ������ ����
		}
		
		return x;
	}//search
	

}//class
