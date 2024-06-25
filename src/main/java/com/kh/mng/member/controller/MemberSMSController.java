package com.kh.mng.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mng.common.phonesms.PhoneSmsVo;
import com.kh.mng.member.service.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Slf4j
@Controller
public class MemberSMSController {
//	private BossPageServiceImplSecond bossPageServiceSecond;
	@Autowired
	private MemberServiceImpl memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Value("${sms.apiKey}") 
	private String apiKey;
	
	@Value("${sms.apiSecretKey}") 
	private String apiSecretKey;
	
	@Value("${sms.url}") 
	private String apiUrl;
	
	@Value("${sms.sentNum}") 
	private String sentNum;
//	
//	// 인증번호와 맞는지 어떻게 확인할 것인가?
//	private String certifyCode;
//	
	@ResponseBody
	@PostMapping("certification.me")
	public String certifyPhone(String getNum, HttpSession session) {
		String code = String.valueOf((int)(Math.random()*900000 + 100000));
    	String msgText = "멍냥가이드 본인확인 인증번호 " + code;
    	
    	String codeEnc = bcryptPasswordEncoder.encode(code);
    	
		PhoneSmsVo psv = new PhoneSmsVo();
    	
    	psv.setPhone(getNum);
    	psv.setCertifyCode(codeEnc);
    	
    	int result = memberService.insertCertifyCode(psv);
    	
    	if (result > 0) {
    		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, apiUrl);
    		Message message = new Message();
    		
    		message.setTo(getNum);
    		message.setFrom(sentNum);
    		message.setText(msgText);
    		
    		MultipleDetailMessageSentResponse response = null;
    		try {
    			response = messageService.send(message);
    		} catch (NurigoMessageNotReceivedException e) {
    			e.printStackTrace();
    		} catch (NurigoEmptyResponseException e) {
    			e.printStackTrace();
    		} catch (NurigoUnknownException e) {
    			e.printStackTrace();
    		}
    		
    		return "NNNNY";
    	} else {
    		return "NNNNN";
    	}

	}
	
	@ResponseBody
	@PostMapping("checkCertifyCode.me")
	public String checkCertifyCode(String phone, String certifyCode) {
		PhoneSmsVo psv = memberService.checkCertifyCode(phone);
		if (psv != null && bcryptPasswordEncoder.matches(certifyCode, psv.getCertifyCode())) {
			// 인증번호 삭제 메소드 추가
			return "NNNNY";
		} else {
			return "NNNNN";
		}
	}
	
//  public MemberSMSController() { // Parameter specified as non-null is null 오류 (Kotlin에서 null인 값이 들어올 경우의 처리를 안 해줘서 발생하는 오류라고 함)
//	if (apiKey != null && (apiSecretKey != null && apiUrl != null)) {
//        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, apiUrl);
//	} else {
//		this.messageService = null;
//	}
//}
//  Message message = new Message();
//  
//  message.setFrom(sentNum); // 문자 발신번호
//  message.setTo(getNum); // 문자 수신번호
	
	
    
