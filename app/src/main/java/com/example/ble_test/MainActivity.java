package com.example.ble_test;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private Button On, Off;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("MainActivity","onCreate_Start");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        On = (Button) findViewById(R.id.onBluetooth);
        On.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnBluetooth(v);
            }
        });

        Off = (Button) findViewById(R.id.offBluetooth);
        Off.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                OffBluetooth(v);
            }
        });

        lv = (ListView)findViewById(R.id.listView1);
        BA = BluetoothAdapter.getDefaultAdapter();

        Log.v("MainActivity","onCreate_End");
    }

    public void OnBluetooth(View view){
        Log.v("MainActivity","OnBluetooth_Start");

        if(!BA.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned On", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Already On", Toast.LENGTH_LONG).show();
        }

        Log.v("MainActivity","OnBluetooth_End");
    }

    public void list(View view){
        Log.v("MainActivity","list_Start");

        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();
        for(BluetoothDevice bt : pairedDevices)
            list.add(bt.getName());

        Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);

        Log.v("MainActivity","list_End");
    }

    public void OffBluetooth(View view){
        Log.v("MainActivity","OffBluetooth_Start");

        BA.disable();
        Toast.makeText(getApplicationContext(), "Turned Off", Toast.LENGTH_LONG).show();

        Log.v("MainActivity","OffBluetooth_End");
    }
}
