package id.ac.unsri.ilkom.kelompok6students.cafetune;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter.MyAdapter;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Controller.MusicController;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Connection;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Music;


public class MainActivity extends AppCompatActivity {

    Connection koneksi;
    Socket socket;
    MusicController c_music;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    boolean doubleBackToExitPressedOnce = false;

    public ArrayList musicBanner = new ArrayList(Arrays.asList(R.drawable.music_genre,R.drawable.music_genre,R.drawable.music_genre));
    public ArrayList musicBanner2 = new ArrayList(Arrays.asList(R.drawable.music_genre,R.drawable.music_genre,R.drawable.music_genre));
    public ArrayList genreText = new ArrayList(Arrays.asList("Pop","Electronic", "Metal"));
    public ArrayList genreText2 = new ArrayList(Arrays.asList("Rock","Classic","Others"));



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Play Cafe");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        koneksi = new Connection();
        c_music = new MusicController(this);

        socket = koneksi.getSocket();
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter(this,this.musicBanner,this.musicBanner2,this.genreText,this.genreText2);
        recyclerView.setAdapter(adapter);

        JSONObject o = new JSONObject();
        try {
            o.put("genre","All");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit("koleksi",o);
        socket.on("koleksi",handlingAllMusic);
    }

    ArrayList<Music> music = new ArrayList<Music>();
    private Emitter.Listener handlingAllMusic = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {

                        JSONArray f = data.getJSONArray("db");
                        Music m;
                        for(int i=0;i<f.length();i++){
                            JSONObject h = f.getJSONObject(i);
                            m = new Music(h.getString("judul").toString(),h.getString("tahun").toString(),h.getString("artist").toString(),
                                    h.getString("album").toString(),h.getString("genre").toString(),Integer.parseInt(h.getString("id").toString()),0);
                            music.add(i,m);
                        }
                        c_music.initiateAllMusic(music);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.music_queue){
            Intent intent = new Intent(this,QueueActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
           //socket emit
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Kembali tombol back untuk kembali ke halaman login", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
