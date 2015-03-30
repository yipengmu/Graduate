package com.laomu.justgraduate.modules.login.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.laomu.justgraduate.R;
import com.laomu.justgraduate.application.JGApplication;
import com.laomu.justgraduate.common.datatype.Province;
import com.laomu.justgraduate.common.datatype.School;
import com.laomu.justgraduate.common.datatype.Univ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yipengmu on 2014/12/4.
 */
public class SigninListviewAdapter<T> extends BaseAdapter {
    /**
     * 1.provice 2. universe 3.school
     */
    private int mAdapterType = 1;


    List<T> mData = new ArrayList<T>();

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(JGApplication.appContext).inflate(R.layout.jg_signin_listview_item_layout, null);
        }
        ViewHolder holder = null;

        if (convertView.getTag() == null) {
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_left_value);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        updateCellView(position, holder);
        return convertView;
    }

    private void updateCellView(int position, ViewHolder holder) {
        T data = mData.get(position);

        if (mAdapterType == 1) {
            Province province = (Province) data;
            holder.textView.setText((position + 1) + " :" + (province == null ? province : province.name));
        } else if (mAdapterType == 2) {
            Univ univ = (Univ) data;
            holder.textView.setText((position + 1) + " :" + (univ == null ? univ : univ.name));
        } else if (mAdapterType == 3) {
            School school = (School) data;
            holder.textView.setText((position + 1) + " :" + (school == null ? school : school.name));
        }

    }

    public void setDataSource(List<T> list) {
        mData = list;
        if (list == null) {
            return;
        }

        if (list.get(0) instanceof Province) {
            mAdapterType = 1;
        } else if (list.get(0) instanceof Univ) {
            mAdapterType = 2;
        } else if (list.get(0) instanceof School) {
            mAdapterType = 3;
        }

        notifyDataSetChanged();
    }

    public List<T> getmData() {
        return mData;
    }

    class ViewHolder {
        TextView textView;
    }

}
