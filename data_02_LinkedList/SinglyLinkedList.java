package data_02_LinkedList;

import java.util.NoSuchElementException;

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
		
		return x; // �ش� ��ġ�� ���
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
		SinglyNode<E> prevNode = search(index-1);
		
		// �߰��� ��ġ�� ���
		SinglyNode<E> nextNode = prevNode.next;
		
		// �߰��� ���
		SinglyNode<E> newNode = new SinglyNode<E>(value);
		
		prevNode.next = null; // �߰��� ��ġ�� ���� ��尡 ����Ű�� ���� ��带 ������
		prevNode.next = newNode; // �� ��带 ���� ���� ����
		newNode.next = nextNode; // ������� ���� ��带 ����
		size++; // ������ ���� ����
	}//add
	
	
	// ==================== remove method: ������ ���� ====================
	
	// 1) remove(): �� �� ������ ����
	// 2) remove(int index): Ư�� ��ġ�� ������ ����
	// 3) remove(Object value): Ư�� ������ ����
	
	public E remove() {
		
		SinglyNode<E> headNode = head; // ������ �� �� ������
		
		// ����Ʈ�� �����Ͱ� ���� ���
		if(headNode == null) {
			throw new  NoSuchElementException(); // ���ܹ߻�
		}
		
		// ������ �����͸� ��ȯ�ϱ� ���� �ӽú���
		E element = headNode.data;
		
		// head����� ���� ��� ����
		SinglyNode<E> nextNode = head.next;
		
		// head����� ������ ��� ����
		head.data = null;
		head.next = null;
		
		// ���� �� ���ο� head ����
		head = nextNode;
		size--; 
		
		// �ϳ� ���� �����͸� ������ ��� tail�� �����Ƿ� ����
		if(size == 0) {
			tail = null;
		}
		
		return element; // ������ ������
	}//remove
	
	
	@Override
	public E remove(int index) {
		
		// �� �պκ��� �����͸� ������ ���
		if(index == 0) {
			return remove();
		}
		
		// �����͸� ������ ������ �ùٸ��� ���� ���
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // ���ܹ߻�
		}
		
		SinglyNode<E> prevNode = search(index-1); // ������ �������� ���� ���
		SinglyNode<E> removedNode = prevNode.next; // ������ ������ ���
		SinglyNode<E> nextNode = removedNode.next; // ������ �������� ���� ���
		
		// ������ �����͸� ��ȯ�ϱ� ���� �ӽú���
		E element = removedNode.data;
		
		// ���� ���� ������ �������� ���� ��带 ����
		prevNode.next = nextNode;
		
		// ������ ����
		removedNode.data = null;
		removedNode.next = null;
		size--;
		
		return element; // ������ ������
	}//remove
	
	
	@Override
	public boolean remove(Object value) {
		
		SinglyNode<E> prevNode = head; // ������ �������� ���� ���
		boolean hasValue = false; // ���� ���ɿ��θ� Ȯ���ϱ� ���� ����
		SinglyNode<E> x = head; // �����͸� �˻��ϱ� ���� ��� ����
		
		// head���� ������ ������ �˻�
		for(; x != null; x = x.next) {
			// x����� �����Ͱ� value�� ��ġ�� ���
			if(value.equals(x.data)) {
				hasValue = true; // ���� ���� Ȯ��
				break;
			}
			prevNode = x;
		}//for
		
		// ������ �����Ͱ� head�� ���
		if(x.equals(head)) {
			remove();
			return true; // ���� �Ϸ�
		
		// ������ �����Ͱ� �������� ���� ���
		} else if (!hasValue) {
			return false; // ���� ����
		
		// ������ �����Ͱ� head �ܿ� ������ ���
		} else {
			prevNode.next = x.next; // ���� ���� ������ �������� ���� ��带 ����
			
			// ������ ����
			x.data = null; 
			x.next = null;
			size--;
			
			return true; // ���� ����
		}//else
		
	}//remove
	
	
	// ==================== get method: ������ ��ȯ ====================
	
	@Override
	public E get(int index) {
		// search method�� Ư�� ��ġ�� ��带 ��ȯ�ϹǷ� �̸� �̿�
		return search(index).data;
	}//get
	
	
	// ==================== set method: ������ ��ü ====================
	
	@Override
	public void set(int index, E value) {
		// search method�� �̿��� ��ü�� ��带 ã��
		SinglyNode<E> replaceNode = search(index);
		replaceNode.data = null; // �ش� ����� ������ �ʱ�ȭ
		replaceNode.data = value; // �ش� ����� ������ ��ü
	}//set
	
	
	// ==================== indexOf method: �������� ��ġ ��ȯ ====================
	
	@Override
	public int indexOf(Object value) {
		int index = 0; // �ʱ�ȭ ��
		SinglyNode<E> x = head; // �����͸� �˻��ϱ� ���� ��� ����
		
		// head���� ������ �˻�
		for(; x != null; x = x.next) {
			// x����� �����Ͱ� value�� ��ġ�� ���
			if(value.equals(x.data)) {
				// �ش� �������� ��ġ ��ȯ
				return index;
			}
			index++; // index�� ��� ����
		}//for
		
		// �ش� �����Ͱ� �������� ���� ��� -1 ��ȯ
		return -1;
	}//indexOf
	
	
	// ==================== contains method: ������ ���� ���� ��ȯ ====================
	
	@Override
	public boolean contains(Object value) {
		// index�� 0 �̻��� ��� �ش� �����Ͱ� ������
		return indexOf(value) >= 0;
	}//contains
	
	
	// ==================== size method: ������ ���� ��ȯ ====================
	
	// ������ private���̱� ������ �ܺο��� ���� �Ұ� ==> size�� ���� ��ȯ���ִ� �޼ҵ� �ʿ�
	
	@Override
	public int size() {
		return size;
	}//size
	
	
	// ==================== isEmpty method: �����Ͱ� ����ִ��� Ȯ�� ====================
	
	@Override
	public boolean isEmpty() {
		return size == 0; // ������ ������ 0�� ��� true ��ȯ
	}//isEmpty
	
	
	// ==================== clear method: ��� ������ ���� ====================
	
	@Override
	public void clear() {
		SinglyNode<E> x = head;
		
		for(; x != null;) {
			SinglyNode<E> nextNode = x.next;
			x.data = null;
			x.next = null;
			x = nextNode;
		}
		
		head = tail = null;
		size = 0; // ������ ���� 0
	}//clear
	
}//class
