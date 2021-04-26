package data_00_interface;

/**
 * Java Collection Framework / Queue의 주요 method
 * 
 * @param <E>
 */
public interface Queue<E> {
	
	/**
	 * Queue의 맨 뒤에 데이터 추가
	 * @param item 추가할 데이터(요소)
	 * @return 추가 성공 시 true 반환
	 */
	boolean offer(E item);
	
	
	/**
	 * Queue의 맨 앞 데이터를 삭제 후 반환
	 * @return 삭제된 데이터
	 */
	E poll();
	
	
	/**
	 * Queue의 맨 앞 데이터를 삭제하지 않고 반환
	 * @return 맨 앞 데이터
	 */
	E peek();

}
