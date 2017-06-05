package com.vanticus.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.vanticus.R;
import com.vanticus.model.MainData;
import com.vanticus.constants.Constants;
import com.vanticus.model.DataList;

import java.util.ArrayList;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    private Menu menu;
    private Toolbar toolbar;
    private TextView mTitle;
    private boolean isOn  = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient());
        setUpActionBar();
        display();
    }

    private void setUpActionBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setElevation(7);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nightmode, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_nightmode) {
                toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void toggle() {
        MenuItem item = menu.findItem(R.id.action_nightmode);
            if (isOn == false) {
                item.setIcon(R.drawable.on);
                changeLightMode();
                isOn = true;
            } else {
                item.setIcon(R.drawable.off);
                changeLightMode();
                isOn = false;
            }
    }
//changes view mode
    private void changeLightMode() {
        webView.loadUrl("javascript:(function(){l=document.getElementsByClassName('toggle');e=document.createEvent('HTMLEvents');e.initEvent('click',true,true);l[0].dispatchEvent(e);})()");
    }

    public void loadWebUrl(String text, String url) {
        mTitle.setText(text);
        webView.loadUrl(url);
    }
    public void display() {
        loadWebUrl(((DataList) ((ArrayList) MainData.totalData.get(Constants.catIndex)).get(Constants.catIndex2)).getName(), ((DataList) ((ArrayList) MainData.totalData.get(Constants.catIndex)).get(Constants.catIndex2)).getUrl());
    }
}
