package data_00_interface;

/**
 * Java Collection Framework / Queue�� �ֿ� method
 * 
 * @param <E>
 */
public interface Queue<E> {
	
	/**
	 * Queue�� �� �ڿ� ������ �߰�
	 * @param item �߰��� ������(���)
	 * @return �߰� ���� �� true ��ȯ
	 */
	boolean offer(E item);
	
	
	/**
	 * Queue�� �� �� �����͸� ���� �� ��ȯ
	 * @return ������ ������
	 */
	E poll();
	
	
	/**
	 * Queue�� �� �� �����͸� �������� �ʰ� ��ȯ
	 * @return �� �� ������
	 */
	E peek();

}
