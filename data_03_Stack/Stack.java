package data_03_Stack;

import java.util.Arrays;
import java.util.EmptyStackException;

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
	
	
	// ==================== push method: ������ �߰� ====================
	
	@Override
	public E push(E item) {
		
		// ������ �� �� ���
		if(size == array.length) {
			resize(); // �����Ҵ�
		}
		
		array[size] = item; // ������ ��ġ�� ������(item) �߰�
		size++; // ���� ����
		
		return item;
	}//push
	
	
	// ==================== pop method: ������ ���� �� ��ȯ ====================
	
	@Override
	public E pop() {
		
		// ������ �����Ͱ� ���� ���
		if(size == 0) {
			throw new EmptyStackException(); // ���ܹ߻�
		}
		
		@SuppressWarnings("unchecked") // push �� ������ E type�� �ޱ� ������ E�� ĳ�����ص� �� �������� ����� ==> warnings ���� ����
		E data = (E)array[size-1]; // ������ �����͸� ��ȯ�ϱ� ���� ������ ��Ƶ�
		
		array[size-1] = null; // ������ ����
		size--; // ���� ����
		resize(); // �����Ҵ�
		
		return data;
	}//pop
	
	
	// ==================== peek method: ������ �������� ��ȯ ====================
	
	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		
		// ������ �����Ͱ� ���� ���
		if(size == 0) {
			throw new EmptyStackException(); // ���ܹ߻�
		}
		
		return (E)array[size-1];
	}//peek
	
	
	// ==================== search method: �������� ����� ��ġ ��ȯ  ====================
	
	@Override
	public int search(Object value) {
		
		// �� ������ �˻�
		for(int index = size-1; index >= 0; index--) {
			// value�� ��ġ�� ���
			if(array[index].equals(value)) {
				return size - index; // �� �������� ��ġ(�Ÿ�, 1���� ����) ��ȯ
			}
		}//for
		
		return -1; // ��ġ�ϴ� �����Ͱ� ���� ��� -1 ��ȯ
	}//search
	
	
	// ==================== size method: �������� ���� ��ȯ  ====================
	
	@Override
	public int size() {
		return size;
	}//size
	
	
	// ==================== clear method: ��� ������ ����  ====================
	
	@Override
	public void clear() {
		
		for(int i = 0; i < size; i++) {
			array[i] = null; // ��� ������ ����
		}
		size = 0; // ������ ���� 0
		resize(); // ���� ����ȭ
	}//clear
	
	
	// ==================== empty method: �����Ͱ� ����ִ��� Ȯ��  ====================
	
	@Override
	public boolean isEmpty() {
		return size == 0; // ������ ������ 0���� ��� true ��ȯ
	}//empty
	
}//class
