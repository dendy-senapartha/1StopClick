package com.a1stopclick.homeactivity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1stopclick.R;
import com.bumptech.glide.Glide;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class TextItemViewHolder extends RecyclerView.ViewHolder {
    private TextView itemMovieTitle;
    private ImageView itemMoviePoster;
    private TextView textMovieReleaseDate;
    private TextView itemMovieGenre;
    private TextView itemMovieRating;

    public TextItemViewHolder(View itemView) {
        super(itemView);
        itemMovieTitle = (TextView) itemView.findViewById(R.id.item_movie_title);
        textMovieReleaseDate = (TextView) itemView.findViewById(R.id.item_movie_release_date);
        itemMovieGenre = (TextView) itemView.findViewById(R.id.item_movie_genre);
        itemMovieRating = (TextView) itemView.findViewById(R.id.item_movie_rating);
        itemMoviePoster = (ImageView) itemView.findViewById(R.id.item_movie_poster);
    }

    public void setItemMovieTitle(String text) {
        itemMovieTitle.setText(text);
    }

    public void setItemMovieReleaseDate(String text) {
        textMovieReleaseDate.setText(text);
    }
    public void setItemMovieGenre(String text) {
        itemMovieGenre.setText(text);
    }
    public void setItemMovieRating(String text) {
        itemMovieRating.setText(text);
    }
    public void setItemMoviePoster(String uri, Fragment parentFragment) {
        //itemMoviePoster.setImageBitmap();
        Glide.with(parentFragment).load(uri).fitCenter().placeholder(R.drawable.image_not_available).into(itemMoviePoster);
    }

}
