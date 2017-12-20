package id.ac.unsri.ilkom.kelompok6students.cafetune;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Adapter.ListMusicAdapter;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Controller.MusicController;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Music;

public class GenreActivity extends AppCompatActivity {

    private RecyclerView recyclerView_list_music;
    private RecyclerView.LayoutManager layoutManager_list_music;
    private RecyclerView.Adapter adapter_list_music;
    MusicController c_music;
    ArrayList<Music> nmusic = new ArrayList<Music>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Bundle extras = getIntent().getExtras();
        String response = extras.getString("Genre");
        toolbar.setTitle(response);
        setSupportActionBar(toolbar);

        c_music = new MusicController(this);
        nmusic = c_music.getMusic(response);

        recyclerView_list_music = (RecyclerView)findViewById(R.id.recycleview_list_music);
        recyclerView_list_music.setItemAnimator(new DefaultItemAnimator());
        layoutManager_list_music = new LinearLayoutManager(this);
        recyclerView_list_music.setLayoutManager(layoutManager_list_music);
        adapter_list_music = new ListMusicAdapter(nmusic,this);
        recyclerView_list_music.setAdapter(adapter_list_music);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),QueueActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.genre_menu, menu);
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
            Toast.makeText(this,"Move to music queue activity", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
