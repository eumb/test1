package com.example.test1;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayNews extends BaseAdapter {
	static final String TAG = "DisplayNews";
	private Activity activity;
	private ArrayList<Post> data;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;

	ViewHolder holder;

	DisplayNews(Activity a, String stire ,String titlu, String poza ) {

		activity = a;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());

		
	}

	@Override
	public int getCount() {
		return data.toArray().length;

	}

	@Override
	public Object getItem(int position) {

		return position;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	public static class ViewHolder {
		public TextView label;
		public TextView desc;
		public ImageView image;
		
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.stire, null);
			holder = new ViewHolder();
			holder.label = (TextView) vi.findViewById(R.id.title);
			holder.desc = (TextView) vi.findViewById(R.id.details);
			holder.image = (ImageView) vi.findViewById(R.id.thumb);

			vi.setTag(holder);
			
			
		} else
			holder = (ViewHolder) vi.getTag();

	//	holder.desc.setText();
		
		holder.label.setText(data.get(position).getTitle());
		imageLoader.DisplayImage((data.get(position).getThumbnail()), holder.image);


	return vi;
	}

}

