package data_03_Stack;

import java.util.Arrays;

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
	
	
	// ==================== resize method: 동적할당 ====================
	
	// 데이터의 개수(size)를 확인하고 크기에 맞게 할당 공간을 변경 ==> 최적화, 메모리 관리
	// 데이터 손상을 막기 위해 private 접근지정자 사용
	private void resize() {
		int arrayCapacity = array.length; // 현재 할당 크기 = 길이(개수)
		
		// 공간을 설정하지 않아 초기화 상태인 0인 경우 ==> 설정해둔 최소 공간만큼 배열 생성
		if(Arrays.equals(array, EMPTY_ARRAY)) { // Arrays.equals(a,b): 데이터 값을 비교하기 위한 메소드
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// 데이터의 개수가 공간을 꽉 채운 경우 ==> 공간을 2배 늘리기
		if(size == arrayCapacity) {
			int newCapacity = arrayCapacity * 2;
			// 공간을 늘린 새 배열에 기존의 데이터를 복사 ==> 뒤의 빈 공간은 null로 채워짐
			array = Arrays.copyOf(array, newCapacity); // Arrays.copyOf(복사할배열, 설정할공간)
			return;
		}
		
		// 데이터가 공간의 절반 미만인 경우 ==> 공간 줄이기
		if(size < (arrayCapacity/2)) {
			int newCapacity = arrayCapacity / 2;
			// 공간을 줄인 새 배열에 기존의 데이터를 복사 ==> 부동소수점 연산처럼 혹시 모를 예외발생 처리: max함수 사용
			array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
			return;
		}
		
	}//resize
	
	
	
}//class
