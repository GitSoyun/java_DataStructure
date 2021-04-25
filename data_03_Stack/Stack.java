package data_03_Stack;

import java.util.Arrays;

public class Stack<E> implements data_00_interface.Stack<E> {
	
	private static final int DEFAULT_CAPACITY = 10; // �ּ� �Ҵ� ũ��(����)
	private static final Object[] EMPTY_ARRAY = {}; // ũ�Ⱑ 0�� �� �迭
	
	private Object[] array; // �����͸� ���� �迭
	private int size; // �������� ����
	
	// ������1: �ʱ� ���� �Ҵ� X ==> ��ü�� ������ ����
	public Stack() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// ������2: �ʱ� ���� �Ҵ� O ==> �������� ������ ���� ������ ��� �̸� ���� �Ҵ�
	public Stack(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}
	
	
	// ==================== resize method: �����Ҵ� ====================
	
	// �������� ����(size)�� Ȯ���ϰ� ũ�⿡ �°� �Ҵ� ������ ���� ==> ����ȭ, �޸� ����
	// ������ �ջ��� ���� ���� private ���������� ���
	private void resize() {
		int arrayCapacity = array.length; // ���� �Ҵ� ũ�� = ����(����)
		
		// ������ �������� �ʾ� �ʱ�ȭ ������ 0�� ��� ==> �����ص� �ּ� ������ŭ �迭 ����
		if(Arrays.equals(array, EMPTY_ARRAY)) { // Arrays.equals(a,b): ������ ���� ���ϱ� ���� �޼ҵ�
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// �������� ������ ������ �� ä�� ��� ==> ������ 2�� �ø���
		if(size == arrayCapacity) {
			int newCapacity = arrayCapacity * 2;
			// ������ �ø� �� �迭�� ������ �����͸� ���� ==> ���� �� ������ null�� ä����
			array = Arrays.copyOf(array, newCapacity); // Arrays.copyOf(�����ҹ迭, �����Ұ���)
			return;
		}
		
		// �����Ͱ� ������ ���� �̸��� ��� ==> ���� ���̱�
		if(size < (arrayCapacity/2)) {
			int newCapacity = arrayCapacity / 2;
			// ������ ���� �� �迭�� ������ �����͸� ���� ==> �ε��Ҽ��� ����ó�� Ȥ�� �� ���ܹ߻� ó��: max�Լ� ���
			array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
			return;
		}
		
	}//resize
	
	
	
}//class
