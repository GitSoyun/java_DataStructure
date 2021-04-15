package data_01_List;

import java.util.Arrays;

public class ArrayList<E> implements ListMethod<E> {
	
	// 상수(constant): 고정된 값
	private static final int DEFAULT_CAPACITY = 10; // 최소 할당 크기(공간)
	private static final Object[] EMPTY_ARRAY = {}; // 크기가 0인 배열
	
	private int size; // 데이터의 개수 (할당 크기(공간)와는 다름 주의)
	
	Object[] array; // 데이터를 넣을 배열
	
	
	// 초기 공간 할당 X 생성자
	// 미리 공간을 할당하지 않고 객체만 생성한 상태 ==> ArrayList<Integer> list = new ArrayList<>();
	public ArrayList() {
		this.array = EMPTY_ARRAY; // 공간 초기화
		this.size = 0;
	}
	
	// 초기 공간 할당 O 생성자
	// 데이터 개수를 예측할 수 있어 미리 공간을 할당한 상태 ==> ArrayList<Integer> list = new ArrayList<>(100);
	public ArrayList(int capacity) {
		this.array = new Object[capacity];
		this.size = 0; // 개수 초기화
	}
	
	
	// ==================== resize method: 동적할당 ====================
	// 데이터의 개수(size)를 확인하고 크기에 맞게 할당 공간을 변경 ==> 최적화, 메모리 관리
	// 데이터 손상을 막기 위해 private 접근지정자 사용
	private void resize() {
		int array_capacity = array.length; // 할당 크기 = 길이(개수)
		
		// 공간이 0인 경우 ==> 설정해둔 최소 공간만큼 배열 생성
		if(Arrays.equals(array, EMPTY_ARRAY)) { // Arrays.equals(a,b): 데이터 값을 비교하기 위한 메소드
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// 데이터의 개수가 공간을 꽉 채운 경우 ==> 공간을 2배 늘리기
		if(size == array_capacity) {
			int new_capacity = array_capacity * 2;
			// 공간을 늘린 새 배열에 기존의 데이터를 복사 ==> 뒤의 빈 공간은 null로 채워짐
			array = Arrays.copyOf(array, new_capacity); // Arrays.copyOf(복사할배열, 공간)
			return;
		}
		
		// 데이터가 공간의 절반을 못채운 경우 ==> 공간 줄이기
		if(size < (array_capacity/2)) {
			int new_capacity = array_capacity / 2;
			// 공간을 줄인 새 배열에 기존의 데이터를 복사 ==> 부동소수점 연산처럼 혹시 모를 예외발생 처리: max함수 사용
			array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY)); // Math.max(a,b): 둘중 큰 값을 반환
			return;
		}
		
	}//resize
	
	
	// ==================== add method: 데이터 추가 ====================
	// 1) addLast(E value): 마지막 위치에 추가 ==> 내장객체에선 add()
	// 2) addFirst(E value): 처음 위치에 추가 ==> 내장객체에선 없지만 index를 0으로 줘서 구현 가능
	// 3) add(int index, E value): 특정 위치(중간)에 추가 ==> 내장객체에선 add(int index, E element) 오버로딩
	
	@Override // 상속관계의 클래스에 있는 같은 이름의 메소드를 자식클래스에서 재정의
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	// add에서 value가 넘어옴
	public void addLast(E value) {
		
		// 데이터를 추가하기 전에 공간이 꽉 찼는지 확인
		if(size == array.length) {
			resize(); // 공간 재할당
		}
		
		// resize한 새 배열의 마지막 index에 넘어온 value 추가
		array[size] = value; // index는 0부터 시작하므로 마지막 index는 size와 같음
		size++; // 데이터 개수 1 증가
	}//addLast
	
	
	@Override
	public void add(int index, E value) {
		
		// 추가할 위치가 데이터의 개수를 벗어나는 경우(List는 빈 공간 없이 연속적이므로)
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException(); // 예외발생
		}
		
		// 추가할 위치가 마지막 위치인 경우
		if(index == size) {
			addLast(value);
			
		} else { // 특정 위치(중간)에 추가
			// 데이터를 추가하기 전에 공간이 꽉 찼는지 확인
			if(size == array.length) {
				resize(); // 공간 재할당
			}
			
			// 추가할 위치의 뒤에 있는 데이터들을 한 칸씩 뒤로 밀기
			for(int i = size; i > index; i--) {
				array[i] = array[i-1];
			}
			// 해당 위치에 value 추가
			array[index] = value; 
			size++; // 데이터 개수 1 증가
		}//else
	}//add
	
	
	public void addFirst(E value) {
		add(0, value); // 기존 내장객체 사용, 추가할 위치 뒤로 한 칸씩 자동으로 밀림
	}//addFirst
	
	
	// get, set, indexOf, contains, remove, size, isEmpty, clear method 추가 예정
	

}
