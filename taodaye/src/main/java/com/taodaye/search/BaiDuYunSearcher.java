package com.taodaye.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.taodaye.utils.JSONObjectUtils;

public class BaiDuYunSearcher implements Searcher{

	private final String SEARCH_ENGINE_API = "http://api.ygyhg.com/pc/free";
	
	@Override
	public String getApi() {
		return SEARCH_ENGINE_API;
	}

	@Override
	public SearchResultObject search(String searchStr, DataProcessor processor) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(getApi());
		CloseableHttpResponse response = null;
		SearchResultObject res = null;
		try {
			response = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response object
			// to allow the response content to be streamed directly from the network socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally clause.
			// Please note that if response content is not fully consumed the underlying
			// connection cannot be safely re-used and will be shut down and discarded
			// by the connection manager. 
		    System.out.println(response.getStatusLine());
		    HttpEntity entity1 = response.getEntity();
		    InputStream in = entity1.getContent();
		    res = processor.process(in, SearchResultObject.class);
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity1);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return res;
	}

}
