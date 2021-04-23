package data_03_Stack;

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
	
}//class
