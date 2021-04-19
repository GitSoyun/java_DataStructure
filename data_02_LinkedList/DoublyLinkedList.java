package data_02_LinkedList;

import data_00_interface.List;

public class DoublyLinkedList<E> implements List<E> {
	
	private DoublyNode<E> head; // ���� ���
	private DoublyNode<E> tail; // �� ���
	private int size; // ������ ����
	
	
	// ������: �ʱ�ȭ ��
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// Ư�� ��ġ�� ��带 ��ȯ�ϴ� method
	// ����(�����)�� ��� ȿ������ ���� �ش� ��ġ�� head�� tail�� ��� ������� �Ǻ� �� �˻������� ����
	private DoublyNode<E> search(int index) {
		
		// �ش� ��ġ�� �ùٸ� ������ �ƴ� ���
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // ���ܹ߻�
		}
		
		// �ش� ��ġ�� tail�� ����� ���
		if(index > size/2) {
			DoublyNode<E> x = tail; // tail������ ����
			// �ں��� ������ �˻�
			for(int i = size-1; i > index; i--) {
				x = x.prev; // x����� ���� ��带 ����
			}
			return x; // �ش� ��ġ�� ���
		
		// �ش� ��ġ�� head �� ����� ���
		} else {
			DoublyNode<E> x = head; // head������ ����
			// �պ��� �ڷ� �˻�
			for(int i = 0; i < index; i++) {
				x = x.next; // x ����� ���� ��带 ����
			}
			return x; // �ش� ��ġ�� ���
		}//else
	}//search
	

}
