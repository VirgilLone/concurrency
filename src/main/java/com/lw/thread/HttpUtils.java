package com.lw.thread;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtils {

	public static final MediaType JSON = MediaType
			.parse("application/json;charset=utf-8");

	public static String post(String url, String json) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public static String get(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
//	public static String get(String url) {
//		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(10000)
//				.setSocketTimeout(30000).build()).build();
//		try {
//			HttpGet httpGet = new HttpGet(url);
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			if(httpResponse.getStatusLine().getStatusCode() == 200) {
//				return EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
//			}
//		} catch (Exception e) {
//		}finally{
//			try {
//				httpClient.close();
//			} catch (IOException e) {
//			}
//		}
//		return null;
//	}
}
