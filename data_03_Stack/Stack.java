package data_03_Stack;

public class Stack<E> implements data_00_interface.Stack<E> {
	
	private static final int DEFAULT_CAPACITY = 10; // 최소 할당 크기(공간)
	private static final Object[] EMPTY_ARRAY = {}; // 크기가 0인 빈 배열
	
	private Object[] array; // 데이터를 담을 배열
	private int size; // 데이터의 개수
	
	// 생성자1: 초기 공간 할당 X ==> 객체만 생성한 상태
	public Stack() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// 생성자2: 초기 공간 할당 O ==> 데이터의 개수가 예측 가능한 경우 미리 공간 할당
	public Stack(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}
	
}//class
