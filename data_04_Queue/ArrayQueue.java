package data_04_Queue;

import data_00_interface.Queue;

public class ArrayQueue<E> implements Queue<E> {
	
	private static final int DEFAULT_CAPACITY = 64; // �ּ� �Ҵ� ����(ũ��)
	
	private Object[] array; // �����͸� ���� �迭
	private int size; // �������� ����
	
	private int front; // ���� ��ġ(index)�� ����Ű�� ����
	private int rear; // ������ ��ġ(index)�� ����Ű�� ����
	
	
	// �ʱ� ���� �Ҵ� X ������
	// �̸� ������ �Ҵ����� �ʰ� ��ü�� ������ ���� ==> ArrayQueue<Integer> queue = new ArrayQueue<>();
	public ArrayQueue() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	// �ʱ� ���� �Ҵ� O ������
	// ������ ������ ������ �� �־� �̸� ������ �Ҵ��� ���� ==> ArrayQueue<Integer> queue = new ArrayQueue<>(100);
	public ArrayQueue(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	
	

}//class
