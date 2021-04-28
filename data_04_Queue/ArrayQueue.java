package data_04_Queue;

import data_00_interface.Queue;

public class ArrayQueue<E> implements Queue<E> {
	
	private static final int DEFAULT_CAPACITY = 64; // 최소 할당 공간(크기)
	
	private Object[] array; // 데이터를 넣을 배열
	private int size; // 데이터의 개수
	
	// Queue의 경우 선입선출(FIFO)이기 때문에 index=0 부터 시작이 아닌, 원형 큐를 생각하면 이해가 쉽다.
	// 직선 배열이라면 앞부분을 채워주려고 하나씩 앞으로 당길 경우 불필요한 연산이 많아지기 때문.
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
	
	
	// ==================== offer method: 맨 뒤에 데이터 추가 ====================
	
	// 마지막 index에 도달했을 경우(배열이 꽉 차있을 경우) 주의
	
	@Override
	public boolean offer(E item) {
		
		// 공간이 꽉 찬 경우
		if((rear+1) % array.length == front) {
			resize(array.length * 2); // 크기 2배로 늘려줌
		}
		
		rear = (rear+1) % array.length; // rear을 다음 위치로 변경
		
		array[rear] = item; // 맨 뒤에 데이터 추가
		size++; // 개수 증가
		
		return true; // 데이터 추가 성공 시 true 반환
	}//offer
	
	
	// ==================== poll method: 맨 앞 데이터 삭제 후 반환 ====================
	
	// add, remove, element: 큐도 가능, 예외발생 O
	// offer, poll, peek: 예외발생 X
	
	@Override
	public E poll() {
		
		// 삭제할 데이터가 없을 경우
		if(size == 0) {
			return null; // null 반환 ==> 예외발생X 차이점 확인하기
		}
		
		front = (front+1) % array.length; // front를 다음 위치로 한 칸 변경
		
		@SuppressWarnings("unchecked") // 형 안정성이 보장되므로 Warnings 무시
		E item = (E)array[front]; // 반환할 데이터 저장
		
		array[front] = null; // 맨 앞의 데이터 삭제
		size--; // 개수 감소
		
		// 배열 길이가 최소 할당 공간보다 크고 개수가 1/4 미만일 경우 
		if(array.length > DEFAULT_CAPACITY && size < (array.length/4)) {
			// 최소용적보다 작게 줄이지는 않음
			resize(Math.max(DEFAULT_CAPACITY, array.length/2));
		}
		
		return item; // 삭제한 데이터 반환
	}//poll
	
	

}//class
