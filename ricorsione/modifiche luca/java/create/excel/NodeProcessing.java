package create.excel;

import java.util.List;

import parser.json.MenuNode;

public class NodeProcessing {

	public void loadRow(List<MenuNode> nodeList, int treeNode, List<RowData> rowForExcel) throws Exception {

			for (MenuNode menuNode : nodeList) {

				int serviceId = 0;
				String nodeName = "";
				String nodeType = "";
				String groupType = "";
				String flowType = "";
				int resourceId = 0;

				if (menuNode.getNodeName() != null) {
					nodeName = menuNode.getNodeName();
				}

				if (menuNode.getNodeType() != null) {
					nodeType = menuNode.getNodeType();
				}

				if (menuNode.getGroupType() != null) {
					groupType = menuNode.getGroupType();
				}
				if (menuNode.getFlowType() != null) {
					flowType = menuNode.getFlowType();
				}
				if (menuNode.getResource() != null) {
					resourceId = menuNode.getResource().getId();
				}
				if (nodeType.contains("service")) {
					serviceId = menuNode.getNodeId();
				}

				rowForExcel.add(new RowData(treeNode, serviceId, nodeName, nodeType, groupType, flowType, resourceId));

//				System.out.println("treeNode: " + treeNode);
//				System.out.println("serviceId: " + serviceId);
//				System.out.println("nodeName: " + nodeName);
//				System.out.println("nodeType: " + nodeType);
//				System.out.println("groupType: " + groupType);
//				System.out.println("flowType: " + flowType);
//				System.out.println("resourceId: " + resourceId);

				loadRow(menuNode.getNodes(), treeNode + 1, rowForExcel);
			}

	}
}
