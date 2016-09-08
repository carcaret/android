package practicas.cursoandroid.practicasandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import practicas.cursoandroid.practicasandroid.binder.RpcMessage;
import practicas.cursoandroid.practicasandroid.binder.RpcMessageImpl;

public class RpcMessagingService extends Service {

    private RpcMessage rpcMessage;

    @Override
    public void onCreate() {
        super.onCreate();
        rpcMessage = new RpcMessageImpl(this);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return rpcMessage;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
