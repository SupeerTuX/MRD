package com.example.mrd.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mrd.DataModel.AdapterDatos;
import com.example.mrd.R;

import java.util.Set;

public class ListaBTActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    RecyclerView recyclerView;
    String s1[], s2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bt);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter != null){
            if(!bluetoothAdapter.isEnabled()){// si no está activado
                // Mandamos a activarlo
                Intent habilitarBluIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(habilitarBluIntent, 243);
            }else {
                // Obtenemos la lista de dispositivos sincronizados
                Set<BluetoothDevice> dispositivosSync = bluetoothAdapter.getBondedDevices();

                // Si hay dispositivos sincronizados
                if(dispositivosSync.size() > 0){
                    int i =0;
                    s1 = new String[dispositivosSync.size()];
                    s2 = new String[dispositivosSync.size()];
                    // Llenamos el array de dispositivos para pasarlo al adapter
                    for(BluetoothDevice dispositivo : dispositivosSync){
                        //dispositivos.add(new ItemDispositivo(dispositivo.getName(),  dispositivo.getAddress()));
                        Log.d("Debug", "Nombre: "+ dispositivo.getName() + " MAC: "+dispositivo.getAddress());
                        s1[i] = dispositivo.getName().toString();
                        s2[i] = dispositivo.getAddress().toString();
                        i++;
                    }
                    recyclerView = findViewById(R.id.recyclerView);
                    AdapterDatos myAdapter = new AdapterDatos(ListaBTActivity.this, s1, s2);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ListaBTActivity.this));
                }
            }
        }else{
            Toast.makeText(ListaBTActivity.this, "El dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == RESULT_OK ) {
            if ( requestCode == 243 ) {
                if( bluetoothAdapter.isEnabled() ){
                    // Obtenemos la lista de dispositivos sincronizados
                    Set<BluetoothDevice> dispositivosSync = bluetoothAdapter.getBondedDevices();

                    // Si hay dispositivos sincronizados
                    if(dispositivosSync.size() > 0){
                        int i =0;
                        s1 = new String[dispositivosSync.size()];
                        s2 = new String[dispositivosSync.size()];
                        // Llenamos el array de dispositivos para pasarlo al adapter
                        for(BluetoothDevice dispositivo : dispositivosSync){
                            //dispositivos.add(new ItemDispositivo(dispositivo.getName(),  dispositivo.getAddress()));
                            //dapterDispositivos.notifyDataSetChanged();
                            Log.d("Debug", "Nombre: "+ dispositivo.getName() + " MAC: "+dispositivo.getAddress());
                            s1[i] = dispositivo.getName().toString();
                            s2[i] = dispositivo.getAddress().toString();
                            i++;
                        }
                        recyclerView = findViewById(R.id.recyclerView);
                        AdapterDatos myAdapter = new AdapterDatos(ListaBTActivity.this, s1, s2);
                        recyclerView.setAdapter(myAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ListaBTActivity.this));
                    }


                }
            }
        }

    }
}
