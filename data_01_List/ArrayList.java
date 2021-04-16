package data_01_List;

import java.util.Arrays;

public class ArrayList<E> implements ListMethod<E> {
	
	// ���(constant): ������ ��
	private static final int DEFAULT_CAPACITY = 10; // �ּ� �Ҵ� ũ��(����)
	private static final Object[] EMPTY_ARRAY = {}; // ũ�Ⱑ 0�� �迭
	
	private int size; // �������� ���� (�Ҵ� ũ��(����)�ʹ� �ٸ� ����)
	
	Object[] array; // �����͸� ���� �迭
	
	
	// �ʱ� ���� �Ҵ� X ������
	// �̸� ������ �Ҵ����� �ʰ� ��ü�� ������ ���� ==> ArrayList<Integer> list = new ArrayList<>();
	public ArrayList() {
		this.array = EMPTY_ARRAY; // ���� �ʱ�ȭ
		this.size = 0;
	}
	
	// �ʱ� ���� �Ҵ� O ������
	// ������ ������ ������ �� �־� �̸� ������ �Ҵ��� ���� ==> ArrayList<Integer> list = new ArrayList<>(100);
	public ArrayList(int capacity) {
		this.array = new Object[capacity];
		this.size = 0; // ���� �ʱ�ȭ
	}
	
	
	// ==================== resize method: �����Ҵ� ====================
	
