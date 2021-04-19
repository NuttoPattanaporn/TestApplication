package com.example.searchtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.searchtest.R;
import com.example.searchtest.service.age.Age;

import java.util.List;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder> {

    private List<Age> dataList;
    private Context mContext;

    public DataRecyclerViewAdapter(List<Age> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(dataList != null){
            holder.nameText.setText(dataList.get(position).getName());
            holder.createdAtText.setText(String.format("Created : %s",dataList.get(position).getCreatedAt()));
            Glide.with(holder.itemView.getContext())
                    .load(dataList.get(position).getAvatar())
                    .into(holder.image);
        };
    }

    public void clear(){
        dataList.clear();
        notifyDataSetChanged();
    }

    public void insert(List<Age> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView createdAtText;
        private TextView nameText;
        private ImageView image;
        private View mView;
        public ViewHolder(View view){
            super(view);
            createdAtText = (TextView) view.findViewById(R.id.createdAt);
            nameText = (TextView) view.findViewById(R.id.name);
            image = (ImageView) view.findViewById(R.id.img_link);
            mView = view;
        }
    }
}
