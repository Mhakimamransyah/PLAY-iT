package id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Controller.MusicController;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Connection;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Music;
import id.ac.unsri.ilkom.kelompok6students.cafetune.R;

/**
 * Created by hp on 11/11/2017.
 */

public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.MyViewHolder> {

    MusicController c_music;
    ArrayList<Music> music;
    Context context;
    Connection koneksi;
    Socket socket;


    public ListMusicAdapter(ArrayList<Music> nMusic,Context ncontext){
        Log.d("jamil","panjang : "+nMusic.size());
        music = nMusic;
        this.context = ncontext;
        koneksi = new Connection();
        socket = koneksi.getSocket();
        c_music = new MusicController(this.context);
    }


    public void setArrayMusic(ArrayList<Music> nMusic){
        this.music = nMusic;
    }

    @Override
    public ListMusicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_music, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ListMusicAdapter.MyViewHolder holder, final int position) {
        holder.judul.setText(this.music.get(position).getJudul().toString());
        if(music.get(position).getReq() == 1){
           holder.judul.setTextColor(Color.RED);
        }
        else{
            holder.judul.setTextColor(Color.BLACK);
        }
        holder.artist.setText(this.music.get(position).getArtist().toString());
        holder.tahun.setText(this.music.get(position).getTahun().toString());
        holder.album.setText(this.music.get(position).getAlbum().toString());
        holder.judul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c_music.isClickable(music.get(position).getId_music()) == 1) {
                    if (music.get(position).getReq() == 1) {
                        Toast.makeText(v.getContext(), "Membatalkan request " + music.get(position).getJudul(), Toast.LENGTH_SHORT).show();
                        music.get(position).setRequested(false);
                        c_music.doRequest(music.get(position).getId_music(), 0);
                        music.get(position).setReq(0);
                        holder.judul.setTextColor(Color.BLACK);
                        JSONObject json = new JSONObject();
                        try {
                            json.put("musicId", music.get(position).getId_music());
                            socket.emit("user-downvote", json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        music.get(position).setRequested(true);
                        //                        c_music.doRequest(music.get(position).getId_music(),1);

                        if (c_music.doRequest(music.get(position).getId_music(), 1)) {
                            holder.judul.setTextColor(Color.RED);
                            music.get(position).setReq(1);
                            Toast.makeText(v.getContext(), music.get(position).getJudul() + " di request", Toast.LENGTH_SHORT).show();
                            JSONObject json = new JSONObject();
                            try {
                                json.put("musicId", music.get(position).getId_music());
                                socket.emit("user-vote", json);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(context, "Ooops telah melampaui batas request", Toast.LENGTH_SHORT).show();
                        }

                    }
                } else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return music.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul,artist,tahun,album;
        public MyViewHolder(View itemView) {
            super(itemView);
            judul = (TextView)itemView.findViewById(R.id.judul_lagu);
            artist=(TextView)itemView.findViewById(R.id.artist);
            tahun = (TextView)itemView.findViewById(R.id.tahun);
            album = (TextView)itemView.findViewById(R.id.album);
        }
    }
}
