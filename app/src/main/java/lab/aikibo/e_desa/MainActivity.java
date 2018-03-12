package lab.aikibo.e_desa;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
            //getActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        WebView wv = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        PanicButton panicBtn = new PanicButton(this);
        panicBtn.init();
        wv.addJavascriptInterface(panicBtn, "PanicButton");

        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });


        wv.loadUrl("https://conceptmedia-app.com/edesa-mobile");
        //wv.loadUrl("https://labsroom.000webhostapp.com/test.html");
    }
}
