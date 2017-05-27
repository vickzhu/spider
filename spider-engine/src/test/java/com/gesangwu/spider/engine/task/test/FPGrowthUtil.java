package com.gesangwu.spider.engine.task.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.util.StringUtil;

/**
 * 基于FP-Tree的关联规则FP-Growth推荐算法
 * @author zhuxb
 *
 */
public class FPGrowthUtil {
	
	public static final int  support = 2; // 设定最小支持频次为2 
	//保存第一次的次序
	public Map<String,Integer> ordermap=new HashMap<String,Integer>();
	
	public LinkedList<LinkedList<String>> readF1()  {      
		LinkedList<LinkedList<String>> records=new LinkedList<LinkedList<String>>();
		LinkedList<String> l1 = new LinkedList<String>();
		l1.add("1");
		l1.add("2");
		records.add(l1);
		l1 = new LinkedList<String>();
		l1.add("2");
		l1.add("3");
		records.add(l1);
		l1 = new LinkedList<String>();
		l1.add("1");
		l1.add("2");
		l1.add("3");
		records.add(l1);
		l1 = new LinkedList<String>();
		l1.add("1");
		l1.add("2");
		l1.add("5");
		records.add(l1);
		return records;
    }
	
	//创建表头链
	public LinkedList<FPTreeNode> buildHeaderLink(LinkedList<LinkedList<String>> records){
		if(records.size() <= 0){
			return null;
		}
		
		Map<String, FPTreeNode> map = new HashMap<String, FPTreeNode>();
		for(LinkedList<String> items : records){//遍历记录
			for(String item : items){
				//如果存在数量增1，不存在则新增
				if(map.containsKey(item)){
					map.get(item).Sum(1);
				}else{
					FPTreeNode node = new FPTreeNode();
					node.setName(item);
					node.setCount(1);
					map.put(item, node);
				}
		     }
		}
		
		LinkedList<FPTreeNode> header = new LinkedList<FPTreeNode>();
		// 把支持度大于（或等于）最小支持度的项加入到F1中
		for(Map.Entry<String, FPTreeNode> entry : map.entrySet()){
			FPTreeNode tNode = entry.getValue();
			if(tNode.getCount() >= support){
				header.add(tNode);
			}
		}
		
		sort(header);
		return header;
	}
	
	//选择法排序,如果次数相等，则按名字排序,字典顺序,先小写后大写
	public List<FPTreeNode> sort(List<FPTreeNode> list){
		int len=list.size();
		for(int i=0;i<len;i++){
			
			for(int j=i+1;j<len;j++){
				FPTreeNode node1=list.get(i);
				FPTreeNode node2=list.get(j);
				if(node1.getCount()<node2.getCount()){
					FPTreeNode tmp=new FPTreeNode();
					tmp=node2;
					list.remove(j);
					//list指定位置插入，原来的>=j元素都会往下移，不会删除,所以插入前要删除掉原来的元素
					list.add(j,node1);
					list.remove(i);
					list.add(i,tmp);
				}
				//如果次数相等，则按名字排序,字典顺序,先小写后大写
				if(node1.getCount()==node2.getCount()){
					String name1=node1.getName();
					String name2=node2.getName();
					int flag=name1.compareTo(name2);
					if(flag>0){
						FPTreeNode tmp=new FPTreeNode();
						tmp=node2;
						list.remove(j);
						//list指定位置插入，原来的>=j元素都会往下移，不会删除,所以插入前要删除掉原来的元素
						list.add(j,node1);
						list.remove(i);
						list.add(i,tmp);
					}
				}
			}
		}
		
		return list;
	}
	//选择法排序，降序,如果同名按L 中的次序排序
	public   List<String> itemsort(LinkedList<String> lis,List<FPTreeNode> header){
		//List<String> list=new ArrayList<String>();
		//选择法排序
		int len=lis.size();
		for(int i=0;i<len;i++){
			for(int j=i+1;j<len;j++){
				String key1=lis.get(i);
				String key2=lis.get(j);
				Integer value1=findcountByname(key1,header);
				if(value1==-1)continue;
				Integer value2=findcountByname(key2,header);
				if(value2==-1)continue;
				if(value1<value2){
					String tmp=key2;
					lis.remove(j);
					lis.add(j,key1);
					lis.remove(i);
					lis.add(i,tmp);
				}
				if(value1==value2){
					int v1=ordermap.get(key1);
					int v2=ordermap.get(key2);
					if(v1>v2){
						String tmp=key2;
						lis.remove(j);
						lis.add(j,key1);
						lis.remove(i);
						lis.add(i,tmp);
					}
				}
		     }
		}
		return lis;
	}
	
	public Integer findcountByname(String itemname,List<FPTreeNode> header){
		Integer count=-1;
		for(FPTreeNode node:header){
			if(node.getName().equals(itemname)){
				count= node.getCount();
			}
		}
		return count;
	}
	
