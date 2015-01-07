package com.synload.nationalanime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.synload.framework.modules.ModuleClass;
import com.synload.framework.modules.annotations.Module;
import com.synload.nationalanime.pages.MyAnimeListFetch;

@Module(name="National Anime Module",author="Nathaniel Davidson",version="0.0.1")
public class NationalAnime extends ModuleClass {
	public static RequestConfig requestConfig = RequestConfig.custom()
	        .setSocketTimeout(10000)
	        .setConnectTimeout(10000)
	        .build();
	@Override
	public void initialize() {
		/*try {
			System.out.println(SynloadFramework.ow.writeValueAsString(Tag.getByTagName("test")));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}*/
		/*try {
			Anime a = new Anime("title","Test","episodes","25");
			a._insert();
			Tag tag = new Tag("tag","thisistag","type","copyright");
			tag._insert();
			a._set(tag);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		*/
		(new Thread(new Runnable(){
			@Override
			public void run() {
				for(long x=1; x<100000; x++){
					if(MyAnimeListFetch.getMyAnimeShow(x)){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		})).start();
		(new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(long x=1; x<100000; x++){
					if(MyAnimeListFetch.getChars(x)){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		})).start();
	}
	
	public static String fetchUrl(String url){
		String message = "";
		CookieStore cookieStore = new BasicCookieStore();
		if((new File("cookies.txt")).exists()){
			try {
				FileInputStream fstream = new FileInputStream("cookies.txt");
				ObjectInputStream in = new ObjectInputStream(fstream);
				cookieStore = (CookieStore) in.readObject();
				in.close();
				fstream.close();
			} catch (IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		httpget.addHeader("Pragma", "no-cache");
		httpget.addHeader("Accept-Language", "en-US,en;q=0.8");
		httpget.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpget.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		httpget.addHeader("Connection", "keep-alive");
    	try {
    		httpget.setConfig(requestConfig);
    		HttpClientContext context = HttpClientContext.create();
    		context.setCookieStore(cookieStore);
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).setProxy(new HttpHost("86.122.124.11",80,"http")).build();
    		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(globalConfig).setDefaultCookieStore(cookieStore).build();
    		CloseableHttpResponse response = httpclient.execute(httpget,context);
    		InputStream is = response.getEntity().getContent();
    		byte[] data = new byte[8*1024];
    		int read = 0;
    		
    		while((read = is.read(data))!=-1){
    			message+= new String(Arrays.copyOf(data,read),"UTF-8");
    		}
    		
    		response.close();
    		httpclient.close();
    		try {
        		FileOutputStream fstream = new FileOutputStream("cookies.txt");
    			ObjectOutputStream out = new ObjectOutputStream(fstream);
    			out.writeObject(cookieStore);
    			out.close();
    			fstream.close();
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    		return message;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return "";
	}

}
