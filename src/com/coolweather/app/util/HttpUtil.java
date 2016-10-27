package com.coolweather.app.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	public static void sendHttpRequest (final String address, final HttpCallbackListener listener) {
		new Thread (new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient connection = new DefaultHttpClient();
				try {
					HttpGet httpGet = new HttpGet(address);
					HttpResponse httpResponse = connection.execute(httpGet);
					String response = null;
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = httpResponse.getEntity();
						response = EntityUtils.toString(entity, "utf-8");
					}
					if (listener != null) {
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					listener.onError(e);
				} 
			}
			
		}).start();
	}
	
}
