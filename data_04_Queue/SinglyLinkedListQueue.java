package data_04_Queue;

import data_00_interface.Queue;

public class SinglyLinkedListQueue<E> implements Queue<E> {
	
	private SinglyNode<E> head; // ���� ���
	private SinglyNode<E> tail; // �� ���
	private int size; // ������ ����(=����� ����� ����)
	
	// ������: ��ü�� ������ �� ������ �־�� �� �ʱ�ȭ ��
	public SinglyLinkedListQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	

}
