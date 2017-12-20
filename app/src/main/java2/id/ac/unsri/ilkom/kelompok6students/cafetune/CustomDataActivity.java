package id.ac.unsri.ilkom.kelompok6students.cafetune;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomDataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private  RecyclerView.Adapter adapter;
    EditText judul,desc;
    Button doTambah;
    DatabaseHelper myDb;
    ArrayList <Model>model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.music_queue);
        setSupportActionBar(toolbar);

        this.myDb = new DatabaseHelper(this);
        this.model = myDb.getAllListData();

        judul = (EditText)findViewById(R.id.judul_input);
        desc = (EditText)findViewById(R.id.desc_input);
        doTambah = (Button)findViewById(R.id.doTambah);

        recyclerView = (RecyclerView)findViewById(R.id.recycleview2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomeAdapter(this,this.model);
        recyclerView.setAdapter(adapter);


        doTambahData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void doTambahData(){
        doTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(judul.getText().toString().isEmpty() || desc.getText().toString().isEmpty()){
                    Toast.makeText(CustomDataActivity.this, "Both data must not empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    long respone = myDb.insertData(judul.getText().toString(),desc.getText().toString());
                    if(respone == -1){
                        Toast.makeText(CustomDataActivity.this, "Oops , data not inserted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(CustomDataActivity.this, "Yeahhhhh , data inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }
            }
        });
    }

   

}