//	@ResponseBody
//    @PostMapping(value="certification.me", produces="application/json; charset-UTF-8")
//    public String sendCertifyCode(String getNum) {
//		
////		 JsonObject params = new JsonObject();
////	        JsonArray messages = new JsonArray();
////
////	        JsonObject msg = new JsonObject();
////	        JsonArray toList = new JsonArray();
////
////	        toList.add("01099403102");
////	        msg.add("to", toList);
////	        msg.addProperty("from", "01099403102");
////	        msg.addProperty("text",
////	                "안녕하세요 줍깅입니다. 오늘 참여하실[같이 줍깅해요!]모임의 모임날짜가 3일 남으셨습니다.");
////	        messages.add(msg);
////
////	        params.add("messages", messages);
//    	
//	
//		
//    	JsonObject msgInfo = new JsonObject();
//    	JsonObject messages = new JsonObject();
//    	
//    	msgInfo.addProperty("to", getNum);
//    	msgInfo.addProperty("from", sentNum);
//    	msgInfo.addProperty("type", "SMS");
//    	log.info(sentNum);
//    	
//    	int ranNum = (int)(Math.random()*900000 + 100000);
//    	String msgText = "멍냥가이드 본인확인 인증번호 " + ranNum;
//    	
//    	msgInfo.addProperty("text", msgText);
//    	
//    	JsonArray messageArr = new JsonArray();
//    	messageArr.add(msgInfo);
//    	messages.add("message", messageArr);
// 
//    	
//    	
//    	
//    	
////    	String parameters = "{\"message\":{\"to\":\""+ getNum +"\",\"from\":\""+sentNum+"\",\"text\":\""+msgText+"\",\"type\":\"SMS\"}}";
//    	
//    	
//    	
//    	
//    	Map<String, String> headerInfo = new HashMap<String, String>();
//		headerInfo.put("Authorization", getHeaders());
//		
//		log.info(messages.toString());
//		log.info(headerInfo.toString());
//		
//		String responseBody = null;
//		try {
//			URL url = new URL(apiUrl);
//			HttpURLConnection con = (HttpURLConnection)url.openConnection();
//			con.setRequestMethod("POST");
//			log.info(headerInfo.toString());
//			responseBody = sendRequest(apiUrl, new Gson().toJson(messages), headerInfo);
////			responseBody = sendRequest(apiUrl, parameters, headerInfo);
//			log.info(new Gson().toJson(messages));
//			log.info(new Gson().toJson(responseBody));
//			
////			JsonObject memberInfo = JsonParser.parseString(responseBody).getAsJsonObject();
////			System.out.println(responseBody);
//////				Response의 값이 필요하므로 다시 꺼내주기
////			JsonObject resObj = memberInfo.getAsJsonObject("response");
////			System.out.println(resObj);
////				데이터베이스의 이메일 정보가 맞는지 확인해주면 됨
//				
//				// 받아온 email과 데이터베이스의 email을 비교하여 가입 유무 확인 후
//				// 가입 되어 있다면 로그인, 아니라면 회원가입 창으로 정보를 가지고 이동
////			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//        
//		return responseBody;
//    }
//    
// 	
//    // API에 POST 요청 보내고 응답을 받아오는 메소드
// 	private static String sendRequest(String apiUrl, String params, Map<String, String> header) throws IOException {
// 		HttpURLConnection conn = connect(apiUrl);
// 		BufferedWriter bw = null;
// 		try {
// 			conn.setRequestMethod("POST");
// 			conn.setDoOutput(true);
// 			for (Map.Entry<String, String> h : header.entrySet()) {
// 				conn.setRequestProperty(h.getKey(), h.getValue()); // conn의 헤더에 키밸류 형식으로 데이터 저장
// 			} 
// 			
// 			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
// 		    wr.writeBytes(params);
// 		    wr.flush();
// 		    wr.close();
// 			
//// 			
//// 			bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//// 			bw.write(params);
////			bw.flush();
////			bw.close();
//			
// 			int responseCode = conn.getResponseCode();
// 			log.info("" + conn.getResponseCode());
// 			if (responseCode == 200) {
// 				return readBody(conn.getInputStream());
// 			} else {
// 				return readBody(conn.getErrorStream());
// 			}
// 		} catch (IOException e) {
// 			throw new RuntimeException("API 요청과 응답 실패 : " + apiUrl, e);
// 		} finally {
// 			conn.disconnect(); // 스트림 닫아준 것, close()
//
// 		}
// 	}
// 	
// 	private static String readBody(InputStream body) {
//		InputStreamReader streamReader = new InputStreamReader(body);
//		
//		try (BufferedReader br = new BufferedReader(streamReader)){
//			StringBuilder responseBody = new StringBuilder();
//			
//			String line;
//			while((line = br.readLine()) != null) {
//				responseBody.append(line);
//			}
//			return responseBody.toString();
//		} catch (IOException e) {
//			throw new RuntimeException("API 응답을 읽는데 실패하였습니다.", e);
//		}
//		
//	}
//    
// // API에 연결하기 위한 HttpURLConnection 객체를 생성하고 반환하는 메소드
//  	private static HttpURLConnection connect(String apiUrl) {
//  		URL url;
//  		try {
//  			url = new URL(apiUrl);
//  			return (HttpURLConnection)url.openConnection();
//  		} catch (MalformedURLException e) {
//  			throw new RuntimeException("API URL이 잘못 되었습니다. : " + apiUrl, e);
//  		} catch (IOException e){
//  			throw new RuntimeException("연결이 실패하였습니다. : " + apiUrl, e);
//  		}
//  	}
//  	
//  	 public String getHeaders() {
//     	log.info("들어옴");
// 		try {
// 			String salt = UUID.randomUUID().toString().replaceAll("-", "");
// 	    	String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString().split("\\[")[0];
// 	    	log.info(apiKey);
// 	    	log.info(apiSecretKey);
// 	    	log.info(apiUrl);
// 	    	log.info(sentNum);
// 	    	Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
// 	    	log.info(new SecretKeySpec(apiSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256").toString());
// 			SecretKeySpec secret_key = new SecretKeySpec(apiSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
// 			
// 	        sha256_HMAC.init(secret_key);
// 	        String signature = new String(Hex.encodeHex(sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8))));
// 	        
// 	        return "HMAC-SHA256 apiKey=" + apiKey + ", date=" + date + ", salt=" + salt + ", signature=" + signature;
// 	    
// 		} catch (NoSuchAlgorithmException e) {
// 			e.printStackTrace();
// 		} catch (InvalidKeyException e) {
// 			e.printStackTrace();
// 		}
// 		return null;
//     }
  	
  	
//  public MemberSMSController() { // Parameter specified as non-null is null 오류 (Kotlin에서 null인 값이 들어올 경우의 처리를 안 해줘서 발생하는 오류라고 함)
//	if (apiKey != null && (apiSecretKey != null && apiUrl != null)) {
//        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, apiUrl);
//	} else {
//		this.messageService = null;
//	}
//}
//  Message message = new Message();
//  
//  message.setFrom(sentNum); // 문자 발신번호
//  message.setTo(getNum); // 문자 수신번호
//  
//  int ranNum = (int)(Math.random()*900000 + 100000);
//  message.setText("[멍냥가이드] 본인확인 인증번호[" + ranNum + "] ");
//    try {
//		
//		URL url = new URL(apiURL);
//		HttpURLConnection con = (HttpURLConnection)url.openConnection();
//		con.setRequestMethod("GET");
//		
//		// HTTP 요청에 대한 응답 코드 확인
//		int responseCode = con.getResponseCode();
//		
//		BufferedReader br;
//		if (responseCode == 200) {
//			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		} else {
//			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//		}
//		
//		// 응답 데이터 읽어오기
//		String inputLine;
//		StringBuffer res = new StringBuffer();
//		while((inputLine = br.readLine()) != null) {
//			res.append(inputLine);
//		}
//		br.close();
//		
//		if(responseCode == 200) {
//			// 정상적으로 정보를 받아왔을 때 result에 정보를 저장
//			String result = res.toString();
////			System.out.println(result);
//			
//			JsonObject totalObj = JsonParser.parseString(result).getAsJsonObject();
//			totalObj.get("access_token");
//			
//			String token = totalObj.get("access_token").getAsString(); // 정보 접근을 위한 토큰
//			String header = "Bearer" + token;
//			
//			apiURL = "https://openapi.naver.com/v1/nid/me";
//			Map<String, String> requestHeaders = new HashMap<String, String>();
//			requestHeaders.put("Autorization", header);
//		
//			String responseBody = get(apiURL, requestHeaders);
//			
//			JsonObject memberInfo = JsonParser.parseString(responseBody).getAsJsonObject();
//			System.out.println(responseBody);
////			Response의 값이 필요하므로 다시 꺼내주기
//			JsonObject resObj = memberInfo.getAsJsonObject("response");
//			System.out.println(resObj);
////			데이터베이스의 이메일 정보가 맞는지 확인해주면 됨
//			
//			// 받아온 email과 데이터베이스의 email을 비교하여 가입 유무 확인 후
//			// 가입 되어 있다면 로그인, 아니라면 회원가입 창으로 정보를 가지고 이동
//		}

