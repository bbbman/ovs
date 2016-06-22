package cn.scau.lcj.entity;

import java.sql.Timestamp;

import cn.scau.common.StringUtil;



/**
 * MessageBoard entity. @author MyEclipse Persistence Tools
 */

public class MessageBoard implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private Integer userId;
	private String content;
	private Timestamp buildTime;
	private String username;
	private Integer parentId = 0;//没parent
	private String  buildTimeString;
	private Integer dealType =0;//未处理
	private Integer receiveId = 0;//没接收者
	private Integer position = 0;//祖先位置
	private String receiveName;//接收者名字

	// Constructors

	/** default constructor */
	public MessageBoard() {
	}

	/** minimal constructor */
	public MessageBoard(Integer userId, Timestamp buildTime) {
		this.userId = userId;
		this.buildTime = buildTime;
	}

	/** full constructor */
	public MessageBoard(Integer userId, String content, Timestamp buildTime,String username) {
		this.userId = userId;
		this.content = content;
		this.buildTime = buildTime;
		this.username = username;
	}

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getBuildTime() {
		return this.buildTime;
	}

	public void setBuildTime(Timestamp buildTime) {
		this.buildTime = buildTime;		
		buildTimeString = StringUtil.Timestamp2String(buildTime);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBuildTimeString() {
		return buildTimeString;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

	public Integer getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}					
	
}