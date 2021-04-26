package data_04_Queue;

import data_00_interface.Queue;

public class ArrayQueue<E> implements Queue<E> {
	
	private static final int DEFAULT_CAPACITY = 64; // 최소 할당 공간(크기)
	
	private Object[] array; // 데이터를 넣을 배열
	private int size; // 데이터의 개수
	
	private int front; // 시작 위치(index)를 가리키는 변수
	private int rear; // 마지막 위치(index)를 가리키는 변수
	
	
	// 초기 공간 할당 X 생성자
	// 미리 공간을 할당하지 않고 객체만 생성한 상태 ==> ArrayQueue<Integer> queue = new ArrayQueue<>();
	public ArrayQueue() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	// 초기 공간 할당 O 생성자
	// 데이터 개수를 예측할 수 있어 미리 공간을 할당한 상태 ==> ArrayQueue<Integer> queue = new ArrayQueue<>(100);
	public ArrayQueue(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	
	

}//class
