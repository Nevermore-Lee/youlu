package cn.tedu.youlu.presenter.impl;

import java.util.List;

import cn.tedu.youlu.entity.Contact;
import cn.tedu.youlu.model.CommonCallback;
import cn.tedu.youlu.model.IContactModel;
import cn.tedu.youlu.model.impl.ContactModelImpl;
import cn.tedu.youlu.presenter.IContactPresenter;
import cn.tedu.youlu.view.IContactView;

public class ContactPresenterImpl implements IContactPresenter{
	private IContactModel model;
	private IContactView view;
	
	public ContactPresenterImpl(IContactView view) {
		model = new ContactModelImpl();
		this.view = view;
	}
	
	@Override
	public void loadAllContacts() {
		model.findAll(new CommonCallback() {
			public void onDataLoaded(Object data) {
				List<Contact> cs = (List<Contact>) data;
				//把cs设置给view 然后显示界面
				view.setData(cs);
				view.showData();
			}
		});
	}
	
}




