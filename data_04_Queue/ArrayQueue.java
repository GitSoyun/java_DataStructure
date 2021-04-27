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
	
	
	// ==================== resize method: 동적할당 ====================
	
	// 데이터의 개수(size)를 확인하고 크기에 맞게 할당 공간을 변경 ==> 최적화, 메모리 관리
	// 데이터 손상을 막기 위해 private 접근지정자 사용
	
	private void resize(int newCapacity) { // 변경할 새 크기를 매개변수로 받음
		
		int arrayCapacity = array.length; // 현재 할당 크기
		Object[] newArray = new Object[newCapacity]; // 크기를 변경할 새 배열
		
		// i: 새 배열의 index, j: 기존 배열의 index
		// front+1의 이유: 원형 큐로 생각하면 배열의 처음과 끝 구분을 위해 맨 앞 index는 데이터를 비워준다.
		for(int i = 1, j = front+1; i <= size; i++, j++) {
			// 새 배열에 기존 배열 값 복사
			// front가 rear보다 작거나 front+1이 배열을 벗어날 경우, 크기로 나눈 나머지의 index 값을 구해야 정확한 front 값을 가져올 수 있다.
			newArray[i] = array[j % arrayCapacity];
		}
		
		this.array = null; // 기존 배열 초기화
		this.array = newArray; // 배열에 새 배열을 복사
		
		front = 0; // 시작 index 설정
		rear = size; // 끝 index 설정
	}//resize
	
	
	

}//class
