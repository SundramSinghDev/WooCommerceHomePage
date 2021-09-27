package com.sundram.woocommercehomepage.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.GsonBuilder;
import com.sundram.woocommercehomepage.R;
import com.sundram.woocommercehomepage.adapters.HorizontalSliderAdapter;
import com.sundram.woocommercehomepage.adapters.ProductAdapter;
import com.sundram.woocommercehomepage.adapters.SubCategoryAdapter;
import com.sundram.woocommercehomepage.databinding.CategoryViewBinding;
import com.sundram.woocommercehomepage.databinding.FragmentHomeBinding;
import com.sundram.woocommercehomepage.databinding.FullWidhtBannerBinding;
import com.sundram.woocommercehomepage.databinding.ProductRvViewBinding;
import com.sundram.woocommercehomepage.databinding.SubCateRvViewBinding;
import com.sundram.woocommercehomepage.databinding.ViewAllBtnViewBinding;
import com.sundram.woocommercehomepage.model.BannerInfo;
import com.sundram.woocommercehomepage.model.Category;
import com.sundram.woocommercehomepage.model.Data;
import com.sundram.woocommercehomepage.utils.ConnectionUtils;
import com.sundram.woocommercehomepage.utils.GridSpacingItemDecoration;
import com.sundram.woocommercehomepage.viewmodels.HomeViewModel;

import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private static final String TAG = "HOME_FRAGMENT";
    private static Integer currentPage = 0;
    private static Integer NUM_PAGES;
    private FullWidhtBannerBinding fullWidhtBannerBinding;

    SubCategoryAdapter subCategoryAdapter;

    //    @Inject
    ProductAdapter productAdapter;

    @Inject
    HorizontalSliderAdapter fullWidthSliderAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getHomePageData();
        autoStartSlider();
        getError();
        Log.i(TAG, "onViewCreated: ");
    }

    private void getError() {
        viewModel.errorData.observe(getViewLifecycleOwner(), this::handleErrorResponse);
    }

    private void handleErrorResponse(String s) {

    }

    private void getHomePageData() {
        viewModel.getHomePageData(getContext()).observe(getViewLifecycleOwner(), this::handleResponse);
    }

    private void handleResponse(Data data) {
        Log.i(TAG, "handleResponse: ");
        if (data != null) {
            binding.pb.setVisibility(View.GONE);
            if (data.getBannerInfo().size() > 0 && data.getBannerInfo() != null) {
                Log.i(TAG, "handleResponse: BANNER");
                handleBannerDataAndAttachView(data.getBannerInfo());
            }
            if (data.getCategoryData().size() > 0 && data.getCategoryData() != null) {
                Log.i(TAG, "handleResponse: CATEGORY");
                handleCategoryDataAndAttachView(data);
            }
        }
    }

    private void handleCategoryDataAndAttachView(Data data) {

        for (int i = 0; i < data.getCategoryData().size(); i++) {
            subCategoryAdapter = new SubCategoryAdapter();
            productAdapter = new ProductAdapter();
            CategoryViewBinding categoryViewBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.category_view, binding.containerLl, false);

            categoryViewBinding.categoryTitleTv.setTextColor(Color.parseColor(data.getCategoryTextColor()));
            categoryViewBinding.categoryContainer.setBackgroundColor(Color.parseColor(data.getCategoryBgColor()));
            categoryViewBinding.categoryTitleTv.setText(data.getCategoryData().get(i).getCategoryName());
            categoryViewBinding.viewAllCate.setTextColor(Color.parseColor(data.getCategoryTextColor()));

            if (data.getCategoryData().get(i).getSubcategories().size() > 0) {
                categoryViewBinding.subCategoryRv.setHasFixedSize(true);
                categoryViewBinding.subCategoryRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                subCategoryAdapter.setData(data.getCategoryData().get(i).getSubcategories(), getActivity(),
                        data.getCategoryBgColor(), data.getCategoryTextColor());
                categoryViewBinding.subCategoryRv.setAdapter(subCategoryAdapter);
                subCategoryAdapter.notifyDataSetChanged();
            }

            if (data.getCategoryData().get(i).getProducts().size() > 0) {
                categoryViewBinding.productRv.setHasFixedSize(true);
                categoryViewBinding.productRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                categoryViewBinding.productRv.addItemDecoration(new GridSpacingItemDecoration(2, 15, false));
                productAdapter.setData(data.getCategoryData().get(i).getProducts(), getContext());
                categoryViewBinding.productRv.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }
            binding.containerLl.addView(categoryViewBinding.getRoot());
        }
    }

    private void handleBannerDataAndAttachView(List<BannerInfo> bannerInfo) {
        fullWidhtBannerBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.full_widht_banner, binding.containerLl, false);
        fullWidthSliderAdapter.setData(getContext(), bannerInfo);
        fullWidhtBannerBinding.bannerViewpager.setAdapter(fullWidthSliderAdapter);
        binding.containerLl.addView(fullWidhtBannerBinding.getRoot());
        setupIndicator(bannerInfo);
        setUpCurrentPageIndicator();
    }

    private void setUpCurrentPageIndicator() {
        try {
            fullWidhtBannerBinding.bannerViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setupCurrentIndicator(position);
                }
            });
        } catch (Exception e) {

        }
    }

    private void autoStartSlider() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                try {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    fullWidhtBannerBinding.bannerViewpager.setCurrentItem(currentPage++, true);
                } catch (Exception e) {
                }
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }

    private void setupIndicator(List<BannerInfo> widget) {
        try {
            fullWidhtBannerBinding.layoutIndicator.removeAllViews();
            ImageView[] indicators = new ImageView[widget.size()];
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(4, 0, 4, 0);
            for (int i = 0; i < indicators.length; i++) {
                indicators[i] = new ImageView(getContext());
                indicators[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_inactive));
                indicators[i].setLayoutParams(layoutParams);
                fullWidhtBannerBinding.layoutIndicator.addView(indicators[i]);
            }
        } catch (Exception e) {

        }
    }

    private void setupCurrentIndicator(int index) {
        try {
            int itemcildcount = fullWidhtBannerBinding.layoutIndicator.getChildCount();
            for (int i = 0; i < itemcildcount; i++) {
                ImageView imageView = (ImageView) fullWidhtBannerBinding.layoutIndicator.getChildAt(i);
                if (i == index) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_active));
                } else {
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_inactive));
                }
            }
        } catch (Exception e) {

        }
    }
}