package com.a1stopclick.home.movielist;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.a1stopclick.R;
import com.a1stopclick.moviedetails.MovieDetailActivity;
import com.a1stopclick.util.AndroidUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.domain.product.ProductResult;

import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView itemMoviePoster;
    private Fragment parentFragment;

    public MovieItemViewHolder(View itemView, Fragment parentFragment) {
        super(itemView);
        itemMoviePoster = (ImageView) itemView.findViewById(R.id.item_movie_poster);
        this.parentFragment = parentFragment;
    }

    public void setItemMovieTitle(String text) {
        // itemMovieTitle.setText(text);
    }

    public void setItemMovieReleaseDate(String text) {
        //textMovieReleaseDate.setText(text);
    }

    public void setItemMovieGenre(String text) {
        //itemMovieGenre.setText(text);
    }

    public void setItemMovieRating(String text) {
        //itemMovieRating.setText(text);
    }

    public void setItemMoviePoster(String uri, Fragment parentFragment) {
        Glide.with(parentFragment).load(uri).placeholder(R.drawable.placeholder_detail_product).into(itemMoviePoster);
    }

    public void bind(ProductResult item) {
        setItemMovieTitle(item.product.productName);
        setItemMovieGenre(item.product.subcategory.name);
        setItemMovieReleaseDate(AndroidUtils.getLongDate(item.product.subcategory.created.toString()));

        itemView.setOnClickListener(onClick -> {
                    Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
                    intent.putExtra(MovieDetailActivity.MOVIE_ITEM, JSON.toJSONString(item));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                                .makeSceneTransitionAnimation((Activity) itemView.getContext(), itemMoviePoster, "poster");
                        parentFragment.startActivityForResult(intent,
                                FragmentMovieList.REQUEST_REFRESH_MOVIE_LIST, activityOptionsCompat.toBundle());
                    } else {
                        parentFragment.startActivityForResult(intent,
                                FragmentMovieList.REQUEST_REFRESH_MOVIE_LIST);
                    }
                }
        );
    }

}
