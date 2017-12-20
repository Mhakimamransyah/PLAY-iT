package id.ac.unsri.ilkom.kelompok6students.cafetune;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter.PlayNowAdapter;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter.QueueMusicAdapter;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Controller.MusicController;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Connection;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Music;

public class QueueActivity extends AppCompatActivity {

    Connection koneksi;
    Socket socket;

    MusicController c_music;

    private RecyclerView recyclerView_play_now;
    private RecyclerView.LayoutManager layoutManager_play_now;
    private RecyclerView.Adapter adapter_play_now;

    private RecyclerView recyclerView_queue;
    private RecyclerView.LayoutManager layoutManager_queue;
    private RecyclerView.Adapter adapter_queue;

    ArrayList<Music> music_update = new ArrayList<Music>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Queue Music");
        setSupportActionBar(toolbar);

        koneksi = new Connection();
        socket = koneksi.getSocket();

        c_music = new MusicController(this);

        socket.emit("playlist","");
        socket.emit("playlist-nowPlaying","");
        socket.on("playlist-update",handlingUpdatePlaylist);
        socket.on("playlist-start",handlingStartPlaylist);

        recyclerView_play_now = (RecyclerView) findViewById(R.id.recycleview_play_now);
        recyclerView_play_now.setItemAnimator(new DefaultItemAnimator());
        layoutManager_play_now = new LinearLayoutManager(this);
        recyclerView_play_now.setLayoutManager(layoutManager_play_now);
//        adapter_play_now = new PlayNowAdapter();
//        recyclerView_play_now.setAdapter(adapter_play_now);

        recyclerView_queue = (RecyclerView) findViewById(R.id.recycleview_queue_music);
        recyclerView_queue.setItemAnimator(new DefaultItemAnimator());
        layoutManager_queue = new LinearLayoutManager(this);
        recyclerView_queue.setLayoutManager(layoutManager_queue);
        adapter_queue = new QueueMusicAdapter(music_update);
        recyclerView_queue.setAdapter(adapter_queue);

    }


    private Emitter.Listener handlingUpdatePlaylist = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];

                    try {
                        music_update.clear();
                        JSONArray f = data.getJSONArray("db");

                        Music m;
                        for(int i=0;i<f.length();i++){
                            JSONObject h = f.getJSONObject(i);

                            Log.d("json update", h.toString());

                            m = new Music(h.getString("title").toString(),h.getString("tahun").toString(),h.getString("artist").toString(),
                                    h.getString("album").toString(),h.getString("genre").toString(),Integer.parseInt(h.getString("id").toString()),0);
                            music_update.add(i,m);
                        }
                        Log.d("bangsat","playlist update size: "+music_update.size());
                        adapter_queue = new QueueMusicAdapter(music_update);
                        recyclerView_queue.setAdapter(adapter_queue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener handlingStartPlaylist = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    Log.d("json start", data.toString());
                    try {

                        data = data.getJSONObject("db");
                        Music m;
//                            JSONObject h = f.getJSONObject(0);
                            m = new Music(data.getString("musicTitle").toString(),data.getString("tahun").toString(),data.getString("musicArtist").toString(),
                                    data.getString("album").toString(),data.getString("genre").toString(),Integer.parseInt(data.getString("id").toString()),0);

                            c_music.changeStateToUnclickable(data.getInt("id"));

                        Log.d("bang bing","playlist start : "+m.getJudul());
                        adapter_play_now = new PlayNowAdapter(m);
                        recyclerView_play_now.setAdapter(adapter_play_now);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

}
