package com.sundram.woocommercehomepage.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sundram.woocommercehomepage.R;
import com.sundram.woocommercehomepage.databinding.SingleItemProdcutViewBinding;
import com.sundram.woocommercehomepage.model.Product;

import java.util.List;

import javax.inject.Inject;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private SingleItemProdcutViewBinding binding;
    private List<Product> productList;
    private Context context;
    private static final String TAG = "PRODUCT_ADAPTER";
//
//    @Inject
//    public ProductAdapter() {
//    }

    public void setData(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.single_item_prodcut_view, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Glide.with(context)
                    .load(productList.get(position)
                            .getProductImage())
                    .placeholder(context.getResources().getDrawable(R.drawable.loading))
                    .error(context.getResources().getDrawable(R.drawable.placeholder))
                    .into(binding.productItemIv);

            binding.productTitleTv.setText(productList.get(position).getProductName());

            if (productList.get(position).getProductType().equalsIgnoreCase("simple")
                    || productList.get(position).getProductType().equalsIgnoreCase("grouped")) {
                binding.regularPriceLl.setVisibility(View.VISIBLE);
                if (productList.get(position).getSalePrice() != null) {
                    binding.salePriceTv.setVisibility(View.VISIBLE);
                    binding.saleTv.setVisibility(View.VISIBLE);
                    binding.salePriceTv.setText(productList.get(position).getSalePrice());
                    binding.regularPriceTv.setPaintFlags(binding.regularPriceTv.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    binding.salePriceTv.setVisibility(View.GONE);
                    binding.saleTv.setVisibility(View.GONE);
                }
                binding.regularPriceTv.setText(productList.get(position).getRegularPrice());
            }

            if (productList.get(position).getProductType().equalsIgnoreCase("variable")) {
                binding.regularPriceLl.setVisibility(View.GONE);
                binding.minMacPriceRl.setVisibility(View.VISIBLE);
                binding.minPriceTv.setText(productList.get(position).getCurrencySymbol() + " " + productList.get(position).getProductPriceMin());
                binding.maxPriceTv.setText(productList.get(position).getCurrencySymbol() + " " + productList.get(position).getProductPriceMax());
            }
            Log.i(TAG, "onBindViewHolder: ");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return productList.size() > 0 ? productList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SingleItemProdcutViewBinding singleItemProdcutViewBinding;

        public ViewHolder(@NonNull SingleItemProdcutViewBinding itemView) {
            super(itemView.getRoot());
            singleItemProdcutViewBinding = binding;
        }
    }
}