//        MultipleDetailMessageSentResponse response = null;
//		try {
//			response = messageService.send(message);
//		} catch (NurigoMessageNotReceivedException | NurigoEmptyResponseException | NurigoUnknownException e) {
//			e.printStackTrace();
//		}
////        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
////        System.out.println(response);
//
//        return response;
//    }
    

//    @GetMapping("certification.me")
//    public String sendCertifyCode() {
//        System.out.println(apiKey);
//
//        return "";
//    }
}

//const msgModule = require('coolsms-node-sdk').default
//
////인증을 위해 발급받은 본인의 API Key를 사용합니다.
//const apiKey = 'NCSHENZGEGWQEIPE'
//const apiSecret = 'KXLXNURMLXE2XEOAPB1P01ECBLIPAN5T'
//const messageService = new msgModule(apiKey, apiSecret);
//
//const params = {
//text: '[쿨에스엠에스 테스트] hello world!', // 문자 내용
//to: '01052475904', // 수신번호 (받는이)
//from: '01052475904' // 발신번호 (보내는이)
//}
//messageService.sendMany([params]).then(console.log).catch(console.error)
//       

















//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.util.UUID;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.commons.codec.binary.Hex;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;
//public class PhoneSmsConfig {
//class APIInit {
//    private static Retrofit retrofit;
//    private static MsgV4 messageService;
//    private static ImgApi imageService;
//
//    static String getHeaders() {
//        try {
//            Ini ini = new Ini(new File(APIInit.class.getResource("").getPath() + "/config.ini"));
//            String apiKey = ini.get("AUTH", "ApiKey");
//            String apiSecret = ini.get("AUTH", "ApiSecret");
//            String salt = UUID.randomUUID().toString().replaceAll("-", "");
//            String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString().split("\\[")[0];
//
//            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//            SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
//            sha256_HMAC.init(secret_key);
//            String signature = new String(Hex.encodeHex(sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8))));
//            return "HMAC-SHA256 ApiKey=" + apiKey + ", Date=" + date + ", salt=" + salt + ", signature=" + signature;
//        } catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    static MsgV4 getAPI() {
//        if (messageService == null) {
//            setRetrofit();
//            messageService = retrofit.create(MsgV4.class);
//        }
//        return messageService;
//    }
//
//    static void setRetrofit() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
////        Request 시 로그가 필요하면 추가하세요.
////        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
////        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.coolsms.co.kr/")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//    }
//}




