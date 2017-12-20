package id.ac.unsri.ilkom.kelompok6students.cafetune;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Connection;

public class login extends AppCompatActivity {

    TextView asking_code,see_music,login;
    EditText number,code;
    Connection koneksi;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        koneksi = new Connection();
        this.socket = koneksi.getSocket();


        login = (TextView)findViewById(R.id.login);
        asking_code = (TextView)findViewById(R.id.asking_code);
        see_music = (TextView)findViewById(R.id.see_music_av);

        number =(EditText)findViewById(R.id.table_number);
        code = (EditText)findViewById(R.id.table_code);


        this.askingCodeListener();
        this.seeMusicAvalaibale();
        this.doLogin();
    }

    private void askingCodeListener(){
        asking_code.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setTitle("Dimana saya dapat menemukan kode ini ?");
                alertDialogBuilder.setMessage("Hubungi resepsionis cafe atau " +
                        "lihat kode pada meja cafe secara langsung");
                alertDialogBuilder.setIcon(R.drawable.warning);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void seeMusicAvalaibale(){

    }

    private void doLogin(){
        this.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number_input = number.getText().toString();
                String code_input = code.getText().toString();

                //do validation
                if(code_input.isEmpty()){
                    Toast.makeText(login.this, "Kode Meja Harus Diisi", Toast.LENGTH_SHORT).show();
                }
                if(number_input.isEmpty()){
                    Toast.makeText(login.this, "Nomor Meja Harus Diisi", Toast.LENGTH_SHORT).show();
                }

                if(!code_input.isEmpty() && !number_input.isEmpty())
                {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("table_number",number_input);
                        json.put("table_code",code_input);
                        socket.emit("authentication",json);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    socket.on("result_auth",handlingLogin);
                }
            }
        });
    }

    private Emitter.Listener handlingLogin = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(args[0].toString().equalsIgnoreCase("true")){
                        Intent i = new Intent(login.this, MainActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(login.this, "gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {

                   }
               });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
