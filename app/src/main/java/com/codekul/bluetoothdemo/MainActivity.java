package com.codekul.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT =     5462   ;
    private static final int REQUEST_VISIBLE_BT = 4879;

    //private Button btnDiscover; 48:13:7E:56:1D:31

    private final BroadcastReceiver receiverDiscovery =
            new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice device =
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            Log.i("@codekul","Name - "+device.getName());
            Log.i("@codekul","Address - "+device.getAddress());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BluetoothAdapter adapter =
                BluetoothAdapter.getDefaultAdapter();

        initBluetooth(adapter);

        findViewById(R.id.btnDiscover).setOnClickListener(new
                View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        discoverDevices(adapter);
                    }
                });

        findViewById(R.id.btnVisible)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        enableVisibility();
                    }
                });

        findViewById(R.id.btnListen).setOnClickListener(new
            View.OnClickListener(){
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            listen(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        findViewById(R.id.btnAccept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            accept(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ENABLE_BT){

            if(resultCode == RESULT_OK){
                mt("Bluetooth enabled successfully ...");
            }
        }
        if(requestCode == REQUEST_VISIBLE_BT){
            if(resultCode == RESULT_OK){
                mt("Bluetooth is visible .. ");
            }
        }
    }

    private final void initBluetooth(BluetoothAdapter adapter) {

        if(adapter != null){
            if(!adapter.isEnabled()) fireEnableBtIntent();
            //else discoverDevices(adapter);
        }
        else mt("Bluetooth not Supported");

    }

    private final void fireEnableBtIntent() {
        Intent enableBtIntent =
                new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    private final void showListOfBondedDevices(BluetoothAdapter adapter){

        Set<BluetoothDevice> bondedDevices =
                adapter.getBondedDevices();

        final TextView textInfo =
                (TextView) findViewById(R.id.textInfo);

        textInfo.setText("");
        for(BluetoothDevice device : bondedDevices){

            textInfo.append("\n Name "+device.getName());
            textInfo.append("\n MAC "+device.getAddress());
            textInfo.append("\n -------------------------");
        }
    }

    private final void mt(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private final void discoverDevices(BluetoothAdapter adapter) {

        Log.i("@codekul","starting discovery;");

        IntentFilter filter =
                new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiverDiscovery, filter);

        adapter.startDiscovery();
    }

    private final void enableVisibility(){

        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivityForResult(discoverableIntent,REQUEST_VISIBLE_BT);
    }

    private final void listen(BluetoothAdapter adapter) throws Exception{

        BluetoothServerSocket bss =
                adapter.listenUsingRfcommWithServiceRecord("chat",
                        UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));

        BluetoothSocket bs = bss.accept();

        OutputStream os = bs.getOutputStream();
        os.write("Welcome to Bt Chat".getBytes());
        os.close();

        bs.close();
        bss.close();
    }

    private final void accept(BluetoothAdapter adaper) throws Exception{

        BluetoothDevice remoteDevice =
                adaper.getRemoteDevice("48:13:7E:56:1D:31");

        BluetoothSocket socket =
                remoteDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));

        socket.connect();

        InputStream is = socket.getInputStream();
        StringBuilder builder =
                new StringBuilder();

        while(true){

            int ch = is.read();
            if(ch == -1) break;
            else builder.append(""+(char)ch);
        }

        Log.i("@codekul","------------>"+builder.toString());


        is.close();
        socket.close();
    }
}
