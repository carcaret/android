package practicas.cursoandroid.practicasandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

import practicas.cursoandroid.practicasandroid.handler.MessageHandler;

public class MsgMessagingService extends Service {

    public static final int SEND_MESSAGE = 10000;

    private Messenger messenger;

    @Override
    public void onCreate() {
        super.onCreate();
        messenger = new Messenger(new MessageHandler(this));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
