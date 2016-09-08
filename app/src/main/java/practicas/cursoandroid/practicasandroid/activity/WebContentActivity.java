package practicas.cursoandroid.practicasandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import practicas.cursoandroid.practicasandroid.R;
import practicas.cursoandroid.practicasandroid.util.RawReader;

public class WebContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_content);
        WebView web = (WebView) findViewById(R.id.web_content);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setAppCacheEnabled(true);
        //web.loadUrl("http://android.awslabstest.net/web.html");
        web.loadData(
                RawReader.readRaw(this.getResources().openRawResource(R.raw.web)),
                "text/html", "UTF-8");
        web.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void mostrarMensaje(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }, "codigoAndroid");
    }
}
