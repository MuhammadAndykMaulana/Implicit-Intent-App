package com.example.implicitintentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mEditBrowser,mEditLoc, mEditShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditBrowser=findViewById(R.id.editTextBrowser);
        mEditLoc=findViewById(R.id.editTextOpenLoc);
        mEditShare=findViewById(R.id.editTextShare);
    }

    public void BukaBrowser(View view) {
        Intent openBrowser= new Intent(Intent.ACTION_VIEW);
        String url=mEditBrowser.getText().toString();
        //http and https

        if (!url.startsWith("http://") && !url.startsWith("https://")){
            url="http://"+url;
        }

        Uri uri=Uri.parse(url);
        openBrowser.setData(uri);
        Intent chooserIntent= Intent.createChooser(openBrowser,"Open Browser");
        startActivity(chooserIntent);
    }

    public void location(View view) {
        Intent loc=new Intent(Intent.ACTION_VIEW);
        String address= mEditLoc.getText().toString();

        //geo: lat,lgn?q=this+address
        Uri uri= Uri.parse("geo:0,0?q="+address.replaceAll(" ","+"));
        loc.setData(uri);
        startActivity(loc);
    }

    public void sharemsg(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        String message= mEditShare.getText().toString();
        shareIntent.putExtra(Intent.EXTRA_TEXT,message);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }
}
