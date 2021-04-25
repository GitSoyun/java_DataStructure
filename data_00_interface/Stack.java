package data_00_interface;

/**
 * Java Collection Framework / Stack�� �ֿ� method
 * 
 * ���� ����: https://blog.naver.com/pinktenshi/222309254165
 * @param <E>
 */
public interface Stack<E> {
	
	/**
	 * Stack�� �� ���� ������ �߰�
	 * @param item �߰��� ������
	 * @return �߰��� ������
	 */
	E push(E item);
	
	
	/**
	 * Stack�� �� ���� �ִ� �����͸� ���� ���� �� ��ȯ
	 * @return ���� ������
	 */
	E pop();
	
	
	/**
	 * Stack�� �� ���� �ִ� �����͸� �������� �ʰ� ��ȯ
	 * @return ������ �� �� ������
	 */
	E peek();
	
	
	/**
	 * Ư�� �����Ͱ� Stack�� �� ������ �� ��°�� ��ġ�ϴ��� ��ȯ
	 * �ߺ� �����Ͱ� ������ ��� ���� �˻��� ���� ��ȯ 
	 * @param value ��ġ�� ã�� ������
	 * @return �� �������� ��ġ(�Ÿ�, 1���� ����), �����Ͱ� ���� ��� -1
	 */
	int search(Object value);
	
	
	/**
	 * Stack ���� �� item ���� ���
	 * @return �������� ����
	 */
	int size();
	
	
	/**
	 * Stack�� ��� ������ ����
	 */
	void clear();
	
	
	/**
	 * Stack�� �����Ͱ� ����ִ��� Ȯ��
	 * @return �����Ͱ� �������� ���� ��� true, ������ ��� false ��ȯ
	 */
	boolean isEmpty();
	
}
