package com.jerome.base;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 
 * ClassName:EasyBaseAdapter <br>
 * Function: 更加简洁的Adapter基类。 <br>
 * 
 * @author Jerome Song
 * @version @param <T>
 * @Date 2013 2013-7-21 上午8:00:28
 * 
 * @see @param <T>
 */
public abstract class EasyBaseAdapter<T> extends BaseAdapter {

	protected List<T> mDataSet;

	protected final Context mContext;

	protected final LayoutInflater mInflater;

	public EasyBaseAdapter(Context context, List<T> dataset) {
		mDataSet = dataset;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mDataSet.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataSet.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView,
			ViewGroup parent);
}
