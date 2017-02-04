package com.gesangwu.spider.engine.task.test;

import java.util.ArrayList;
import java.util.List;

public class FPTreeNode implements Comparable<FPTreeNode>{
	
	private String name; // 节点名称
	private Integer count; // 计数
	private FPTreeNode parent; // 父节点
	private List<FPTreeNode> children; // 子节点
	private FPTreeNode nextHomonym; // 下一个同名节点
  
	public FPTreeNode() {
  
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	public void Sum(Integer count) {
		this.count =this.count+count;
	}
	public FPTreeNode getParent() {
		return parent;
	}

	public void setParent(FPTreeNode parent) {
		this.parent = parent;
	}

	public List<FPTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<FPTreeNode> children) {
		this.children = children;
	}

	public FPTreeNode getNextHomonym() {
		return nextHomonym;
	}

	public void setNextHomonym(FPTreeNode nextHomonym) {
		this.nextHomonym = nextHomonym;
	}
	/**
	 * 添加一个节点
	 * @param child
	 */
	public void addChild(FPTreeNode child) {
		if (this.getChildren() == null) {
			List<FPTreeNode> list = new ArrayList<FPTreeNode>();
			list.add(child);
			this.setChildren(list);
		} else {
			this.getChildren().add(child);
		}
	}
	/**
	*  是否存在着该节点,存在返回该节点，不存在返回空
	* @param name
	* @return
	*/
	public FPTreeNode findChild(String name) {
		List<FPTreeNode> children = this.getChildren();
		if (children != null) {
			for (FPTreeNode child : children) {
				if (child.getName().equals(name)) {
					return child;
				}
			}
		}
		return null;
	}


	@Override
	public int compareTo(FPTreeNode arg0) {
		// TODO Auto-generated method stub
		int count0 = arg0.getCount();
		// 跟默认的比较大小相反，导致调用Arrays.sort()时是按降序排列
		return count0 - this.count;
	}
}
