package com.codepath.flicksmoviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.flicksmoviesapp.models.Config;
import com.codepath.flicksmoviesapp.models.Movie;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by tjeanjacques on 6/21/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    ArrayList<Movie> movies;
    Config config;
    Context context;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        String imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
        Glide.with(context)
                .load(imageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 25, 0))
                .placeholder(R.drawable.flicks_movie_placeholder)
                .error(R.drawable.flicks_movie_placeholder)
                .into(holder.ivPosterImage);
    }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
ImageView ivPosterImage;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPosterImage = (ImageView) itemView.findViewById(R.id.ivPosterImage);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);

        }
    }

}
