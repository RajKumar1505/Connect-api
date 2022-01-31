package org.test.e2s.apiiii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HTTPURLConnectionExample {
	public static void GetMethodExample() throws IOException {
		URL url = new URL("http://dummy.restapiexample.com/api/v1/employees");
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.connect();

		// Response code
		int responseCode = httpURLConnection.getResponseCode();
		System.out.println(responseCode);

		// Response Message
		String responseMessage = httpURLConnection.getResponseMessage();
		System.out.println(responseMessage);

		// For Body

		// get all value in body
		InputStream inputStream = httpURLConnection.getInputStream();

		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String readLine;
		StringBuffer stringBuffer = new StringBuffer();
		while ((readLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(readLine);
		}
		System.out.println(stringBuffer);
	}

	public static void PostMethodExample() throws IOException {

		URL url = new URL("http://dummy.restapiexample.com/api/v1/create");
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("POST");

		httpURLConnection.setRequestProperty("Content-Type", "application/json");

		httpURLConnection.setDoOutput(true);

		String jsonBody = "{\"name\":\"TEST\",\"salary\":\"321\",\"age\":\"32\"}";

		byte[] inputJsonBytes = jsonBody.getBytes();

		OutputStream outputStream = httpURLConnection.getOutputStream();
		outputStream.write(inputJsonBytes);

		System.out.println(httpURLConnection.getResponseCode());
		System.out.println(httpURLConnection.getResponseMessage());

		InputStream inputStream = httpURLConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String readLine;

		StringBuffer stringBuffer = new StringBuffer();
		while ((readLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(readLine);
		}
		System.out.println(stringBuffer);
	}

	public static void putMethodExample() throws IOException {

		
		URL url = new URL("http://dummy.restapiexample.com/api/v1/update/1830/");
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("PUT");
		
		httpURLConnection.setRequestProperty("Content-Type", "application/json");

		httpURLConnection.setDoOutput(true);

		String jsonBody = "{\"name\":\"TEST\",\"salary\":\"321\",\"age\":\"32\"}";

		byte[] inputJsonBytes = jsonBody.getBytes();

		OutputStream outputStream = httpURLConnection.getOutputStream();
		outputStream.write(inputJsonBytes);

		System.out.println(httpURLConnection.getResponseCode());
		System.out.println(httpURLConnection.getResponseMessage());

		InputStream inputStream = httpURLConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String readLine;

		StringBuffer stringBuffer = new StringBuffer();
		while ((readLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(readLine);
		}
		System.out.println(stringBuffer);
		
		
	}
	
	public static void deleteMethodExample() throws IOException {
		URL url = new URL("http://dummy.restapiexample.com/api/v1/delete/1830/");
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("DELETE");

		httpURLConnection.setRequestProperty("Content-Type", "application/json");

		httpURLConnection.setDoOutput(true);
		
		System.out.println(httpURLConnection.getResponseCode());
		System.out.println(httpURLConnection.getResponseMessage());

		InputStream inputStream = httpURLConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String readLine;

		StringBuffer stringBuffer = new StringBuffer();
		while ((readLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(readLine);
		}
		System.out.println(stringBuffer);

	}
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		deleteMethodExample();
	}

}