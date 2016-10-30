package com.gesangwu.spider.engine.task.test;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class HtmlParserTest {

	public static void main(String[] args) {
		extractKeyWordText("http://quotes.money.163.com/hs/marketdata/mrlhbSub.php?clear=1202&symbol=000736&type=01&date=2016-10-28", "佛山");
	}
	
	public static void extractKeyWordText(String url, String keyword) {
		try {
            //生成一个解析器对象，用网页的 url 作为参数
			Parser parser = new Parser(url);
			//设置网页的编码,这里只是请求了一个 UTF-8 编码网页
			parser.setEncoding("UTF-8");
			//迭代所有节点, null 表示不使用 NodeFilter
			NodeList list = parser.parse(null);
            //从初始的节点列表迭代所有的节点
			processNodeList(list, keyword);
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	private static void processNodeList(NodeList list, String keyword) {
		//迭代开始
		SimpleNodeIterator iterator = list.elements();
		while (iterator.hasMoreNodes()) {
			Node node = iterator.nextNode();
			if(node instanceof TagNode){
				TagNode tn = (TagNode)node;
				if("A".equals(tn.getTagName())){
					String href = tn.getAttribute("href");
					System.out.println(href);
					String deptCode = href.substring(href.indexOf("_")+1, href.indexOf("."));
					if(href.indexOf("agencylist") != 0){
						String deptName = tn.getFirstChild().getText();
						Node buyNode = tn.getParent().getNextSibling();
						String buyAmt = buyNode.getFirstChild().getText().trim();
						Node sellNode = buyNode.getNextSibling().getNextSibling().getNextSibling().getNextSibling();
						String sellAmt = sellNode.getFirstChild().getText().trim();
						System.out.println(deptCode + deptName+":"+buyAmt + "," + sellAmt);
					}
				}
			}
			//得到该节点的子节点列表
			NodeList childList = node.getChildren();
			if(null != childList){
				processNodeList(childList, keyword);
			}
		}//end wile
	}

}
