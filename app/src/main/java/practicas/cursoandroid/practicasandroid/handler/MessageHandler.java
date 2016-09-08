package practicas.cursoandroid.practicasandroid.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import practicas.cursoandroid.practicasandroid.service.MsgMessagingService;

public class MessageHandler extends Handler {

    private final Context context;

    public MessageHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case MsgMessagingService.SEND_MESSAGE:
                MyMessage message = (MyMessage) msg.obj;
                Toast.makeText(
                        context,
                        String.format("Mensaje enviado: %s - %s",
                                message.getDestination(), message.getMessage()),
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}
