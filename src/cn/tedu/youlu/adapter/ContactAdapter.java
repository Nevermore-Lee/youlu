package cn.tedu.youlu.adapter;

import java.util.List;

import cn.tedu.youlu.R;
import cn.tedu.youlu.entity.Contact;
import cn.tedu.youlu.util.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter{
	private List<Contact> contacts;
	private Context context;
	private LayoutInflater inflater;
	private Bitmap defaultPhoto;
	
	public ContactAdapter(List<Contact> contacts, Context context) {
		this.contacts = contacts;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		defaultPhoto = BitmapFactory.decodeResource(context.getResources(), R.drawable.img01g_15);
	}

	@Override
	public int getCount() {
		return contacts.size();
	}

	@Override
	public Contact getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_gv_contact, null);
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		//设置内容
		Contact c = getItem(position);
		holder.tvName.setText(c.getName());
		//设置头像
		Bitmap bitmap=BitmapUtils.getPhoto(context, c.getPhotoId());
		if(bitmap!=null){
			holder.ivPhoto.setImageBitmap(bitmap);
		}else{
			holder.ivPhoto.setImageBitmap(defaultPhoto);
		}
		return convertView;
	}
	
	class ViewHolder{
		ImageView ivPhoto;
		TextView tvName;
	}
	
}
