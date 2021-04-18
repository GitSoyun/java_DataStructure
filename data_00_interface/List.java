package data_00_interface;

/**
 * Java Collection Framework / List의 주요 method 정리
 * ArrayList, LinkedList
 * 
 * 개념 정리: https://blog.naver.com/pinktenshi/222309254165
 * @author 소윤
 * @param <E>
 */
public interface List<E> {
	
	/**
	 * List에서 특정 위치의 데이터 얻기
	 * @param index 검색할 위치
	 * @return 해당 데이터
	 */
	E get(int index);

	
	/**
	 * List에서 특정 위치의 데이터를 변경
	 * @param index 변경할 위치
	 * @param value 변경할 데이터 값
	 */
	void set(int index, E value);

	
	/**
	 * List에서 데이터 검색
	 * @param value 검색할 데이터
	 * @return 데이터가 존재할 경우 true, 존재하지 않을 경우 false
	 */
	boolean contains(Object value);
	
	
	/**
	 * List에서 데이터의 위치 검색
	 * @param value 검색할 데이터
	 * @return 데이터의 위치 index, 데이터가 존재하지 않을 경우 -1
	 */
	int indexOf(Object value);
	
	
	/**
	 * List의 총 데이터 개수 얻기
	 * @return 데이터의 개수
	 */
	int size();
	
	
	/**
	 * List에 데이터 추가
	 * @param value 추가할 데이터 값
	 * @return List는 기본적으로 중복데이터를 허용하지만,
	 * 중복데이터를 허용하지 않을 경우 중복데이터가 없다면 true, 중복데이터가 있다면 false
	 */
	boolean add(E value);
	
	
	/**
	 * List의 특정 위치에 데이터 추가
	 * @param index 추가하는 위치
	 * @param value 추가할 데이터 값
	 */
	void add(int index, E value);
	
	
	/**
	 * List의 특정 데이터 삭제, 
	 * 중복 데이터가 존재할 경우 먼저 검색된 것을 삭제
	 * @param value 삭제할 데이터 값
	 * @return 삭제 성공할 경우 true, 실패할 경우 false
	 */
	boolean remove(Object value);
	
	
	/**
	 * List의 특정 위치에 있는 데이터 삭제
	 * @param index 삭제할 위치
	 * @return 삭제한 데이터
	 */
	E remove(int index);
	

	/**
	 * List의 데이터가 비어있는지 확인
	 * @return 데이터가 존재하지 않을 경우 true, 존재할 경우 false
	 */
	boolean isEmpty();
	
	
	/**
	 * List의 모든 데이터 삭제
	 */
	public void clear();
	
}
