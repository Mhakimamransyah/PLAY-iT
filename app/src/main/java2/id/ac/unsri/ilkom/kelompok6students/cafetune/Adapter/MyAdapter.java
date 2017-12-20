package id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Controller.MusicController;
import id.ac.unsri.ilkom.kelompok6students.cafetune.GenreActivity;
import id.ac.unsri.ilkom.kelompok6students.cafetune.R;

/**
 * Created by hp on 25/09/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList musicBanner;
    ArrayList musicBanner2;
    ArrayList genreText;
    ArrayList genreText2;
    MusicController mc;
    Context context;


    public MyAdapter(Context context,ArrayList banner1,ArrayList banner2,ArrayList genre1,ArrayList genre2){
        this.context = context;
        this.musicBanner = banner1;
        this.musicBanner2 = banner2;
        this.genreText = genre1;
        this.genreText2 = genre2;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.image.setImageResource((Integer) this.musicBanner.get(position));
        holder.text.setText((CharSequence) this.genreText.get(position));
        holder.image2.setImageResource((Integer) this.musicBanner2.get(position));
        holder.text2.setText((CharSequence) this.genreText2.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GenreActivity.class);
                intent.putExtra("Genre",genreText.get(position).toString());
                v.getContext().startActivity(intent);
            }
        });
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GenreActivity.class);
                intent.putExtra("Genre",genreText2.get(position).toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.musicBanner.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image,image2;
        TextView text,text2;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.banner_1);
            image2 = (ImageView) itemView.findViewById(R.id.banner_2);
            text = (TextView) itemView.findViewById(R.id.genreText1);
            text2 = (TextView) itemView.findViewById(R.id.genreText2);
        }
    }
}
