package ond.test.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;


public class posttest {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("http://ondisk.co.kr/main/module/bbs_list_sphinx_prc.php?mode=ondisk&list_row=20");

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet HttpGet = new HttpGet(sb.toString());
		System.out.println(HttpGet.getURI());
		
		HttpGet.setHeader("X-Requested-With" , "XMLHttpRequest");
		HttpGet.setHeader("Cookie", "list_type=mnShare_text_list; _e_charge9=done; Intro_domain_chk=ondisk.co.kr; _ga=GA1.3.1559096028.1578798540; _gid=GA1.3.1764513473.1578798540; charge=no; mid=dgd2314897aegd2314897aegd2314897aegd2314897a95d2314897a35d2314897a25d2314897a75d2314897a96d2314897a; UID=ansrl0001; nick=asfawrty3456; join_path=main; mp_info2=y; ration=1578399605%7C1579004405%7C00%7C24%7C50; adult=1; cmn_cash=0; bns_cash=0; coupon=0; Lidx=%241%24zF2Vyl9a%243vhGZP0oj8BT1cPXfOCW01; SCkey=ZGdkMjMxNDg5N2FlZ2QyMzE0ODk3YWVnZDIzMTQ4OTdhZWdkMjMxNDg5N2E5NWQyMzE0ODk3YTM1ZDIzMTQ4OTdhMjVkMjMxNDg5N2E3NWQyMzE0ODk3YTk2ZDIzMTQ4OTdhfGFzZmF3cnR5MzQ1Nnwx; topBnnrSlide_20150715=true; keep_search_type=ADT; _gat_UA-57089340-3=1; keep_query_string=%2Findex.php%3Fmode%3Dondisk%26search_type%3DADT%26sub_sec%3Dundefined");
		
		
		try {

			CloseableHttpResponse response = client.execute(HttpGet);
									

			String responseBody = IOUtils.toString(response.getEntity().getContent(),(Charset)response.getEntity().getContentEncoding());
			
			System.out.println(responseBody);
			
			client.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
