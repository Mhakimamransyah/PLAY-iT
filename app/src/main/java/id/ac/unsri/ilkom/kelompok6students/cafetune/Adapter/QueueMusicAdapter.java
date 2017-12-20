package id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Music;
import id.ac.unsri.ilkom.kelompok6students.cafetune.R;

/**
 * Created by hp on 11/11/2017.
 */

public class QueueMusicAdapter extends RecyclerView.Adapter<QueueMusicAdapter.MyViewHolder> {
    ArrayList<Music> music;

    public QueueMusicAdapter(ArrayList<Music> nMusic){
        music = nMusic;
    }

    @Override
    public QueueMusicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.queue_music, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(QueueMusicAdapter.MyViewHolder holder, int position) {
         holder.judul.setText(this.music.get(position).getJudul().toString());
         holder.tahun.setText(this.music.get(position).getTahun().toString());
         holder.artist.setText(this.music.get(position).getArtist().toString());
         holder.album.setText(this.music.get(position).getAlbum().toString());
    }

    @Override
    public int getItemCount() {
        return this.music.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul,artist,tahun,album;
        public MyViewHolder(View itemView) {
            super(itemView);
            judul = (TextView)itemView.findViewById(R.id.judul_lagu_queue);
            artist=(TextView)itemView.findViewById(R.id.artist_queue);
            tahun = (TextView)itemView.findViewById(R.id.tahun_queue);
            album = (TextView)itemView.findViewById(R.id.album_queue);
        }
    }
}
