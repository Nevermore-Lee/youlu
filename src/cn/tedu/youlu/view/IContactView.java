package cn.tedu.youlu.view;

import java.util.List;

import cn.tedu.youlu.entity.Contact;

/**
 * ��ϵ�˽���view��ӿ�
 */
public interface IContactView {
	/**
	 * ��������Դ
	 * @param contacts  ��ϵ���б�
	 */
	public void setData(List<Contact> contacts);
	
	/**
	 * �ڽ�������ʾ������ϵ��
	 * ���ø÷���ǰ��������������Դ(setData())
	 */
	public void showData();
}



