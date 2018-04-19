package com.jtanveer.mindvolley.sample.feed;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;

import com.jtanveer.mindvolley.ImageRequestCallback;
import com.jtanveer.mindvolley.MindVolley;
import com.jtanveer.mindvolley.sample.R;

/**
 * Created by jtanveer on 4/20/18.
 */

public class BindingUtils {

    @BindingAdapter({"imageUrl"})
    public static void loadContentImage(final ImageView view, String url) {
        view.setImageDrawable(new ColorDrawable(ContextCompat.getColor(view.getContext(), R.color.colorLightGrey)));
        if (url != null && !TextUtils.isEmpty(url)) {
            MindVolley.getInstance().getImageVolley().from(url).fallbackImageResource(R.drawable.empty)
                    .load(new ImageRequestCallback() {
                @Override
                public void onImageLoaded(Bitmap bitmap) {
                    view.setImageBitmap(bitmap);
                }

                @Override
                public void onError(int fallbackImageResource) {
                    view.setImageResource(fallbackImageResource);
                }
            });
        }
    }

    @BindingAdapter({"profileImageUrl"})
    public static void loadCommentImage(final ImageView view, String url) {
        view.setImageDrawable(new ColorDrawable(ContextCompat.getColor(view.getContext(), R.color.colorLightGrey)));
        if (url != null && !TextUtils.isEmpty(url)) {
            MindVolley.getInstance().getImageVolley().from(url).fallbackImageResource(R.drawable.user)
                    .load(new ImageRequestCallback() {
                @Override
                public void onImageLoaded(Bitmap bitmap) {
                    view.setImageBitmap(bitmap);
                }

                @Override
                public void onError(int fallbackImageResource) {
                    view.setImageResource(fallbackImageResource);
                }
            });
        }
    }
}
