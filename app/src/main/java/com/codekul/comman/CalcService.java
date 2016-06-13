package com.codekul.comman;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class CalcService extends Service {

    private MyCalc calc = new MyCalc();

    public CalcService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return calc;
    }

    private class MyCalc extends ICalc.Stub {

        @Override
        public int calc(int num1, int num2, char operator) throws RemoteException {
            return operator == '+' ? num1 + num2 : num1 * num2 ;
        }
    }
}
