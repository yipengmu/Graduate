package com.laomu.graduate.utils.spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SchoolSpider {

	static String url = "http://kechenggezi.com/schools";

	public static void main(String[] args) {

		URL BJ_PM_URL;
		try {
			BJ_PM_URL = new URL(url);
			Document doc = Jsoup.parse(BJ_PM_URL, 5000);
			Elements elements = doc.body().getElementById("content")
					.getElementsByClass("privence_schools").get(0).getAllElements();

			for(int i=0 ;i<elements.size();i++){

				Element item =elements.get(i);
//				item.childNodes().get(0).childNodeSize();
				item.getElementById("school");
			}

			System.out.println(elements.get(0).getAllElements() + " \n----\n");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
