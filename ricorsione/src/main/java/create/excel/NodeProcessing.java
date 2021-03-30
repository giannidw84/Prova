package create.excel;

import java.util.List;

import parser.json.MenuNode;

public class NodeProcessing {

	public void loadRow(List<MenuNode> nodeList, int treeNode, List<RowData> rowForExcel) throws Exception {

		if (nodeList != null) {

			for (int i = 0; i < nodeList.size(); i++) {

				int serviceId = 0;
				String nodeName = "";
				String nodeType = "";
				String groupType = "";
				String flowType = "";
				int resourceId = 0;

				if (nodeList.get(i).getNodeName() != null) {
					nodeName = nodeList.get(i).getNodeName();
				}

				if (nodeList.get(i).getNodeType() != null) {
					nodeType = nodeList.get(i).getNodeType();
				}

				if (nodeList.get(i).getGroupType() != null) {
					groupType = nodeList.get(i).getGroupType();
				}
				if (nodeList.get(i).getFlowType() != null) {
					flowType = nodeList.get(i).getFlowType();
				}
				if (nodeList.get(i).getResource() != null) {
					resourceId = nodeList.get(i).getResource().getId();
				}
				if (nodeType.contains("service")) {
					serviceId = nodeList.get(i).getNodeId();
				}

				rowForExcel.add(new RowData(treeNode, serviceId, nodeName, nodeType, groupType, flowType, resourceId));

//				System.out.println("treeNode: " + treeNode);
//				System.out.println("serviceId: " + serviceId);
//				System.out.println("nodeName: " + nodeName);
//				System.out.println("nodeType: " + nodeType);
//				System.out.println("groupType: " + groupType);
//				System.out.println("flowType: " + flowType);
//				System.out.println("resourceId: " + resourceId);

				List<MenuNode> nodeListNew = nodeList.get(i).getNodes();
				loadRow(nodeListNew, treeNode + 1, rowForExcel);
			}

		}

	}
}
