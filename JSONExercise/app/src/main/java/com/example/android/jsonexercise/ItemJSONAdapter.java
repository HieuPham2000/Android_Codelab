package com.example.android.jsonexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class ItemJSONAdapter extends RecyclerView.Adapter<ItemJSONAdapter.ItemViewHolder> {

    private JSONArray jsonArray;
    private Context context;
    private IItemClickListener itemClickListener;

    public ItemJSONAdapter(JSONArray jsonArray, IItemClickListener itemClickListener) {
        this.jsonArray = jsonArray;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            ItemModel itemModel = new ItemModel(jsonObject);

            Picasso.with(context).load(itemModel.getAvatar().get("thumbnail")).into(holder.imgAvatar);
            holder.txtUsername.setText(itemModel.getUsername());
            holder.txtEmail.setText(itemModel.getEmail());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView txtUsername;
        TextView txtEmail;
        IItemClickListener _itemClickListener;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.img_avatar);
            txtUsername = itemView.findViewById(R.id.txt_username);
            txtEmail = itemView.findViewById(R.id.txt_email);

            _itemClickListener = itemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (_itemClickListener != null)
                        _itemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
