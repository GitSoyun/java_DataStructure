package data_02_LinkedList;

import data_00_interface.List;

public class SinglyLinkedList<E> implements List<E> {
	
	private SinglyNode<E> head; // ���� ���
	private SinglyNode<E> tail; // �� ���
	private int size; // ������ ����(=����� ����� ����)
	
	// ������: ��ü�� ������ �� ������ �־�� �� �ʱ�ȭ ��
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// Ư�� ��ġ�� ������ �߰�, ����, �˻��� ���� ���� ������ �ش� ��ġ���� ã�� method
	private SinglyNode<E> search(int index){
		
		// �ش� ��ġ�� �ùٸ� ������ �ƴ� ���
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // ���ܹ߻�
		}
		
		SinglyNode<E> x = head; // head ������ ����
		
		for(int i = 0; i < index; i++) {
			x = x.next; // x����� ���� ��带 x������ ����
		}
		
		return x;
	}//search
	
	
	// ==================== add method: ������ �߰� ====================
	
	// 1) addFirst(E value): ó�� ��ġ�� �߰� ==> size�� 0�� ��� ȣ�����ֱ� ���� ���� ����
	// 2) addLast(E value): ������ ��ġ�� �߰� ==> ���尴ü add()�� �⺻ ��
	// 3) add(int index, E value): Ư�� ��ġ(�߰�)�� �߰� ==> ���尴ü���� add(int index, E element) �����ε�
	
	public void addFirst(E value) {
		SinglyNode<E> newNode = new SinglyNode<E>(value); // �߰��� �������� �� ��� ����
		newNode.next = head; // �� ����� ���� ���� head��� ����
		head = newNode; // head��带 �� ���� ����
		size++; // ������ ���� ����
		
		// ���� ������ ��尡 ���� ��� ==> �����Ͱ� ���� ���¿��� ó�� �߰��� ���
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
		SinglyNode<E> newNode = new SinglyNode<E>(value); // �� ��� ����
		
		// �����Ͱ� ���� ���¿��� ó�� �߰��� ���
		if(size == 0) {
			addFirst(value);
			return;
		}
		
		tail.next = newNode; // tail����� ���� ���� ���ο� ��� ����
		tail = newNode; // tail��带 �� ���� ����
		size++; // ������ ���� ����
	}// addLast
	
	
	@Override
	public void add(int index, E value) {
		
		// �����͸� �߰��� ��ġ�� �ùٸ� ������ �ƴ� ���
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException(); // ���ܹ߻�
		}
		
		// �� �պκп� �����͸� �߰��� ���
		if(index == 0) {
			addFirst(value);
			return;
		}
		
		// �� �޺κп� �����͸� �߰��� ���
		if(index == size) {
			addLast(value);
			return;
		}
		
		// �߰��� ��ġ�� ���� ���
		SinglyNode<E> prev_Node = search(index-1);
		
		// �߰��� ��ġ�� ���
		SinglyNode<E> next_Node = prev_Node.next;
		
		// �߰��� ���
		SinglyNode<E> newNode = new SinglyNode<E>(value);
		
		prev_Node.next = null; // �߰��� ��ġ�� ���� ��尡 ����Ű�� ���� ��带 ������
		prev_Node.next = newNode; // �� ��带 ���� ���� ����
		newNode.next = next_Node; // ������� ���� ��带 ����
		size++; // ������ ���� ����
	}//add
	
	
	

}//class
