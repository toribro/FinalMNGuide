package testApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestApi {
   
	@Setter
	@Getter
	@AllArgsConstructor
	static class Businesses {
		private String b_no;
		private String start_dt;
		private String p_nm;
		private String p_nm2;
		private String b_nm;
		private String corp_no;
		private String b_sector;
		private String b_type;
		private String b_adr;
	}
	
	@Setter
	@Getter
	static class Data{
		
		private ArrayList<Businesses> businesses;
	}
	
	
	
	private static final String KEY="";
	public static void main(String[] args) {
		String url = "https://api.odcloud.kr/api/nts-businessman/v1/validate";
		url+="?serviceKey="+KEY;
		url+="&returnType=JSON";
		
		Data data =new Data();
		Businesses b = new Businesses(
				"5770202948",
				"20000101",
				"홍길동",
				"홍길동",
				"(주)테스트",
				"0000000000000",
				 "",
				 "",
				 ""
				);

		ArrayList<Businesses> businesses = new ArrayList();
		businesses.add(b);
		data.setBusinesses(businesses);
		
		
		
		
			try {
				URL requestUrl = new URL(url);
				HttpURLConnection urlConnection =(HttpURLConnection)requestUrl.openConnection();
				urlConnection.setRequestMethod("POST");
				urlConnection.setDoOutput(true);
				urlConnection.setRequestProperty("Content-Type", "application/json charset:UTF-8");
				urlConnection.setRequestProperty("Accept", "application/json charset:UTF-8");
				String JsonInputData=new Gson().toJson(data);
				System.out.println(JsonInputData);
			
				
				
				OutputStream os= urlConnection.getOutputStream();
				byte[] input = JsonInputData.getBytes("utf-8");
				os.write(input,0,input.length);
			
				
				int responseCode = urlConnection.getResponseCode();
				System.out.println(responseCode);
				
				String responseText="";
		        String line="";
		        StringBuffer response =new StringBuffer();
		        
			    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				while((line = br.readLine())!=null) {
						response.append(line);
				}
			
			    br.close();
			     System.out.println(response);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
				
			}catch(ProtocolException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			
	       //데이터 전송
		
		}
}
