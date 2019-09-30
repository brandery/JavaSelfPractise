package com.liutao;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.liutao.global.GlobalException;
import com.liutao.model.CounponJwtToken;
import com.liutao.model.JwtToken;
import com.liutao.model.RawSms;
import com.liutao.security.DesSecurity;
import com.liutao.util.MyHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Iterator;
import java.util.Map;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}, scanBasePackages = {"com.liutao"})
@SpringBootApplication// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application
{
	public static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args)
	{
		logger.debug("init main......");
		Thread.setDefaultUncaughtExceptionHandler(new GlobalException());

//		String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
//		System.out.println(timeStr);
		//JdbcTest.testJdbcTemplate();//Test();
		//String tokenStr = GetToken();b
		// System.out.println(tokenStr);
		//TestReadJsonArray();
        //TestDesCbc("[{\"Cardid\":\"6021\",\"Cardno\":\"0000000100229\",\"Cardsecurity\":\"763944352263\",\"Createtime\":\"2019-07-15T15:02:29+08:00\",\"State\":0,\"Phone\":null,\"BandedTime\":null,\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":0,\"Isfrezon\":0,\"Validstarttime\":\"2019-06-08T18:39:30+08:00\",\"Validendtime\":\"2019-06-09T15:00:00+08:00\"}]","desmbaas");
		//TestCouponToken();

		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);//config for enable to capture 404
	}
	public static int performArithmeticOperation(int num1, int num2)
	{
		return num1/num2;
	}

	public static String TestDesCbc(String data, String key)
    {
        String temp = "";
        try {
            temp =  DesSecurity.encrypt(data,key);
            System.out.println("加密结果："+temp);
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            temp =  DesSecurity.decrypt(temp,key);
            System.out.println("解密结果："+temp);
            return "finished";
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "invalid data";
    }

	public static void TestReadJsonArray(){
		System.out.println("TestReadJsonArray begin......");
		String jsonarrayStr = "[{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-08T18:16:06+08:00\",\"State\":0,\"Phone\":\"13661644902\",\"BandedTime\":\"2019-07-08T18:16:15+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-08T18:49:57+08:00\",\"State\":0,\"Phone\":\"13661644904\",\"BandedTime\":\"2019-07-08T18:50:02+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-09T09:42:25+08:00\",\"State\":0,\"Phone\":\"13661644908\",\"BandedTime\":\"2019-07-09T09:42:37+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-10T10:15:41+08:00\",\"State\":0,\"Phone\":\"13661644901\",\"BandedTime\":\"2019-07-10T10:15:40+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-10T14:34:26+08:00\",\"State\":0,\"Phone\":\"13661644903\",\"BandedTime\":\"2019-07-10T14:34:27+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-10T14:44:23+08:00\",\"State\":0,\"Phone\":\"13400001000\",\"BandedTime\":\"2019-07-10T14:44:23+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-10T16:48:20+08:00\",\"State\":0,\"Phone\":\"15262733800\",\"BandedTime\":\"2019-07-10T16:48:20+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-10T18:34:14+08:00\",\"State\":0,\"Phone\":\"18321921314\",\"BandedTime\":\"2019-07-10T18:34:15+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-10T18:49:01+08:00\",\"State\":0,\"Phone\":\"18682148557\",\"BandedTime\":\"2019-07-10T18:49:01+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-11T10:16:34+08:00\",\"State\":0,\"Phone\":\"13661644905\",\"BandedTime\":\"2019-07-11T10:16:36+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"},{\"Cardid\":\"3818\",\"Cardno\":\"0101000100018\",\"Cardsecurity\":\"550337051249\",\"Createtime\":\"2019-07-11T10:57:18+08:00\",\"State\":0,\"Phone\":\"13661644906\",\"BandedTime\":\"2019-07-11T10:57:20+08:00\",\"UsedOrder\":null,\"UsedRegion\":null,\"UsedTime\":null,\"Frezontime\":null,\"Isused\":0,\"Istake\":1,\"Isfrezon\":0,\"Validstarttime\":\"2019-07-08T16:42:58+08:00\",\"Validendtime\":\"2019-07-31T00:00:00+08:00\"}]";
		try{
			ObjectMapper objMapper = new ObjectMapper();
			JsonNode jsonNode = objMapper.readTree(jsonarrayStr);
			if(!jsonNode.isArray()){
				throw new Exception("is not jsonarray!");
			}
			ArrayNode jsonArray = (ArrayNode)(jsonNode);
			int i=0;
			for (JsonNode inode: jsonArray) {
				System.out.println("Json object :: \n" + i++);
				ObjectNode obj = (ObjectNode)inode;
				Iterator<Map.Entry<String,JsonNode>> jsonNodes = obj.fields();
				while (jsonNodes.hasNext()){
					Map.Entry<String, JsonNode> node = jsonNodes.next();
					System.out.println(node.getKey()+":"+node.getValue());
				}
			}
			System.out.println("TestReadJsonArray finished......");
		}
		catch (Exception e){
			System.out.println(e);
		}
    }

	public  static  void TestCouponToken()
	{
        String HOST = "http://10.40.0.190:8101";//"http://10.40.0.190:8092";
		RestTemplate restClient = MyHttpClient.getInstance();

		String url = HOST + "/api/account/login";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		StringBuilder paras = new StringBuilder();
		paras.append("{\"client_id\": \"admin\",");
		paras.append("\"client_secret\": \"admin\",");
		paras.append("\"scope\": \"CouponManager.Api\",");
		paras.append("\"grant_type\":\"client_credentials\"}");

		CounponJwtToken parasObj = new CounponJwtToken();
		parasObj.client_id = "admin";
		parasObj.client_secret = "admin";
		parasObj.scope = "CouponManager.Api";
		parasObj.grant_type = "client_credentials";
		try {
			//封装请求内容
			String utf8String = new String(paras.toString().getBytes("UTF-8"));
			HttpEntity<String> req = new HttpEntity<>(utf8String, headers);
			//HttpEntity<CounponJwtToken> req = new HttpEntity<>(parasObj, headers);

            req.getHeaders().getAcceptCharset();
            ResponseEntity<String> responseEntity = restClient.postForEntity(url, req, String.class);
            //String body = responseEntity.getBody();
			//String utf8body = new String(body.getBytes("UTF-16"));
			System.out.println(responseEntity);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void TestSMS()
	{
		String token = GetToken();
		String HOST = "http://localhost:8092";//"http://10.40.0.190:8092";
		RestTemplate restClient = MyHttpClient.getInstance();
		String url = HOST + "/api/Notify/raw";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", token);
		RawSms params = new RawSms("19901671604", "1000", "【精锐教育】您已成功激活【2314】【3】元优惠券，【123】前可至附近校区使用");
		//封装请求内容
		HttpEntity<RawSms> req = new HttpEntity<>(params, headers);
		try {
			ResponseEntity<String> responseEntity = restClient.postForEntity(url, req, String.class);
			String body = responseEntity.getBody();
			String result = restClient.postForObject(url, req, String.class);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String GetToken() {
		String HOST = "http://10.40.0.190:40190";
		RestTemplate restClient = MyHttpClient.getInstance();
		String url = HOST + "/connect/token";
		//String req = "grant_type=client_credentials&client_id=admin&client_secret=admin&scope=";d

		//设置请求数据的格式
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		//封装参数
		MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
		params.add("grant_type", "client_credentials");
		params.add("client_id", "admin");
		params.add("client_secret", "admin");
		params.add("scope", "");

		//封装请求内容
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,headers);

		try {
			ResponseEntity<String> responseEntity = restClient.postForEntity(url, req, String.class);
            ObjectMapper objMapper = new ObjectMapper();
			JwtToken body = objMapper.readValue(responseEntity.getBody(), JwtToken.class);
			String result = body.token_type +" " + body.access_token;

			return result;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return "error";
	}
}
