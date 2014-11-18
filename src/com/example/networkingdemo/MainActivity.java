package com.example.networkingdemo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private ImageView ivBitmap;
	private static final String IMAGE_URL = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSlyZrms-Ra4zIVAexKZyG5_ivl5u_UdMa0bDPB5Er8aYyIwuAuGw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //disableStrictPolicy();
        
        setContentView(R.layout.activity_main);
        ivBitmap = (ImageView) findViewById(R.id.ivBitmap);
        
        //Bitmap bm = downloadImage(IMAGE_URL);
        //ivBitmap.setImageBitmap(bm);
        
        //new ImageAsyncLoader().execute(IMAGE_URL);
        
        downloadImageAsync(IMAGE_URL);
    }

    private void downloadImageAsync(String imageUrl) {
		AsyncHttpClient client = new AsyncHttpClient();
		String[] imageTypes = new String[] { "image/jpeg" };
		client.get(imageUrl, new BinaryHttpResponseHandler(imageTypes) {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] imageBytes) {
				InputStream in = new ByteArrayInputStream(imageBytes);
				Bitmap bmp = BitmapFactory.decodeStream(in);
				ivBitmap.setImageBitmap(bmp);
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				Toast.makeText(getBaseContext(), "Image could not be found!", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/*
    private Bitmap downloadImage(String imageUrl) {    	
    	// Convert string to url    	
    	URL url = getUrlFromString(imageUrl);    			
    	// Get input stream    	
    	InputStream in = getInputStream(url);    	
    	// Decode bitmap    	
    	Bitmap bmp = decodeBitmap(in);    	
    	// return bitmap result    	
    	return bmp;
	}
    
    
    // Returns the URL object based on the address given
 	private URL getUrlFromString(String imageUrl) {
 		URL url;
 		try {
 			url = new URL(imageUrl);
 		} catch (MalformedURLException e) {
 			url = null;
 		}
 		return url;
 	}
 	
    // Returns an input stream by connecting to the given URL
 	private InputStream getInputStream(URL url) {
 		InputStream in;
 		
 		// Open connection
 		URLConnection conn;
 		
 		try {
 			conn = url.openConnection();
 			conn.connect();
 			in = conn.getInputStream();
 		} catch (IOException e) {
 			in = null;
 		}
 		
 		return in;
 	}

    // Convert the input stream into a Bitmap object using BitmapFactory
    private Bitmap decodeBitmap(InputStream in) {
		Bitmap bitmap;
		// Turn response into Bitmap
		bitmap = BitmapFactory.decodeStream(in);
		try {
			// Close the input stream
			in.close();
		} catch (IOException e) {
			bitmap = null;
		}
		return bitmap;
	}
    */
    
//    private void disableStrictPolicy() {
//    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
//    }
    
    /*
    private class ImageAsyncLoader extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... addresses) {
			// Convert string to url    	
	    	URL url = getUrlFromString(addresses[0]);    			
	    	// Get input stream    	
	    	InputStream in = getInputStream(url);    	
	    	// Decode bitmap    	
	    	Bitmap bmp = decodeBitmap(in);    	
	    	// return bitmap result    	
	    	return bmp;
		}
		
		// Returns the URL object based on the address given
	 	private URL getUrlFromString(String imageUrl) {
	 		URL url;
	 		try {
	 			url = new URL(imageUrl);
	 		} catch (MalformedURLException e) {
	 			url = null;
	 		}
	 		return url;
	 	}
	 	
	    // Returns an input stream by connecting to the given URL
	 	private InputStream getInputStream(URL url) {
	 		InputStream in;
	 		
	 		// Open connection
	 		URLConnection conn;
	 		
	 		try {
	 			conn = url.openConnection();
	 			conn.connect();
	 			in = conn.getInputStream();
	 		} catch (IOException e) {
	 			in = null;
	 		}
	 		
	 		return in;
	 	}

	    // Convert the input stream into a Bitmap object using BitmapFactory
	    private Bitmap decodeBitmap(InputStream in) {
			Bitmap bitmap;
			// Turn response into Bitmap
			bitmap = BitmapFactory.decodeStream(in);
			try {
				// Close the input stream
				in.close();
			} catch (IOException e) {
				bitmap = null;
			}
			return bitmap;
		}
    	
	    // In the UIThread
	    @Override
	    protected void onPostExecute(Bitmap result) {
	    	// set bitmap image for the result
	    	ivBitmap.setImageBitmap(result);
	    }
    }
    */

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
