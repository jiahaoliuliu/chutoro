package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiahaoliuliu.androidcleanarchitecturecore.R;
import com.jiahaoliuliu.domain.IMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieHolder>{

    private List<? extends IMovie> moviesList;
    private Picasso picasso;

    public MoviesListAdapter() {
        this.moviesList = new ArrayList<>();
        this.picasso = Picasso.get();
    }

    public void setMoviesList(List<? extends IMovie> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        IMovie movie = moviesList.get(position);
        holder.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView description;

        public MovieHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.movie_image);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);
        }

        public void setMovie(IMovie movie) {
            picasso.load(movie.getImageUrl()).into(image);
            this.title.setText(movie.getTitle());
            this.description.setText(movie.getDescription());
        }
    }
}
