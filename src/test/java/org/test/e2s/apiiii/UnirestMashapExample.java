package org.test.e2s.apiiii;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestMashapExample {

	public static void getRestExample() throws UnirestException {
		HttpResponse<JsonNode> asJson = Unirest.get("http://dummy.restapiexample.com/api/v1/employees").asJson();
		System.out.println(asJson.getStatus());
		System.out.println(asJson.getStatusText());
		System.out.println(asJson.getBody());
	}

	public static void postrequestExample() throws UnirestException {
		HttpResponse<JsonNode> asJson = Unirest.post("http://dummy.restapiexample.com/api/v1/create")
				.body("{\"name\":\"test\",\"salary\":\"100\",\"age\":\"12\"}").asJson();
		System.out.println(asJson.getStatus());
		System.out.println(asJson.getStatusText());
		System.out.println(asJson.getBody());
	}

	public static void putRequestExample() throws UnirestException {
		HttpResponse<JsonNode> asJson = Unirest.put("http://dummy.restapiexample.com/api/v1/update/4835/")
				.body("{\"name\":\"asdf\",\"salary\":\"10\",\"age\":\"1\"}").asJson();
		System.out.println(asJson.getStatus());
		System.out.println(asJson.getStatusText());
		System.out.println(asJson.getBody());
	}

	public static void deleteRequestExample() throws UnirestException {
		HttpResponse<JsonNode> asJson = Unirest.delete("http://dummy.restapiexample.com/api/v1/delete/2/").asJson();
		System.out.println(asJson.getStatus());
		System.out.println(asJson.getStatusText());
		System.out.println(asJson.getBody());
	}

	public static void main(String[] args) throws UnirestException {
		// getRestExample();
		// postrequestExample();
		// putRequestExample();
		// deleteRequestExample();
	}
}
