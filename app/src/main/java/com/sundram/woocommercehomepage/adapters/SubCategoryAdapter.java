package com.sundram.woocommercehomepage.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sundram.woocommercehomepage.R;
import com.sundram.woocommercehomepage.databinding.SingleItemSubCateViewBinding;
import com.sundram.woocommercehomepage.model.Subcategory;

import java.util.List;

import javax.inject.Inject;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private SingleItemSubCateViewBinding binding;
    private List<Subcategory> subcategoryList;
    private Context context;
    private String category_bg_color;
    private String category_text_color;
    private static final String TAG = "SubCategoryAdapter";

//    @Inject
//    public SubCategoryAdapter() {
//    }

    public void setData(List<Subcategory> subcategoryList, Context context, String category_bg_color, String category_text_color) {
        this.subcategoryList = subcategoryList;
        this.context = context;
        this.category_bg_color = category_bg_color;
        this.category_text_color = category_text_color;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.single_item_sub_cate_view, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            //here setting up the default img to sub category because did not gert any img from server
            Glide.with(context).load(context.getResources().getDrawable(R.drawable.placeholder)).error(R.drawable.loading).into(binding.subCateIv);
            binding.subCateTitleTv.setText(subcategoryList.get(position).getName());
            Log.i(TAG, "onBindViewHolder: ");
            binding.subCateTitleTv.setBackgroundColor(Color.parseColor(category_bg_color));
            binding.subCateTitleTv.setTextColor(Color.parseColor(category_text_color));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return subcategoryList.size() > 0 ? subcategoryList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SingleItemSubCateViewBinding singleItemSubCateViewBinding;

        public ViewHolder(@NonNull SingleItemSubCateViewBinding itemView) {
            super(itemView.getRoot());
            singleItemSubCateViewBinding = binding;

        }
    }
}
