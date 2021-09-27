package com.sundram.woocommercehomepage.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sundram.woocommercehomepage.R;
import com.sundram.woocommercehomepage.databinding.SingleSliderViewBinding;
import com.sundram.woocommercehomepage.model.BannerInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

public class HorizontalSliderAdapter extends RecyclerView.Adapter<HorizontalSliderAdapter.ViewHolder> {

    private Context context;
    private List<BannerInfo> bannerInfoList;
    SingleSliderViewBinding binding;
    private static final String TAG = "HorizontalSliderAdapter";

    @Inject
    public HorizontalSliderAdapter() {
    }

    public void setData(Context context, List<BannerInfo> bannerInfoList) {
        this.context = context;
        this.bannerInfoList = bannerInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.single_slider_view, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Glide.with(context)
                    .load(bannerInfoList.get(position).getBannerUrl())
                    .placeholder(context.getResources().getDrawable(R.drawable.loading))
                    .error(context.getResources().getDrawable(R.drawable.placeholder))
                    .into(binding.horizontalSliderIv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return bannerInfoList.size() > 0 ? bannerInfoList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SingleSliderViewBinding singleSliderViewBinding;

        public ViewHolder(@NonNull SingleSliderViewBinding itemView) {
            super(itemView.getRoot());
            singleSliderViewBinding = itemView;
        }
    }
}
