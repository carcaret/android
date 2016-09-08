package practicas.cursoandroid.practicasandroid.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import practicas.cursoandroid.practicasandroid.R;
import practicas.cursoandroid.practicasandroid.binder.RpcMessage;
import practicas.cursoandroid.practicasandroid.handler.MyMessage;
import practicas.cursoandroid.practicasandroid.service.MsgMessagingService;
import practicas.cursoandroid.practicasandroid.service.RpcMessagingService;
import practicas.cursoandroid.practicasandroid.service.SynchronizationService;

public class TestServicesActivity extends AppCompatActivity {

    private RpcMessage rpcMessage;
    private Messenger serviceMessenger;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(name.getClassName().equals(RpcMessagingService.class.getName())) {
                rpcMessage = (RpcMessage) service;
            } else if(name.getClassName().equals(MsgMessagingService.class.getName())) {
                serviceMessenger = new Messenger(service);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // Se invoca si se produce algun error durante el uso del servicio, no cuando
            // se llama a unbindService()
            rpcMessage = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_services);
    }

    public void synchronize(View view) {
        Intent intent = new Intent(this, SynchronizationService.class);
        startService(intent);
    }

    public void bindRpc(View view) {
        Intent intent = new Intent(this, RpcMessagingService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void unbindRpc(View view) {
        unbindService(serviceConnection);
    }

    public void testRpc(View view) {
        if(rpcMessage != null) {
            rpcMessage.send("963854125", "Mensaje de test RPC");
            Toast.makeText(this, "Llamada RPC hecha", Toast.LENGTH_LONG).show();
        }
    }

    public void bindMsg(View view) {
        Intent intent = new Intent(this, MsgMessagingService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void unbindMsg(View view) {
        unbindService(serviceConnection);
        serviceMessenger = null;
    }

    public void testMsg(View view) {
        if(serviceMessenger != null) {
            try {
                serviceMessenger.send(Message.obtain(null, MsgMessagingService.SEND_MESSAGE,
                        new MyMessage("963852147", "Mensaje de test MSG")));
                Toast.makeText(this, "Llamada MSG hecha", Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
