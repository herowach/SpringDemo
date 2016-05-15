package com.demo.model.mongo;

public class TreeNode {
	private Long labelId;
	private Long dsId;
	private String text;
	private TreeNode[] children;
	private Long parentId;
	private boolean leaf;
	private String isActive;
	private String type;
	private String labelName;
	public TreeNode(String text, boolean leaf,
			TreeNode[] children) {
		this.text= text;
		this.leaf = leaf;
		this.setChildren(children);
	}
	public TreeNode() {
	}
	public Long getLabelId() {
		return labelId;
	}
	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	public Long getDsId() {
		return dsId;
	}
	public void setDsId(Long dsId) {
		this.dsId = dsId;
	}
 
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
 

	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public TreeNode[] getChildren() {
		return children;
	}
	public void setChildren(TreeNode[] children) {
		this.children = children;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
}
