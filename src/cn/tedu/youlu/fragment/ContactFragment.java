package cn.tedu.youlu.fragment;

import java.util.List;

import cn.tedu.youlu.R;
import cn.tedu.youlu.adapter.ContactAdapter;
import cn.tedu.youlu.entity.Contact;
import cn.tedu.youlu.presenter.IContactPresenter;
import cn.tedu.youlu.presenter.impl.ContactPresenterImpl;
import cn.tedu.youlu.view.IContactView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ContactFragment extends Fragment implements IContactView{
	private List<Contact> contacts;
	private IContactPresenter presenter;
	private ContactAdapter adapter;
	private GridView gvContact; 
	
	public ContactFragment() {
		this.presenter = new ContactPresenterImpl(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.	layout.fragment_contact, null);
		setViews(view);
		//调用presenter的方法  执行加载联系人的流程
		presenter.loadAllContacts();
		return view;
	}

	private void setViews(View view) {
		this.gvContact = (GridView)view.findViewById(R.id.gvContact);
	}

	@Override
	public void setData(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public void showData() {
		//设置自定义Adapter
		adapter = new ContactAdapter(contacts, getActivity());
		gvContact.setAdapter(adapter);
	}
}



