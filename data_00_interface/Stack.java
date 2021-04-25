package data_00_interface;

/**
 * Java Collection Framework / Stack의 주요 method
 * 
 * 개념 정리: https://blog.naver.com/pinktenshi/222309254165
 * @param <E>
 */
public interface Stack<E> {
	
	/**
	 * Stack의 맨 위에 데이터 추가
	 * @param item 추가할 데이터
	 * @return 추가된 데이터
	 */
	E push(E item);
	
	
	/**
	 * Stack의 맨 위에 있는 데이터를 꺼내 삭제 후 반환
	 * @return 꺼낸 데이터
	 */
	E pop();
	
	
	/**
	 * Stack의 맨 위에 있는 데이터를 삭제하지 않고 반환
	 * @return 스택의 맨 위 데이터
	 */
	E peek();
	
	
	/**
	 * 특정 데이터가 Stack의 맨 위부터 몇 번째에 위치하는지 반환
	 * 중복 데이터가 존재할 경우 먼저 검색된 것을 반환 
	 * @param value 위치를 찾을 데이터
	 * @return 맨 위부터의 위치(거리, 1부터 시작), 데이터가 없을 경우 -1
	 */
	int search(Object value);
	
	
	/**
	 * Stack 안의 총 item 개수 얻기
	 * @return 데이터의 개수
	 */
	int size();
	
	
	/**
	 * Stack의 모든 데이터 삭제
	 */
	void clear();
	
	
	/**
	 * Stack의 데이터가 비어있는지 확인
	 * @return 데이터가 존재하지 않을 경우 true, 존재할 경우 false 반환
	 */
	boolean isEmpty();
	
}
