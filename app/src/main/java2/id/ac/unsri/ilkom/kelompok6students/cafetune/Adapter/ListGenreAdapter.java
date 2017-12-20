package id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Category;
import id.ac.unsri.ilkom.kelompok6students.cafetune.GenreActivity;
import id.ac.unsri.ilkom.kelompok6students.cafetune.R;

/**
 * Created by hp on 11/11/2017.
 */

public class ListGenreAdapter extends RecyclerView.Adapter<ListGenreAdapter.MyViewHolder> {

    Category category;

    public ListGenreAdapter(){
        category = new Category();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_genre, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListGenreAdapter.MyViewHolder holder, final int position) {
        holder.list_genre_t.setText(this.category.getList_categori().get(position).toString());
        holder.list_genre_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GenreActivity.class);
                intent.putExtra("Genre",category.getList_categori().get(position).toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.getCategoryLength();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView list_genre_t;
        public MyViewHolder(View itemView) {
            super(itemView);
            list_genre_t = (TextView)itemView.findViewById(R.id.list_genre_text);
        }
    }
}
