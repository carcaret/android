package practicas.cursoandroid.practicasandroid.binder;

import android.content.Context;
import android.os.Binder;
import android.widget.Toast;

public class RpcMessageImpl extends Binder implements RpcMessage {

    private Context context;

    public RpcMessageImpl(Context context) {
        this.context = context;
    }

    @Override
    public void send(String destination, String message) {
        Toast.makeText(
                context, String.format("Mensaje: %s - %s", destination, message), Toast.LENGTH_LONG)
                .show();
    }
}
