package com.example.test1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class JustifiedTextView extends WebView{
    private String core      = "<html><body style='text-align:justify;color:rgba(%s);font-size:%dpx;margin: 0px 0px 0px 0px;'>%s</body></html>";
    private String text;
    private int textColor;
    private int backgroundColor;
    private int textSize;

    public JustifiedTextView(Context context, String stire) {
        super(context);
        
    }


    private void init(String s) { 
        this.setWebChromeClient(new WebChromeClient(){});
        reloadData();
    }

    public void setText(String s){
        if(s==null)
            this.text="";
        else
            this.text = s;
        reloadData();
    }

    @SuppressLint("NewApi")
    private void reloadData(){

        if(text!=null) {
            String data = String.format(text);
           
            this.loadDataWithBaseURL(null, data, "text/html","utf-8", null);
        }

        // set WebView's background color *after* data was loaded.
       // super.setBackgroundColor(backgroundColor);

        // Hardware rendering breaks background color to work as expected.
        // Need to use software renderer in that case.
        if(android.os.Build.VERSION.SDK_INT >= 11)
            this.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
    }

}