	// �������� ����(size)�� Ȯ���ϰ� ũ�⿡ �°� �Ҵ� ������ ���� ==> ����ȭ, �޸� ����
	// ������ �ջ��� ���� ���� private ���������� ���
	private void resize() {
		int array_capacity = array.length; // �Ҵ� ũ�� = ����(����)
		
		// ������ 0�� ��� ==> �����ص� �ּ� ������ŭ �迭 ����
		if(Arrays.equals(array, EMPTY_ARRAY)) { // Arrays.equals(a,b): ������ ���� ���ϱ� ���� �޼ҵ�
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// �������� ������ ������ �� ä�� ��� ==> ������ 2�� �ø���
		if(size == array_capacity) {
			int new_capacity = array_capacity * 2;
			// ������ �ø� �� �迭�� ������ �����͸� ���� ==> ���� �� ������ null�� ä����
			array = Arrays.copyOf(array, new_capacity); // Arrays.copyOf(�����ҹ迭, ����)
			return;
		}
		
		// �����Ͱ� ������ ������ ��ä�� ��� ==> ���� ���̱�
		if(size < (array_capacity/2)) {
			int new_capacity = array_capacity / 2;
			// ������ ���� �� �迭�� ������ �����͸� ���� ==> �ε��Ҽ��� ����ó�� Ȥ�� �� ���ܹ߻� ó��: max�Լ� ���
			array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY)); // Math.max(a,b): ���� ū ���� ��ȯ
			return;
		}
		
	}//resize
	
	
	// ==================== add method: ������ �߰� ====================
	
	// 1) addLast(E value): ������ ��ġ�� �߰� ==> ���尴ü���� add()
	// 2) addFirst(E value): ó�� ��ġ�� �߰� ==> ���尴ü���� ������ index�� 0���� �༭ ���� ����
	// 3) add(int index, E value): Ư�� ��ġ(�߰�)�� �߰� ==> ���尴ü���� add(int index, E element) �����ε�
	
	@Override // ��Ӱ����� Ŭ������ �ִ� ���� �̸��� �޼ҵ带 �ڽ�Ŭ�������� ������
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	// add���� value�� �Ѿ��
	public void addLast(E value) {
		
		// �����͸� �߰��ϱ� ���� ������ �� á���� Ȯ��
		if(size == array.length) {
			resize(); // ���� ���Ҵ�
		}
		
		// resize�� �� �迭�� ������ index�� �Ѿ�� value �߰�
		array[size] = value; // index�� 0���� �����ϹǷ� ������ index�� size�� ����
		size++; // ������ ���� 1 ����
	}//addLast
	
	
	@Override
	public void add(int index, E value) {
		
		// �߰��� ��ġ�� �������� ������ ����� ���(List�� �� ���� ���� �������̹Ƿ�)
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException(); // ���ܹ߻�
		}
		
		// �߰��� ��ġ�� ������ ��ġ�� ���
		if(index == size) {
			addLast(value);
			
		} else { // Ư�� ��ġ(�߰�)�� �߰�
			// �����͸� �߰��ϱ� ���� ������ �� á���� Ȯ��
			if(size == array.length) {
				resize(); // ���� ���Ҵ�
			}
			
			// �߰��� ��ġ�� �ڿ� �ִ� �����͵��� �� ĭ�� �ڷ� �б�
			for(int i = size; i > index; i--) {
				array[i] = array[i-1];
			}
			// �ش� ��ġ�� value �߰�
			array[index] = value; 
			size++; // ������ ���� 1 ����
		}//else
	}//add
	
	
	public void addFirst(E value) {
		add(0, value); // ���� ���尴ü ���, �߰��� ��ġ �ڷ� �� ĭ�� �ڵ����� �и�
	}//addFirst
	
	
	// ==================== get method: ������ ��ȯ ====================
	
	@SuppressWarnings("unchecked") // add �� ������ E�� �ޱ� ������ E�� ĳ�����ص� �� �������� ����ǹǷ� warnings ����
	@Override
	public E get(int index) {
		
		// �����͸� ã�� ��ġ�� index�� ������ ������ ��� ���
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // ���ܹ߻�
		}
		
		// Object => E Ÿ������ ĳ���� ==> ���� ������ Ÿ������ ��ȯ
		return (E)array[index];
	}//get
	
	
	// ==================== set method: ������ ��ü ====================
	
	public void set(int index, E value) {
		
		// �����͸� ��ü�� ��ġ�� index�� ������ ������ ��� ���
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(); // ���ܹ߻�
			
		} else {
			// �ش� ��ġ�� ������ �� ��ü
			array[index] = value;
		}//else
	}//set
	
	
	// ==================== indexOf method: �������� ��ġ ��ȯ ====================
	
	// 1) indexOf(Object value): ó�� index�� 0���� �˻�
	// 2) lastIndexOf(Object value): ������ index���� �˻� ==> �˻��� �����Ͱ� �ڿ� ���� ������ ����� ���
	
	@Override
	public int indexOf(Object value) {
		int i = 0;
		// array[0]���� ������ ������� �˻� ==> �ߺ� �����Ͱ� ���� ��� ���� �˻��Ǵ� index ��ȯ
		for(i = 0; i < size; i++) {
			// ã�� ������ ���� ��ġ�� ��� ==> ��ü �� �� �ݵ�� equals() ���
			if(array[i].equals(value)) { 
				return i; // �ش� index ��ȯ
			}
		}//for
		
		// �ش� �����Ͱ� ���� ��� -1 ��ȯ
		return -1;
	}//indexOf
	
	
	public int lastIndexOf(Object value) {
		// array[size-1]���� �Ųٷ� �˻�
		for(int i = size-1; i >= 0; i--) { // index�� 0���� �����ϹǷ� ������ index�� size-1
			if(array[i].equals(value)) {
				return i;
			}
		}//for
		
		// �ش� �����Ͱ� ���� ��� -1 ��ȯ
		return -1;
	}//lastIndexOf
	
	
	// ==================== contains method: ������ ���� ���� ��ȯ ====================
	
	@Override
	public boolean contains(Object value) {
		// index�� 0 �̻��� ��� �ش� �����Ͱ� ������
		if(indexOf(value) >= 0) {
			return true;
		
		} else {
			return false;
		}
	}//contains
	
	
	
	// remove, size, isEmpty, clear method �߰� ����
	

}
