package com.codekul.comman;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ICalc calc = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnBind).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction("com.codekul.mycalc");

                bindService(convertImplicitIntentToExplicitIntent(intent), new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        calc = ICalc.Stub.asInterface(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                        Log.i("@codekul","Disconneced");
                    }
                },BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try {
                    int addition = calc.calc(10,10,'+');
                    Log.i("@codekul","Addition is "+addition);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Intent convertImplicitIntentToExplicitIntent(Intent implicitIntent) {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);

        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
