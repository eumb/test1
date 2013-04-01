package com.example.test1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.test1.stiri.loadingTask.Elemente;

public class stiri extends Activity{
private static final String TAG = "Stiri";
public ArrayList<Post> Elem = new ArrayList<Post>();
ProgressDialog ShowProgress;



public void onCreate(Bundle savedInstanceState) {
		
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.dispstire);	
    	Intent stiri = getIntent();
		String display = stiri.getStringExtra(Jsouptest.EXTRA_MESSAGE);
		ShowProgress = ProgressDialog.show(stiri.this, "",
				"Loading. Please wait...", true);
	
		new loadingTask().execute(display);
		
}
					
		class loadingTask extends AsyncTask<String, Void, Elemente> {
						
						
						protected Elemente doInBackground(String...params){
							//Post current = new Post();
											
								try {
									Elemente e = new Elemente();	
									Document html = Jsoup.connect(params[0]).userAgent("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.15 (KHTML, like Gecko) Chrome/24.0.1295.0 Safari/537.15").timeout(0).get();
									
									Elements text = html.select("div[class=SingleContentWrapper-450px]");
									//Log.d(TAG,String.format("%s: %s", "DEsc on post doc", text));
										String desc = text.toString();
										Log.d(TAG,String.format("%s: %s", "Desc on post doc", desc));
										e.newsdesc=desc;
									
									Elements title = html.select("div[class=Pic] [title]");
									
									//Log.d(TAG,String.format("%s: %s", "Title on post doc", title));
										String chars = title.attr("title");
									
									//Log.d(TAG,String.format("%s: %s", "Title on post chars", chars));
										e.newstitle=chars;
									
								
									Elements image = html.select("div[class=Pic] img[src$=.jpg]");
										String href = image.attr("src");
										URLConnection conn = new URL( href ).openConnection();
										conn.connect();
										Bitmap poza = BitmapFactory.decodeStream( conn.getInputStream() );
										e.bitmap=poza;
									
									return e;
										
							} catch (MalformedURLException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
								return null;
							

						}
						
						protected void onPostExecute(Elemente e) {
							
							Log.d(TAG,String.format("%s: %s", "Title on post", e.newsdesc)); 
							TextView tv = (TextView) findViewById(R.id.textstire);		
							tv.setText(Html.fromHtml(e.newsdesc));
							
							Log.d(TAG,String.format("%s: %s", "Title on post", e.newstitle)); 
							
							TextView tv1 = (TextView) findViewById(R.id.texttitlu);		
							tv1.setText(e.newstitle);
							ImageView imageView = (ImageView) findViewById(R.id.thumbstire);
							 if( imageView != null ) {
							      imageView.setImageBitmap( e.bitmap );
							    }
							 ShowProgress.dismiss();
						}		
						public class Elemente
						{
						    public String newsdesc;
						    public String newstitle;
						    public String newsthumb;
						    public Bitmap bitmap;
						    
						   
						}
					
							

}
}

