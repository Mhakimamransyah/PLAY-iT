package id.ac.unsri.ilkom.kelompok6students.cafetune;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Progress_Bar extends AppCompatActivity {

    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        bar = (ProgressBar)findViewById(R.id.simpleProgressBar);
        bar.setMax(200);
        Log.d("anjay","Progress "+bar.getProgress()+" ");
    }

}
