package cn.scau.lcj.entity;

import java.sql.Timestamp;

import cn.scau.common.StringUtil;

/**
 * HelpCenter entity. @author MyEclipse Persistence Tools
 */

public class HelpCenter implements java.io.Serializable {

	// Fields

	private Integer helpId;
	private Integer userId;
	private String username;
	private String content;
	private Integer parentId = 0;
	private Timestamp buildTime;
	private String buildTimeString;
	private Integer helpLevel = 0;
	private Integer helpDealType = 0;

	// Constructors

	/** default constructor */
	public HelpCenter() {
	}

	/** full constructor */
	public HelpCenter(Integer userId, String username, String content,
			Integer parentId) {
		this.userId = userId;
		this.username = username;
		this.content = content;
		this.parentId = parentId;
	}

	// Property accessors

	public Integer getHelpId() {
		return this.helpId;
	}

	public void setHelpId(Integer helpId) {
		this.helpId = helpId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Timestamp getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Timestamp buildTime) {
		this.buildTime = buildTime;		
		buildTimeString = StringUtil.Timestamp2String(buildTime);
	}

	public String getBuildTimeString() {
		return buildTimeString;
	}

	public Integer getHelpLevel() {
		return helpLevel;
	}

	public void setHelpLevel(Integer helpLevel) {
		this.helpLevel = helpLevel;
	}

	public Integer getHelpDealType() {
		return helpDealType;
	}

	public void setHelpDealType(Integer helpDealType) {
		this.helpDealType = helpDealType;
	}
	

}