package rest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Consome {

	public  StringBuilder teste = new StringBuilder();
	 ObjectNode node = null;
	 ObjectMapper mapper = new ObjectMapper();
	public  Alien[] x; 
	public  Alien x1; 
	public String s = "";
	// http://localhost:8080/RESTfulExample/json/product/get
	public Alien[] getList() {

	  try {

		URL url = new URL("http://localhost:8080/demorest/webapi/aliens");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String line = null;
		while ((line = br.readLine()) != null) {
			teste.append(line);
		}
		
		//node = new ObjectMapper().readValue(teste.toString(), ObjectNode.class);
		//a = (List<Alien>) mapper.readValue(teste.toString(), Alien.class);
		x = mapper.readValue(teste.toString(), Alien[].class);
		String output;
		System.out.println("Output from Server .... \n");
		for  (Alien aa: x) {
		System.out.println("ID - " + aa.getId());
		System.out.println("Nome - " + aa.getName());
		System.out.println("Nota - " + aa.getPoints());
		}
	//	while ((output = br.readLine()) != null) {
		//	System.out.println(output);
	//	}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
	  return x;
	}
	  public Alien getAlien(int id) {

		  try {

			URL url = new URL("http://localhost:8080/demorest/webapi/aliens/alien/"+ id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String line = null;
			while ((line = br.readLine()) != null) {
				teste.append(line);
			}
			
			//node = new ObjectMapper().readValue(teste.toString(), ObjectNode.class);
			//a = (List<Alien>) mapper.readValue(teste.toString(), Alien.class);
			x1 = mapper.readValue(teste.toString(), Alien.class);
			String output;
			System.out.println("Output from Server .... \n");
			
			System.out.println("ID - " + x1.getId());
			System.out.println("Nome - " + x1.getName());
			System.out.println("Nota - " + x1.getPoints());
			

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  return x1;
	}
	  public int updAlien(Alien a) throws JsonProcessingException {
		  String retorno = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(a);
		  int i = 0;
		  System.out.println("Imprimindo retorno - ");
		  System.out.println(retorno);

		  try {

			URL url = new URL("http://localhost:8080/demorest/webapi/aliens/upd");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(retorno.toString());
			out.close();
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
            i = conn.getResponseCode();
		//	out.close();
			conn.disconnect();
			

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  return i;
	}
	  public int addAlien(Alien a) throws JsonProcessingException {
		  String retorno = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(a);
		  int i = 0;
		  System.out.println("Imprimindo retorno - ");
		  System.out.println(retorno);

		  try {

			URL url = new URL("http://localhost:8080/demorest/webapi/aliens/alien");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(retorno.toString());
			out.close();
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
            i = conn.getResponseCode();
		//	out.close();
			conn.disconnect();
			

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  return i;
	}

	  public int delAlien(int id) {
           int i = 0;
		  try {

			URL url = new URL("http://localhost:8080/demorest/webapi/aliens/del/"+ id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			i = conn.getResponseCode();
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  return i;
	}


}
