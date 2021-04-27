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
	
	
	// ==================== resize method: �����Ҵ� ====================
	
	// �������� ����(size)�� Ȯ���ϰ� ũ�⿡ �°� �Ҵ� ������ ���� ==> ����ȭ, �޸� ����
	// ������ �ջ��� ���� ���� private ���������� ���
	
	private void resize(int newCapacity) { // ������ �� ũ�⸦ �Ű������� ����
		
		int arrayCapacity = array.length; // ���� �Ҵ� ũ��
		Object[] newArray = new Object[newCapacity]; // ũ�⸦ ������ �� �迭
		
		// i: �� �迭�� index, j: ���� �迭�� index
		// front+1�� ����: ���� ť�� �����ϸ� �迭�� ó���� �� ������ ���� �� �� index�� �����͸� ����ش�.
		for(int i = 1, j = front+1; i <= size; i++, j++) {
			// �� �迭�� ���� �迭 �� ����
			// front�� rear���� �۰ų� front+1�� �迭�� ��� ���, ũ��� ���� �������� index ���� ���ؾ� ��Ȯ�� front ���� ������ �� �ִ�.
			newArray[i] = array[j % arrayCapacity];
		}
		
		this.array = null; // ���� �迭 �ʱ�ȭ
		this.array = newArray; // �迭�� �� �迭�� ����
		
		front = 0; // ���� index ����
		rear = size; // �� index ����
	}//resize
	
	
	

}//class