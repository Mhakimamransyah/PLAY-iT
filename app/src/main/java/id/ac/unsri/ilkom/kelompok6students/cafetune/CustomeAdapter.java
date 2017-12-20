package id.ac.unsri.ilkom.kelompok6students.cafetune;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 26/09/2017.
 */

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {
    ArrayList<Model> model;
    Context context;

    public  CustomeAdapter(Context cntx,ArrayList<Model> mdl){
        this.model = mdl;
        this.context = cntx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.judul.setText(this.model.get(position).getJudul());
        holder.desc.setText(this.model.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return this.model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul,desc;
        public MyViewHolder(View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.judul_tampil);
            desc = (TextView) itemView.findViewById(R.id.desc_tampil);
        }
    }
}
