package org.windycitygo.windycitygo.util;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    
	public DownloadImageTask(ImageView imageView) {
    	this.imageView = imageView;
    }
    
	protected Bitmap doInBackground(String... urls) {
	     return Network.downloadRoundedBitmap(urls[0]);
	}

	protected void onPostExecute(Bitmap result) {
		if(imageView != null)
			imageView.setImageBitmap(result);
	}
}
