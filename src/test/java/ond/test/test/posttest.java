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

	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		//http://ondisk.co.kr/main/module/bbs_list_sphinx_prc.php?mode=ondisk&list_row=20
		//&page=1&search_type=ADT&search_type2=title&sub_sec=ADT_001
		//&search=&hide_adult=N&blind_rights=N&sort_type=&sm_search=&plans_idx=
		//
		StringBuilder sb = new StringBuilder();
		sb.append("http://ondisk.co.kr/main/module/bbs_list_sphinx_prc.php?mode=ondisk&list_row=20");
		sb.append("&page=1&search_type=ADT&search_type2=title&sub_sec=ADT_001");
		sb.append("&search=&hide_adult=N&blind_rights=N&sort_type=&sm_search=&plans_idx=");

		//sb = new StringBuilder();
		//sb.append("http://ondisk.co.kr");

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet HttpGet = new HttpGet(sb.toString());
		System.out.println(HttpGet.getURI());
		
		//HttpGet.setHeader("Accept" , "application/json, text/javascript, */*; q=0.01");
		//HttpGet.setHeader("Connection" , "keep-alive");
		//HttpGet.setHeader("Host" , "ondisk.co.kr");
		HttpGet.setHeader("X-Requested-With" , "XMLHttpRequest");
		//HttpGet.setHeader("If-Modified-Since" , "Mon 23 Dec 2019 01:41:15 GMT");
		//HttpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36");
		//HttpGet.setHeader("Referer", "http://ondisk.co.kr/index.php?mode=ondisk&search_type=ADT&sub_sec=ADT_001");
		//HttpGet.setHeader("Cookie", "list_type=mnShare_text_list; _e_charge9=done; Intro_domain_chk=ondisk.co.kr; _ga=GA1.3.1337713627.1578796614; _gid=GA1.3.1544312903.1578796614; topBnnrSlide_20150715=true; charge=no; mid=cgd2314897aegd2314897aegd2314897aegd2314897a95d2314897a35d2314897a25d2314897a75d2314897a96d2314897a; UID=ansrl0002; nick=sodigow3rt23; join_path=main; mp_info2=y; cmn_cash=0; bns_cash=0; coupon=0; Lidx=%241%24391M0HED%247FjbcOyVGLUyeU35tkfIm1; SCkey=Y2dkMjMxNDg5N2FlZ2QyMzE0ODk3YWVnZDIzMTQ4OTdhZWdkMjMxNDg5N2E5NWQyMzE0ODk3YTM1ZDIzMTQ4OTdhMjVkMjMxNDg5N2E3NWQyMzE0ODk3YTk2ZDIzMTQ4OTdhfHNvZGlnb3czcnQyM3ww; keep_query_string=%2Findex.php%3Fmode%3Dondisk%26search_type%3DADT%26sub_sec%3DADT_001; keep_search_type=ADT; keep_sub_sec=ADT_001; adult=1; PCID=15787966692772426744588; sendId=Y; _gat_UA-57089340-3=1");
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
