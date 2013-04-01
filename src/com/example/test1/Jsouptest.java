package com.example.test1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


public class Jsouptest extends Activity{	
	static final String TAG = "MAIN";
	public static final String EXTRA_MESSAGE = "";
	
	public ArrayList<Post> PostList = new ArrayList<Post>();
	ListView lv1;
	public String chars = new String();
	public String chars1 = new String();
	public String href = new String();
	ProgressDialog ShowProgress;
	
	public void onCreate(Bundle savedInstanceState) {
		
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.activity_jsoup);			//not needed when using listview
        lv1 = (ListView) findViewById(R.id.listView1);
       


        ShowProgress = ProgressDialog.show(Jsouptest.this, "",
				"Loading. Please wait...", true);
        new loadingTask().execute("http://www.tion.ro/");
        
       
           
        
    	
      //Opens the clicked news
        lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				
				Intent stiri = new Intent(Jsouptest.this,stiri.class);
				final String mesaj1 = PostList.get(position).getUrl();
				stiri.putExtra(EXTRA_MESSAGE, mesaj1);
				startActivity(stiri);
				
				
				
				
			}
		});
	
} 

	class loadingTask extends AsyncTask<String, Void, String> {
		
		
		protected String doInBackground(String...params){
			
			Post currentPost = new Post();
			
			Document doc;		
				try {
						
						doc = Jsoup.connect(params[0]).userAgent("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.15 (KHTML, like Gecko) Chrome/24.0.1295.0 Safari/537.15").timeout(0).get();
				
						int index;
						for (index=0;index<=2;index++){
						
						Element text = doc.select("div[class=TeaserContent]").get(index);
							String chars1 = text.text();
							currentPost.setDescription(chars1);
							//Log.d(TAG,String.format("%s: %s", "Description", chars1)); //display status in the log
						Element title = doc.select("h2,h3,h4 a[title]").get(index);
							String chars = title.text();
							currentPost.setTitle(chars);
							//Log.d(TAG,String.format("%s: %s", "Title", chars)); //display status in the log
						Element image = doc.select("span[class=link] img[src$=.jpg]").get(index);
							String href = image.attr("src");
							currentPost.setThumbnail(href);
							//Log.d(TAG,String.format("%s: %s", "IMG link", href)); //display status in the log
						Element link = doc.select("h2 a[href],h3 a[href],h4 a[href]").get(index);
							String url = link.attr("href");
							Log.d(TAG,String.format("%s: %s", "IMG link", url)); //display status in the log
							currentPost.setUrl(url);
							
						PostList.add(currentPost);
						currentPost = new Post();
						}
					
						
						
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";

		}
		
		protected void onPostExecute(String s) {
			
			
			
			EfficientAdapter adapter=new EfficientAdapter(Jsouptest.this, PostList);
			
			
			lv1.setAdapter(adapter);
			ShowProgress.dismiss();
			
		}
		
		 
	 }  
	



}
		
