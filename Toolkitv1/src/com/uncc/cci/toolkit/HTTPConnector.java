package com.uncc.cci.toolkit;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class HTTPConnector {

	//	static String http_req = "http://192.168.2.2:8084/BEMWebserver/webservice";
	//static String http_req = "http://152.15.96.157:8084/BEMWebserver/webservice";
	static String http_req = "http://152.15.97.236:8080/webservice";
	HttpResponse response;

	public void submitCampaign(String fileName) {
		System.out.println("connecting");
		HttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(http_req);

		System.out.println("creating file object");
		File file = new File(fileName);

		System.out.println("file object created");
		MultipartEntity mpEntity = new MultipartEntity();
		ContentBody cbFile = new FileBody(file, "txt");
		mpEntity.addPart("userfile", cbFile);

		httppost.setEntity(mpEntity);
		System.out.println("executing request " + httppost.getRequestLine());
		response = null;

		try {
			response = httpclient.execute(httppost);
			System.out.println("something after that");
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}
}
