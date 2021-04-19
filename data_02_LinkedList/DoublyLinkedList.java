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
	
	
	// ==================== add method: ������ �߰� ====================
	
	// 1) addFirst(E value): �� �տ� �߰� ==> size�� 0�� ��� ȣ�����ֱ� ���� ���� ����
	// 2) addLast(E value): �� �ڿ� �߰� ==> ���尴ü add()�� �⺻ ��
	// 3) add(int index, E value): Ư�� ��ġ(�߰�)�� �߰� ==> ���尴ü���� add(int index, E element) ������
	
	public void addFirst(E value) {
		DoublyNode<E> newNode = new DoublyNode<E>(value); // �߰��� �������� �� ��� ����
		newNode.next = head; // �� ����� ���� ���� head��� ����
		
		// �����Ͱ� ���������� Ȯ�� �� prev ���� ==> null�� ��� prev ���� �� NullPointerException �߻�
		if(head != null) {
			head.prev = newNode;
		}
		
		head = newNode; // head��带 �� ���� ����
		size++; // ������ ���� ����
		
		// ���� ������ ��尡 ���� ��� ==> �����Ͱ� ���� ���¿��� ó�� �߰��� ���
		if(head.next == null) {
			tail = head;
		}
	}//addFirst
	
	
	@Override
	public boolean add(E value) { // �⺻ ���尴ü
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		DoublyNode<E> newNode = new DoublyNode<E>(value); // �� ��� ����
		
		// �����Ͱ� ���� ���¿��� ó�� �߰��� ���
		if(size == 0) {
			addFirst(value);
			return;
		}
		
		tail.next = newNode; // tail����� ���� ���� ���ο� ��� ����
		newNode.prev = tail; // �� ����� ���� ��带 tail�� ����
		tail = newNode; // tail��带 �� ���� ����
		size++; // ���� ����
	}//addLast
	
	
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
		DoublyNode<E> prevNode = search(index-1);
		
		// �߰��� ��ġ�� ���� ���
		DoublyNode<E> nextNode = prevNode.next;
		
		// �߰��� ���
		DoublyNode<E> newNode = new DoublyNode<E>(value);
		
		// ���� ����
		prevNode.next = null;
		nextNode.prev = null;
		
		// �߰��� ��� ����
		prevNode.next = newNode;
		newNode.prev = prevNode;
		newNode.next = nextNode;
		nextNode.prev = newNode;

		size++; // ���� ����
	}//add
	
	
	
	
	

}//class