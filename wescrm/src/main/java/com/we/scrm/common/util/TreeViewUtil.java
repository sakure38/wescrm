package com.we.scrm.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author QiCong
 * @version 树状结构数据处理
 *          {"id":1,"name":"treeNode","parentid":0,"order":100000000}
 **/

public class TreeViewUtil {
	
	/**
	 * 默认parentid = 0
	 */
	public static JSONArray getTreeJson(Map<Integer, TreeNode> nodes) {
		return getTreeJson(0, nodes);
	}
	/**
	 * Map转化为树形JSON
	 * map:{key:TreeNode.id,value:TreeNode}
	 */
	public static JSONArray getTreeJson(Integer nodeId, Map<Integer, TreeNode> nodes) {
		List<TreeNode> childList = getChildNodes(nodeId, nodes);
		JSONArray childTree = new JSONArray();
		for (TreeNode node : childList) {
			JSONObject o = new JSONObject();
			o.put("id", node.getId());
			o.put("name", node.getName());
			o.put("order", node.getOrder());
			// 递归调用该方法
			JSONArray childs = getTreeJson(node.getId(), nodes);
			if (!childs.isEmpty()) {
				o.put("children", childs);
			}
			childTree.fluentAdd(o);
		}
		return childTree;
	}

	/**
	 * 获取当前节点的所有子节点
	 * 
	 * @param nodeId
	 * @param nodes
	 * @return
	 */
	public static List<TreeNode> getChildNodes(Integer nodeId, Map<Integer, TreeNode> nodes) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		for (Integer key : nodes.keySet()) {
			if (nodes.get(key).getParentId().equals(nodeId)) {
				list.add(nodes.get(key));
			}
		}
		return list;
	}

}

