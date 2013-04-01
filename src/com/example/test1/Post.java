package com.example.test1;

import android.util.Log;


public class Post {

	static final String TAG = "Post";
	private String title;
	private String thumbnail;
	private String url;
	private String description;
	private String pubDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		//Log.d(TAG,String.format("%s: %s", "Title", title )); //display status in the log
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		
	}

	public String getUrl() {
		return url;
		
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescription(String description) {
		this.description = description;
		//Log.d(TAG,String.format("%s: %s", "Post", description )); //display status in the log
		
	}

	public String getDescription() {
		return description;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getPubDate() {
		return pubDate;
	}

}
