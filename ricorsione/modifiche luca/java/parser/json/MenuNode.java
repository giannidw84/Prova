package parser.json;

import java.util.List;

public class MenuNode {

	private int nodeId;
	private String nodeName;
	private String nodeType;
	private String groupType;
	private String status;
	private Double startValidityTs;
	private Double endValidityTs;
	private Resource resource;
	private String tag;
	private String flowType;
    private List<MenuNode> nodes;

	public MenuNode() {
		super();
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getStartValidityTs() {
		return startValidityTs;
	}

	public void setStartValidityTs(Double startValidityTs) {
		this.startValidityTs = startValidityTs;
	}

	public Double getEndValidityTs() {
		return endValidityTs;
	}

	public void setEndValidityTs(Double endValidityTs) {
		this.endValidityTs = endValidityTs;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<MenuNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuNode> nodes) {
		this.nodes = nodes;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	
	

}