//package com.kh.mng.common.phonesms;
//
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.util.UUID;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.commons.codec.binary.Hex;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//
//@Slf4j
//public class AuthorizationHeader {
////	
////	private static Retrofit retrofit;
////  private static MsgV4 messageService;
//
//	@Value("${sms.apiKey}") 
//	private static String apiKey;
//	
//	@Value("${sms.apiSecretKey}") 
//	private static String apiSecretKey;
//	
////	@Value("${sms.url}") 
////	private static String apiUrl;
////	
////	@Value("${sms.sentNum}") 
////	private static String sentNum;
//  
//	// API Key를 인증하기 위한 정보를 헤더에 담기 위해 형식에 맞게 데이터를 구해 표현하는 메소드
//  public static String getHeaders() {
//  	log.info("들어옴");
//		try {
//			String salt = UUID.randomUUID().toString().replaceAll("-", "");
//	    	String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString().split("\\[")[0];
//	    	log.info(apiKey);
//	    	log.info(apiSecretKey);
////	    	log.info(apiUrl);
////	    	log.info(sentNum);
//	    	Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//	    	log.info(new SecretKeySpec(apiSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256").toString());
//			SecretKeySpec secret_key = new SecretKeySpec(apiSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
//			
//	        sha256_HMAC.init(secret_key);
//	        String signature = new String(Hex.encodeHex(sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8))));
//	        return "HMAC-SHA256 ApiKey=" + apiKey + ", Date=" + date + ", salt=" + salt + ", signature=" + signature;
//	    
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		}
//		return null;
//  }
  
//  public static String getAPI() {
//  	
//  }
	
	// MsgV4 : RESTful API 통신을 위한 인터페이스, retrofit을 이용해 RESTful API를 호출하고 있음
  // getAPI() 메서드는 Retrofit을 사용하여 MsgV4 인터페이스를 구현한 서비스를 생성하고, 이를 반환함으로써 서버에 RESTful API 요청을 보내는 역할을 합니다. 
  // 이 서비스를 사용하여 클라이언트는 서버로부터 데이터를 가져오거나 업데이트할 수 있습니다.
//	static MsgV4 getAPI() {
//      if (messageService == null) {
//          setRetrofit();
//          messageService = retrofit.create(MsgV4.class);
//          // retrofit.create(MsgV4.class)를 호출하여 MsgV4 인터페이스를 구현한 서비스를 생성합니다. 이 서비스는 Retrofit을 통해 서버에 대한 요청을 수행할 것입니다.
//      }
//      return messageService; // messageService에 이 서비스를 할당하고, 이를 반환합니다.
//  }
//
//	// Request를 보내기 위한 url과 OkHttpClient를 세팅
//  static void setRetrofit() {
//      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
////      Request 시 로그가 필요하면 추가하세요.
////      interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
////      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//      
//      OkHttpClient client = new OkHttpClient.Builder()
//              .addInterceptor(interceptor)
//              .build();
//      retrofit = new Retrofit.Builder()
//              .baseUrl("https://api.coolsms.co.kr/")
//              .client(client)
//              .build();
//  }
//}








//package com.kh.mng.common.phonesms;
//
//import net.nurigo.sdk.message.model.Message;
//import okhttp3.Call;
//import retrofit2.http.Body;
//import retrofit2.http.Header;
//import retrofit2.http.POST;
//
//public interface MsgV4 {
//
//    // 심플 메시지
//    @POST("messages/v4/send")
//    Call sendMessage(@Header("Authorization") String auth,
//                                   @Body Message message);
//}
