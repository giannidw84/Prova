package create.excel;

public class RowData {
	private int profondita;
	private int serviceId;
	private String nodeName;
	private String nodeType;
	private String groupType;
	private String flowType;
	private int resourceId;

	public RowData(int profondita, int serviceId, String nodeName, String nodeType, String groupType, String flowType,
			int resourceId) {

		this.serviceId = serviceId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.groupType = groupType;
		this.flowType = flowType;
		this.resourceId = resourceId;
		this.profondita = profondita;

	}

	public int getProfondita() {
		return profondita;
	}

	public void setProfondita(int profondita) {
		this.profondita = profondita;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
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

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

}
