package id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Music;
import id.ac.unsri.ilkom.kelompok6students.cafetune.R;

/**
 * Created by hp on 11/11/2017.
 */

public class PlayNowAdapter extends RecyclerView.Adapter<PlayNowAdapter.MyViewHolder> {
    Music music;

    public PlayNowAdapter(Music nMusic){
        this.music = nMusic;
    }

    @Override
    public PlayNowAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.play_now, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlayNowAdapter.MyViewHolder holder, int position) {
        holder.judul.setText(music.getJudul().toString());
        holder.artist.setText(music.getArtist().toString());
        holder.tahun.setText(music.getTahun().toString());
        holder.album.setText(music.getAlbum().toString());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul,artist,tahun,album;
        public MyViewHolder(View itemView) {
            super(itemView);
            judul = (TextView)itemView.findViewById(R.id.judul_play_now);
            artist=(TextView)itemView.findViewById(R.id.artis_playnow);
            tahun = (TextView)itemView.findViewById(R.id.tahun_playnow);
            album = (TextView)itemView.findViewById(R.id.album_playnow);
        }
    }
}
