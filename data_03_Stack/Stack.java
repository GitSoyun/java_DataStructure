package data_03_Stack;

import java.util.Arrays;
import java.util.EmptyStackException;

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
	
	
	// ==================== push method: 데이터 추가 ====================
	
	@Override
	public E push(E item) {
		
		// 공간이 꽉 찬 경우
		if(size == array.length) {
			resize(); // 동적할당
		}
		
		array[size] = item; // 마지막 위치에 데이터(item) 추가
		size++; // 개수 증가
		
		return item;
	}//push
	
	
	// ==================== pop method: 데이터 삭제 후 반환 ====================
	
	@Override
	public E pop() {
		
		// 삭제할 데이터가 없을 경우
		if(size == 0) {
			throw new EmptyStackException(); // 예외발생
		}
		
		@SuppressWarnings("unchecked") // push 시 무조건 E type만 받기 때문에 E로 캐스팅해도 형 안정성이 보장됨 ==> warnings 무시 가능
		E data = (E)array[size-1]; // 삭제할 데이터를 반환하기 위해 변수에 담아둠
		
		array[size-1] = null; // 데이터 삭제
		size--; // 개수 감소
		resize(); // 동적할당
		
		return data;
	}//pop
	
	
	// ==================== peek method: 데이터 삭제없이 반환 ====================
	
	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		
		// 삭제할 데이터가 없을 경우
		if(size == 0) {
			throw new EmptyStackException(); // 예외발생
		}
		
		return (E)array[size-1];
	}//peek
	
	
	// ==================== search method: 데이터의 상대적 위치 반환  ====================
	
	@Override
	public int search(Object value) {
		
		// 맨 위부터 검색
		for(int index = size-1; index >= 0; index--) {
			// value와 일치할 경우
			if(array[index].equals(value)) {
				return size - index; // 맨 위부터의 위치(거리, 1부터 시작) 반환
			}
		}//for
		
		return -1; // 일치하는 데이터가 없을 경우 -1 반환
	}//search
	
	
	// ==================== size method: 데이터의 개수 반환  ====================
	
	@Override
	public int size() {
		return size;
	}//size
	
	
	// ==================== clear method: 모든 데이터 삭제  ====================
	
	@Override
	public void clear() {
		
		for(int i = 0; i < size; i++) {
			array[i] = null; // 모든 데이터 삭제
		}
		size = 0; // 데이터 개수 0
		resize(); // 공간 최적화
	}//clear
	
	
	// ==================== empty method: 데이터가 비어있는지 확인  ====================
	
	@Override
	public boolean isEmpty() {
		return size == 0; // 데이터 개수가 0개일 경우 true 반환
	}//empty
	
}//class
