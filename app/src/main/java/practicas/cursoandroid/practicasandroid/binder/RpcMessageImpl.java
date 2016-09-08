package practicas.cursoandroid.practicasandroid.binder;

import android.content.Context;
import android.os.Binder;
import android.os.Looper;
import android.widget.Toast;

public class RpcMessageImpl extends Binder implements RpcMessage {

    private Context context;

    public RpcMessageImpl(Context context) {
        this.context = context;
    }

    @Override
    public void send(final String destination, final String message) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(
                        context,
                        String.format("Mensaje: %s - %s", destination, message), Toast.LENGTH_LONG)
                        .show();
                Looper.loop();
            }
        };
        new Thread(runnable).start();
    }
}
