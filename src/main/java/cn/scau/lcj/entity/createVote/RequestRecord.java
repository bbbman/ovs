package cn.scau.lcj.entity.createVote;

import java.sql.Timestamp;

/**
 * RequestRecord entity. @author MyEclipse Persistence Tools
 */
public class RequestRecord implements java.io.Serializable {

	// Fields

	private Integer requestRecordSeq;
	private Integer requestPageId;
	private String requestIp;
	private Timestamp requestDate;
	private String requestContent;
	private String requestMacAddress;
	private String otherOptionIds;

	// Constructors

	/** default constructor */
	public RequestRecord() {
	}

	/** full constructor */
	public RequestRecord(Integer requestPageId, String requestIp,
			Timestamp requestDate, String requestContent,
			String requestMacAddress, String otherOptionId) {
		this.requestPageId = requestPageId;
		this.requestIp = requestIp;
		this.requestDate = requestDate;
		this.requestContent = requestContent;
		this.requestMacAddress = requestMacAddress;
		this.otherOptionIds = otherOptionIds;
	}

	// Property accessors

	public Integer getRequestRecordSeq() {
		return this.requestRecordSeq;
	}

	public void setRequestRecordSeq(Integer requestRecordSeq) {
		this.requestRecordSeq = requestRecordSeq;
	}

	public Integer getRequestPageId() {
		return this.requestPageId;
	}

	public void setRequestPageId(Integer requestPageId) {
		this.requestPageId = requestPageId;
	}

	public String getRequestIp() {
		return this.requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public Timestamp getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestContent() {
		return this.requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getRequestMacAddress() {
		return this.requestMacAddress;
	}

	public void setRequestMacAddress(String requestMacAddress) {
		this.requestMacAddress = requestMacAddress;
	}

	public String getOtherOptionIds() {
		return this.otherOptionIds;
	}

	public void setOtherOptionIds(String otherOptionIds) {
		this.otherOptionIds = otherOptionIds;
	}
}
