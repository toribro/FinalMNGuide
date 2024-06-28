package com.kh.mng.common.gpt.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.mng.common.gpt.prompt.Prompt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("http://localhost:3000")
@Controller
public class GptController {
  
	//private ApiKey apiKey= new ApiKey();
	
	
	@Value("${api.apikey}") 
	private String apiKey;
	
	
	
	@ResponseBody
	@GetMapping(value="chat",produces="application/json; charset=UTF-8")
	public String GptGet(@RequestParam(value="prompt",defaultValue="hi") String prompt) throws IOException {
		String question=sendMessage(prompt);
		String url="https://api.openai.com/v1/chat/completions";
		
		
		//1. 요청하고자하는 url을 전달하면서 java,net.URL객체 생성
		
		URL requestUrl =new URL(url);
	   //2.만들어진 URL 객체를 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection =(HttpURLConnection)requestUrl.openConnection();
		//3.요청에 필요한 header 설정하기
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("Authorization", "Bearer "+apiKey);
		urlConnection.setDoOutput(true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
		bw.write(question);
		bw.flush();
		bw.close();
		
		//4. 해당 api서버로 요청 보낸후 입력데이터 읽어오기
		 BufferedReader br =new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		 
		 
		 String responseMessage="";
	        String line="";
	        while((line = br.readLine())!=null) {
	        	responseMessage+=line;
	        }
	        
	        br.close();
	        urlConnection.disconnect();
	        
	        System.out.println(responseMessage);
	        return responseMessage;
	}
	
	
	

	@ResponseBody
	@PostMapping(value="chat",produces="application/json; charset=UTF-8")
	public String GptPost(@RequestBody Prompt prompt) throws IOException {
		System.out.println("요청");
		System.out.println(prompt.getPrompt());
		String question=sendMessage(prompt.getPrompt());
		System.out.println(question);
		String url="https://api.openai.com/v1/chat/completions";
		
		
		//1. 요청하고자하는 url을 전달하면서 java,net.URL객체 생성
		URL requestUrl =new URL(url);
	   //2.만들어진 URL 객체를 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection =(HttpURLConnection)requestUrl.openConnection();
		//3.요청에 필요한 header 설정하기
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("Authorization", "Bearer "+apiKey);
		urlConnection.setDoOutput(true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
		bw.write(question);
		bw.flush();
		bw.close();
		
		//4. 해당 api서버로 요청 보낸후 입력데이터 읽어오기
		 BufferedReader br =new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		 
		 
		 String responseMessage="";
	        String line="";
	        while((line = br.readLine())!=null) {
	        	responseMessage+=line;
	        }
	        
	        br.close();
	        urlConnection.disconnect();
	        
	        System.out.println(responseMessage);
	        return responseMessage;
	}
	
	
	private String sendMessage(String prompt) {
		String sendMessage="{"
				+ "\"model\":"+"\"gpt-3.5-turbo\","
				+ " \"messages\":"+"[" + 
						"{" + 
						"\"role\": \"system\"," + 
						" \"content\": \""+content()+"\" " + 
						" },"
						
						+ "{" + 
						"\"role\": \"user\"," + 
						"\"content\":\""+prompt+"\"" + 
						"}" + 
						"],"
				+"\"temperature\":"+"1,"
				+"\"max_tokens\":"+"1002,"
		        +"\"top_p\":"+"1,"
		        +"\"frequency_penalty\":"+"0,"
		        +"\"presence_penalty\":"+"0"
		        +"}";
		
		return sendMessage;
	}
	
	private String content() {
		return "  나는 반려견 행동 전문가인 AI입니다.사용자가 입력한 반려견 행동에 대한 질문에 답변을 제공합니다." + 
				"   반려견의 행동에 대한 원인, 해결책, 관련된 조언 등을 포함하여 상세히 답변하세요."+
				"해결책을 문단으로 깔끔하게 나눠 번호를 붙여서 대답하시오";
	}
	
	private String sendMessageMap(String prompt) {
		
		Map<String,String> sendMessageMap = new HashMap<String,String>();
		
		sendMessageMap.put("model","gpt-3.5-turbo");
		sendMessageMap.put("temperature","1");
		return null;
		
		
	}
	
} 
