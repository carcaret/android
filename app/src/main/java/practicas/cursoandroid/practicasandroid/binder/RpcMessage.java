package practicas.cursoandroid.practicasandroid.binder;

import android.os.IBinder;

public interface RpcMessage extends IBinder {
    void send(String destination, String message);
}
