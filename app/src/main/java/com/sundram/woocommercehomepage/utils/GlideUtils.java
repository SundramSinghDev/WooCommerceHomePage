package com.sundram.woocommercehomepage.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.sundram.woocommercehomepage.R;

public class GlideUtils {
    @BindingAdapter("imgViewSrc")
    public static void showImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder)
                .into(view);
    }
}
