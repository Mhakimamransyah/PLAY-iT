package id.ac.unsri.ilkom.kelompok6students.cafetune.Entity;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by hp on 25/11/2017.
 */
public class Connection {


     Socket mSocket;

    public Connection(){
        try {
            mSocket = IO.socket("http://192.168.43.39:90");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        mSocket.emit("pesan","terhubung");
        mSocket.on("server_ack",handling);
    }


    private Emitter.Listener handling = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d("sok",args[0].toString());
        }
    };

    public Socket getSocket(){
        return this.mSocket;
    }



}
