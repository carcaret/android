package practicas.cursoandroid.practicasandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Messenger;

import practicas.cursoandroid.practicasandroid.handler.MessageHandler;

public class MsgMessagingService extends Service {

    public static final int SEND_MESSAGE = 10000;

    private Messenger messenger;
    private HandlerThread handlerThread;

    @Override
    public void onCreate() {
        super.onCreate();
        handlerThread = new HandlerThread("MsMsgMessagingServicegM");
        handlerThread.start();
        messenger = new Messenger(new MessageHandler(this, handlerThread.getLooper()));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
