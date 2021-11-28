package com.example.android.inbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    List<ItemModel> items;

    public ItemAdapter(List<ItemModel> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_layout, null);

            viewHolder = new ViewHolder();
            viewHolder.imgAvatar = view.findViewById(R.id.img_avatar);
            viewHolder.txtName = view.findViewById(R.id.txt_name);
            viewHolder.txtTitle = view.findViewById(R.id.txt_title);
            viewHolder.txtContent = view.findViewById(R.id.txt_content);
            viewHolder.txtTime = view.findViewById(R.id.txt_time);
            viewHolder.btnStar = view.findViewById(R.id.btn_star);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ItemModel item = items.get(i);

        viewHolder.imgAvatar.setImageResource(item.getAvatarResource());
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtTitle.setText(item.getTitle());
        viewHolder.txtContent.setText(item.getContent());
        viewHolder.txtTime.setText(item.getTime());

        return view;
    }

    private class ViewHolder {
        public ImageView imgAvatar;
        public TextView txtName;
        public TextView txtTitle;
        public TextView txtContent;
        public TextView txtTime;
        public ImageButton btnStar;
    }
}
