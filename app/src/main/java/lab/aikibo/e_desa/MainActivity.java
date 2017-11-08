package lab.aikibo.e_desa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        wv.setWebViewClient(new WebViewClient());

        wv.loadUrl("http://conceptmedia-app.com/e-desa/");
    }
}
