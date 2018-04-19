package com.jtanveer.mindvolley.sample.main;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtanveer.mindvolley.ImageRequestCallback;
import com.jtanveer.mindvolley.MindVolley;
import com.jtanveer.mindvolley.sample.R;
import com.jtanveer.mindvolley.sample.databinding.FragmentImageItemBinding;

/**
 * Created by jtanveer on 19/4/18.
 */

public class ImageItemFragment extends Fragment {

    private static final String KEY_URL = "url";

    private FragmentImageItemBinding binding;

    private String url;

    public ImageItemFragment() {
        // Required empty public constructor
    }

    public static ImageItemFragment getInstance(String url) {
        ImageItemFragment fragment = new ImageItemFragment();
        Bundle args = new Bundle();
        args.putString(KEY_URL, url);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_item, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceStates) {
        super.onActivityCreated(savedInstanceStates);

        url = getArguments().getString(KEY_URL);

        MindVolley.getInstance().getImageVolley()
                .from(url)
                .load(new ImageRequestCallback() {
                    @Override
                    public void onImageLoaded(Bitmap bitmap) {
                        binding.imgItem.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(int fallbackImageResource) {
                        binding.imgItem.setImageResource(fallbackImageResource);
                    }
                });
    }
}
