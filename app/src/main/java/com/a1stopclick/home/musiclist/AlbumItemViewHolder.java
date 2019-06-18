package com.a1stopclick.home.musiclist;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.home.musiclist.albumdetails.AlbumDetailActivity;
import com.a1stopclick.util.AndroidUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.domain.base.result.AlbumResult;

public class AlbumItemViewHolder extends RecyclerView.ViewHolder {
    private TextView itemMovieTitle;
    private ImageView albumPoster;
    private TextView albumYear;

    public AlbumItemViewHolder(View itemView) {
        super(itemView);
        itemMovieTitle = (TextView) itemView.findViewById(R.id.albumTitle);
        albumYear = (TextView) itemView.findViewById(R.id.albumYear);
        albumPoster = (ImageView) itemView.findViewById(R.id.itemAlbumPoster);
    }

    public void setItemMovieTitle(String text) {
         itemMovieTitle.setText(text);
    }

    public void setItemAlbumYear(String text) {
        albumYear.setText(text);
    }


    public void setMusicPoster(String uri, Fragment parentFragment) {
        Glide.with(parentFragment).load(uri).placeholder(R.drawable.placeholder_detail_product).into(albumPoster);
    }

    public void bind(AlbumResult item) {
        setItemMovieTitle(item.album.name);
        setItemAlbumYear(AndroidUtils.getYear(item.album.releaseDate.toString()));
        //holder.setItemMovieRating(items.get(position).subcategory.name);

        itemView.setOnClickListener(onClick -> {
                    Intent intent = new Intent(itemView.getContext(), AlbumDetailActivity.class);
                    intent.putExtra(AlbumDetailActivity.ALBUM_ITEM, JSON.toJSONString(item));

                    itemView.getContext().startActivity(intent);
                }
        );
    }

}
