package cn.tedu.youlu.model.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import cn.tedu.youlu.app.YouLuApplication;
import cn.tedu.youlu.entity.Contact;
import cn.tedu.youlu.model.CommonCallback;
import cn.tedu.youlu.model.IContactModel;

public class ContactModelImpl implements IContactModel{

	@Override
	public void findAll(final CommonCallback callback) {
		new AsyncTask<String, String, List<Contact>>() {
			//�ڹ����߳��в�ѯ������ϵ��
			protected List<Contact> doInBackground(String... params) {
				List<Contact> cs = loadAllContacts();
				return cs;
			}
			protected void onPostExecute(List<Contact> result) {
				callback.onDataLoaded(result);
			}
		}.execute();
	}
	
	
	/**
	 * ��ѯ������ϵ��
	 *  �Ȳ�contacts�� ��ȡ�����˵�id
	 *  ����ÿ���˵�id��ѯdata���ȡ����˵���������
	 */
	private List<Contact> loadAllContacts() {
		ContentResolver r = YouLuApplication.getApp().getContentResolver();
		// contactUri:  content://com.android.contacts/contacts
		Uri contactUri = Contacts.CONTENT_URI;
		String[] columns = {
				Contacts._ID,				//0
				Contacts.PHOTO_ID	//1
		};
		Cursor c1 = r.query(contactUri , columns, null, null, null);
		List<Contact> contacts = new ArrayList<Contact>();
		while(c1.moveToNext()){
			Contact c = new Contact();
			int id = c1.getInt(0);
			int photoId = c1.getInt(1);
			c.setId(id);
			c.setPhotoId(photoId);
			//dataUri: content://com.android.contacts/data
			Uri dataUri = Data.CONTENT_URI;
			String[] projection = {
					Data.RAW_CONTACT_ID,		//0
					Data.MIMETYPE,					//1
					Data.DATA1							//2
			};
			Cursor c2=r.query(dataUri, projection, Data.RAW_CONTACT_ID+"=?", new String[]{id+""}, null);
			while(c2.moveToNext()){
				String mm = c2.getString(1);
				String data = c2.getString(2);
				if(mm.equals(Email.CONTENT_ITEM_TYPE)){
					//data������������˵�email����
					c.setEmail(data);
				}else if(mm.equals(Phone.CONTENT_ITEM_TYPE)){
					//data����������˵�phone����
					c.setPhone(data);
				}else if(mm.equals("vnd.android.cursor.item/postal-address_v2")){
					//data����������˵�address����
					c.setAddress(data);
				}else if(mm.equals("vnd.android.cursor.item/name")){
					//data����������˵�name����
					c.setName(data);
				}
			}
			c2.close();
			contacts.add(c);
		}
		c1.close();
		return contacts;
	}


}



