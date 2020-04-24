package com.artemissoftware.pokedex.databinding;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.artemissoftware.pokedex.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideBinding {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, String imageUrl) {

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);

    }


    /*
    app:defaultImageUrl="@drawable/ic_launcher_foreground"
*/

    @BindingAdapter({"imageUrl", "defaultImageUrl"})
    public static void setImage(ImageView view, String imageUrl, int defaultImageUrl) {

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(defaultImageUrl)
                .error(R.drawable.ic_launcher_foreground);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);

    }

}
