package com.example.mrd.DataModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrd.Activities.CapturaActivity;
import com.example.mrd.R;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.MyViewHolder> {

    String data1[], data2[];
    Context context;

    public AdapterDatos(Context ct, String s1[], String s2[]){
        context = ct;
        data1 = s1;
        data2 = s2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos el nombre y mac del dispositivo
                //Toast.makeText(context, "BT Selected Nombre: " + data1[position] +" MAC: "+ data2[position] , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("NombreDispositivo", data1[position]);
                bundle.putString("DireccionDispositivo", data2[position]);
                intent.putExtras(bundle);

                ((Activity)context).setResult(Activity.RESULT_OK, intent);
                ((Activity)context).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.textViewNombre);
            myText2 = itemView.findViewById(R.id.textViewMac);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