	/**
	 * 
	 * @param records 构建树的记录,如I1,I2,I3
	 * @param header 韩书中介绍的表头
	 * @return 返回构建好的树
	 */
	public FPTreeNode builderFpTree(LinkedList<LinkedList<String>> records,List<FPTreeNode> header){
	   if(records.size() <= 0){
		   return null;
	   }
	   FPTreeNode root = new FPTreeNode();
	   for(LinkedList<String> items : records){
		   itemsort(items, header);
		   addNode(root, items, header);
	   }
	   return root;
	}
	
	//当已经有分枝存在的时候，判断新来的节点是否属于该分枝的某个节点，或全部重合，递归
	public  FPTreeNode addNode(FPTreeNode root, LinkedList<String> items,List<FPTreeNode> header){
		if(items.size() <= 0){
			return null;
		}
		String item = items.poll();
		//当前节点的孩子节点不包含该节点，那么另外创建一支分支。
		FPTreeNode node = root.findChild(item);
		if(node == null){
			node = new FPTreeNode();
			node.setName(item);
			node.setCount(1);
			node.setParent(root);
			root.addChild(node);
			
			//将各个节点加到链头中 
			for(FPTreeNode head : header){
				if(head.getName().equals(item)){
					while(head.getNextHomonym() != null){//存在下一个同名节点
						head = head.getNextHomonym();
					}
					head.setNextHomonym(node);
					break;
				}
			}
		}else{
			node.setCount(node.getCount()+1);
		}
		addNode(node, items, header);
		return root;
	}
	
	//从叶子找到根节点，递归之
	public void toroot(FPTreeNode node,LinkedList<String> newrecord){
		if(node.getParent()==null)return;
		String name=node.getName();
		newrecord.add(name);
		toroot(node.getParent(),newrecord);
	}
	
	//对条件FP-tree树进行组合，以求出频繁项集
	public void combineItem(FPTreeNode node,LinkedList<String> newrecord,String Item){
		if(node.getParent()==null)return;
		String name=node.getName();
		newrecord.add(name);
		toroot(node.getParent(),newrecord);
	}
	
	//fp-growth
	public Map<String, Integer> fpgrowth(LinkedList<LinkedList<String>> records, String item){
		//构建链头
		LinkedList<FPTreeNode> header = buildHeaderLink(records);
		//创建FP-Tree
		FPTreeNode fptree = builderFpTree(records, header);
		//结束递归的条件
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		if(header.size() <= 0|| fptree == null){
			return resultMap;
		}
		//打印结果,输出频繁项集
		if(item != null){
			//寻找条件模式基,从链尾开始
			for(int i = header.size() - 1; i >= 0; i--){
				FPTreeNode head = header.get(i);
				Integer count = 0;
				while(head.getNextHomonym() != null){
					head = head.getNextHomonym();
					//叶子count等于多少，就算多少条记录
					count = count + head.getCount();
					
				}
				//打印频繁项集
				System.out.println(head.getName()+","+item+"\t"+count);
				resultMap.put(head.getName(), count);
			}
		}
		
		//保存新的条件模式基的各个记录，以重新构造FP-tree
		LinkedList<LinkedList<String>> newrecords = new LinkedList<LinkedList<String>>();
		
		//寻找条件模式基,从链尾开始
		for(int i = header.size() - 1; i >= 0; i--){
			FPTreeNode head = header.get(i);
			String itemname = buildItemName(item, head);
			while(head.getNextHomonym() != null){
				head = head.getNextHomonym();
				//叶子count等于多少，就算多少条记录
				Integer count = head.getCount();
				for(int j = 0; j < count; j++){
				   LinkedList<String> record=new LinkedList<String>();
				   toroot(head.getParent(),record);
				   newrecords.add(record);
				}
			}
			//递归求子FP-Tree
			resultMap.putAll(fpgrowth(newrecords,itemname));
		}
		return resultMap;
    }
	
	/**
	 * 构建频繁项名称(如：啤酒，尿不湿)
	 * @param item
	 * @param head
	 * @return
	 */
	private String buildItemName(String item, FPTreeNode head){
		StringBuilder sb = new StringBuilder();
		sb.append(head.getName());
		//再组合
		if(StringUtil.isNotBlank(item)){
			sb.append(SymbolConstant.COMMA);
			sb.append(item);
		}
		return sb.toString();
	}
	
	//保存次序，此步也可以省略，为了减少再加工结果的麻烦而加
	public void orderF1(LinkedList<FPTreeNode> orderheader){
		for(int i=0;i < orderheader.size();i++){
			FPTreeNode node=orderheader.get(i);
			ordermap.put(node.getName(), i);
		}
		
	}
	public static void main(String[] args) throws IOException {
		//读取数据
		FPGrowthUtil fpg=new FPGrowthUtil();
		LinkedList<LinkedList<String>> records=fpg.readF1();
		LinkedList<FPTreeNode> orderheader=fpg.buildHeaderLink(records);
		for (FPTreeNode fpTreeNode : orderheader) {
			System.out.println(fpTreeNode.getName());
			
		}
		fpg.orderF1(orderheader);
		fpg.fpgrowth(records,null);
	}
}
