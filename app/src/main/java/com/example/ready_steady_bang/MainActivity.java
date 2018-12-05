package com.example.ready_steady_bang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity implements MvpContract.MainView {
    MvpContract.MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new PagePresenter(this);

        mPresenter.prepareStore(getApplicationContext());


    }

    public void startGame() {
        Log.d("allowlog", "startgame");
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);

    }

    public void showError() {
        Log.d("AllowResult", "error");
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Error")
                .setMessage("Bad internet connection");
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();

    }

    public void showWebView() {
        WebView vistaWeb = (WebView) findViewById(R.id.webView);
        vistaWeb.setWebChromeClient(new WebChromeClient());
        vistaWeb.setWebViewClient(new WebViewClient());
        vistaWeb.clearCache(true);
        vistaWeb.clearHistory();
        vistaWeb.getSettings().setJavaScriptEnabled(true);
        vistaWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        vistaWeb.loadUrl("https://html5test.com/");
    }


}
