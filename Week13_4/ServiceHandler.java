package com.example.week13_4;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.HttpUrl;

public class ServiceHandler {

	public static final int GET = 1;
	public static final int POST = 2;

	private final OkHttpClient client = new OkHttpClient();

	public ServiceHandler() {
		// Empty constructor
	}

	public String makeServiceCall(String url, int method) {
		return makeServiceCall(url, method, null);
	}

	public String makeServiceCall(String url, int method, List<Param> params) {
		Request request;
		Response response;

		try {
			if (method == POST) {
				FormBody.Builder formBuilder = new FormBody.Builder();
				if (params != null) {
					for (Param param : params) {
						formBuilder.add(param.getName(), param.getValue());
					}
				}
				RequestBody body = formBuilder.build();
				request = new Request.Builder()
						.url(url)
						.post(body)
						.build();

			} else { // GET
				HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
				if (params != null) {
					for (Param param : params) {
						urlBuilder.addQueryParameter(param.getName(), param.getValue());
					}
				}
				request = new Request.Builder()
						.url(urlBuilder.build())
						.get()
						.build();
			}

			response = client.newCall(request).execute();
			Log.d("ServiceHandler", "Response code: " + response.code());
			return response.body() != null ? response.body().string() : null;

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("ServiceHandler", "IOException: " + e.getMessage(), e);
			return null;
		}
	}
}
