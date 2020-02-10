package ond.test.test;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class proxyCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(isOnline("121.139.218.165",31409));
	}
	
	
	
	private static boolean isOnline(String host, int port)
	{
	    String url = "http://wjqthrtjqj2.com/ip.txt";
	    try
	    {
	        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
	        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection(proxy);
	        connection.connect();
	        connection.setConnectTimeout(5);
	        System.out.println(IOUtils.toString(connection.getInputStream()));
	        return true;

	    } catch (Exception e)
	    {
	    	System.out.println(e);
	        return false;
	    }
	}

}