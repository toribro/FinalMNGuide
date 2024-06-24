package testApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import testApi.TestApi.Businesses;
import testApi.TestApi.Data;



public class TestApi2 {
   
	   
	
		
		private static final String KEY="";
		public static void main(String[] args) {
			String url = "https://api.odcloud.kr/api/nts-businessman/v1/status";
			url+="?serviceKey="+KEY;
			url+="&returnType=JSON";
			
	        String data="{\"b_no\": [\"5770202948\"]}";
	    
			
			
				try {
					URL requestUrl = new URL(url);
					HttpURLConnection urlConnection =(HttpURLConnection)requestUrl.openConnection();
					urlConnection.setRequestMethod("POST");
					urlConnection.setDoOutput(true);
					urlConnection.setRequestProperty("Content-Type", "application/json charset:UTF-8");
					urlConnection.setRequestProperty("Accept", "application/json charset:UTF-8");
					
					System.out.println(data);
				
					
					
					OutputStream os= urlConnection.getOutputStream();
					byte[] input = data.getBytes("utf-8");
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
