package com.homejjr.soufang;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.homejjr.clawer.util.HttpUtil;

public class App 
{
	
	public static void generatePageLinks() throws Exception {
		List<String> pageLinks = new ArrayList<String>();
		for(int i = 1; i <= 100; i++) {
			String url = "http://esf.sh.fang.com/house-a026/i3" + i + "/";
			pageLinks.add(url);
		}
		FileUtils.writeLines(new File("./output/soufang/pagelink.txt"), pageLinks, true);
	}
	
	public static List<String> getPageLinks() throws Exception {
		return FileUtils.readLines(new File("./output/soufang/pagelink.txt"), "utf-8");
	}
	
	public static void saveAgentLinks(String pageLink) throws Exception {
		
		
		String html = HttpUtil.HttpGet(pageLink);
		//System.out.println(html);
		Document doc = Jsoup.parse(html);
		//Document doc = Jsoup.parse(new File("./input/html.txt"), "utf-8");
		List<String> agentLinks = new ArrayList<String>();
		
		Elements elements = doc.select("A[target=_blank]");
		
		for(Element element : elements) {
			String href = element.attr("href");
			if(href.startsWith("/a/")) {
				String url = "http://esf.sh.fang.com"+href;
				System.out.println(url);
				agentLinks.add(url);
			}
		}
		
		FileUtils.writeLines(new File("./output/soufang/agentlink.txt"), agentLinks, true);
	}
	
	public static List<String> getAgentLinks() throws Exception {
		return FileUtils.readLines(new File("./output/soufang/agentlink.txt"), "utf-8");
	}
	
	public static void outputAgent() throws Exception {
		
		
		
		
		try {
			
			StringBuilder buf = new StringBuilder();
			
			for(int i = 1; i <= 33; i++) {
				
				Thread.sleep(6000);		
			
				//鞍山
				//String url = "http://esf.sh.fang.com/agenthome-a026-b05255/-i3" + i + "-j310/";
				
				//东外滩
				//String url = "http://esf.sh.fang.com/agenthome-a026-b01648/-i3" + i + "-j310/";
				
				//黄兴公园
				//String url = "http://esf.sh.fang.com/agenthome-a026-b01650/-i3" + i + "-j310/";
				
				//控江路
				//String url = "http://esf.sh.fang.com/agenthome-a026-b010350/-i3" + i + "-j310/";				
				
				//五角场
				//String url = "http://esf.sh.fang.com/agenthome-a026-b01647/-i3" + i + "-j310/";
				
				//新江湾
				//String url = "http://esf.sh.fang.com/agenthome-a026-b01651/-i3" + i + "-j310/";
				
				//杨浦大桥
				//String url = "http://esf.sh.fang.com/agenthome-a026-b012971/-i3" + i + "-j310/";
				
				//中原
				//String url = "http://esf.sh.fang.com/agenthome-a026-b01652/-i3" + i + "-j310/";
				
				//周家嘴路
				String url = "http://esf.sh.fang.com/agenthome-a026-b01649/-i3" + i + "-j310/";
			
			    System.out.println("page NO="+i);
			    
				String html = HttpUtil.HttpGet(url);
				//System.out.println(html);
				Document doc = Jsoup.parse(html);
				//Document doc = Jsoup.parse(new File("./input/html.txt"), "utf-8");
				
				
			
			//agentName = doc.select("DIV[class=rzname floatl]").get(0).text();
			List<Element> houseList = doc.select("DIV[class=house]");
			
			System.out.println("houseList.size()="+houseList.size());
			 
			for(Element el:houseList){			
				
				
				String agentName = "";				
				agentName=el.select("p[class=housetitle]").get(0).text();
				agentName = StringUtils.substringBefore(agentName, "(");
				System.out.print(agentName+",");
				buf.append(agentName).append(",");
				
				
				String mobile = "";		
				try{
				mobile = el.select("p[class=black]").get(1).select("strong").get(0).text();
				}catch(Exception e){
					mobile = el.select("p[class=black]").get(0).select("strong").get(0).text();
					System.out.print(mobile+",");
					buf.append(mobile).append(",");
					System.out.println("");
					buf.append("").append("\n");
					continue;
				}
				System.out.print(mobile+",");
				buf.append(mobile).append(",");
				
				String company = "";
				
				company = el.select("p[class=black]").get(0).select("span").get(0).text();				
				System.out.println(company);
				buf.append(company).append("\n");
			}
			}
			
			FileUtils.write(new File("./output/soufang/soufang_agent_zhoujiazuilu_10_10.csv"), new String(buf.toString().getBytes("utf-8")), true);
		} catch(Exception e) {
			throw e;
		}
		
	
	}
	
	public static void main(String[] args) throws Exception {
		
		//List<String> urls = IOUtils.readLines(input, "utf-8");
		
		/*for() {
			
			Thread.sleep(3000);
		}*/
		
		/*List<String> agentLinks = new ArrayList<String>();
		List<String> lines = FileUtils.readLines(new File("./output/soufang/soufang_agent.csv"), "utf-8");
		Set<String> phone = new HashSet<String>();
		int lineNo = 1;
		for(String line : lines) {
			String[] array = line.split(",");
			String no = array[1];
			System.out.println("line"+lineNo + ":" + no);
			lineNo++;
			
			if(phone.add(no)) {
				agentLinks.add(line);
			}
			
		}
		FileUtils.writeLines(new File("./output/soufang/sofang_agent_new.csv"), agentLinks, true);*/
		
		outputAgent();
	}
}
