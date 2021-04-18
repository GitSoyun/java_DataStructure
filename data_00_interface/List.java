package data_00_interface;

/**
 * Java Collection Framework / List�� �ֿ� method ����
 * ArrayList, LinkedList
 * 
 * ���� ����: https://blog.naver.com/pinktenshi/222309254165
 * @author ����
 * @param <E>
 */
public interface List<E> {
	
	/**
	 * List���� Ư�� ��ġ�� ������ ���
	 * @param index �˻��� ��ġ
	 * @return �ش� ������
	 */
	E get(int index);

	
	/**
	 * List���� Ư�� ��ġ�� �����͸� ����
	 * @param index ������ ��ġ
	 * @param value ������ ������ ��
	 */
	void set(int index, E value);

	
	/**
	 * List���� ������ �˻�
	 * @param value �˻��� ������
	 * @return �����Ͱ� ������ ��� true, �������� ���� ��� false
	 */
	boolean contains(Object value);
	
	
	/**
	 * List���� �������� ��ġ �˻�
	 * @param value �˻��� ������
	 * @return �������� ��ġ index, �����Ͱ� �������� ���� ��� -1
	 */
	int indexOf(Object value);
	
	
	/**
	 * List�� �� ������ ���� ���
	 * @return �������� ����
	 */
	int size();
	
	
	/**
	 * List�� ������ �߰�
	 * @param value �߰��� ������ ��
	 * @return List�� �⺻������ �ߺ������͸� ���������,
	 * �ߺ������͸� ������� ���� ��� �ߺ������Ͱ� ���ٸ� true, �ߺ������Ͱ� �ִٸ� false
	 */
	boolean add(E value);
	
	
	/**
	 * List�� Ư�� ��ġ�� ������ �߰�
	 * @param index �߰��ϴ� ��ġ
	 * @param value �߰��� ������ ��
	 */
	void add(int index, E value);
	
	
	/**
	 * List�� Ư�� ������ ����, 
	 * �ߺ� �����Ͱ� ������ ��� ���� �˻��� ���� ����
	 * @param value ������ ������ ��
	 * @return ���� ������ ��� true, ������ ��� false
	 */
	boolean remove(Object value);
	
	
	/**
	 * List�� Ư�� ��ġ�� �ִ� ������ ����
	 * @param index ������ ��ġ
	 * @return ������ ������
	 */
	E remove(int index);
	

	/**
	 * List�� �����Ͱ� ����ִ��� Ȯ��
	 * @return �����Ͱ� �������� ���� ��� true, ������ ��� false
	 */
	boolean isEmpty();
	
	
	/**
	 * List�� ��� ������ ����
	 */
	public void clear();
	
}
