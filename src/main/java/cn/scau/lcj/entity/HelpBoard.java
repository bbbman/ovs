package cn.scau.lcj.entity;

import java.sql.Timestamp;

/**
 * HelpBoard entity. @author MyEclipse Persistence Tools
 */

public class HelpBoard implements java.io.Serializable {

	// Fields

	private Integer helpBoardId;
	private Integer userId;
	private String helpTitle;
	private String helpContent;
	private Timestamp helpBuildTime;
	private Integer helpLevel = 0;

	// Constructors

	/** default constructor */
	public HelpBoard() {
	}

	/** full constructor */
	public HelpBoard(Integer userId, String helpTitle, String helpContent,
			Timestamp helpBuildTime, Integer helpLevel) {
		this.userId = userId;
		this.helpTitle = helpTitle;
		this.helpContent = helpContent;
		this.helpBuildTime = helpBuildTime;
		this.helpLevel = helpLevel;
	}

	// Property accessors

	public Integer getHelpBoardId() {
		return this.helpBoardId;
	}

	public void setHelpBoardId(Integer helpBoardId) {
		this.helpBoardId = helpBoardId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getHelpTitle() {
		return this.helpTitle;
	}

	public void setHelpTitle(String helpTitle) {
		this.helpTitle = helpTitle;
	}

	public String getHelpContent() {
		return this.helpContent;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}

	public Timestamp getHelpBuildTime() {
		return this.helpBuildTime;
	}

	public void setHelpBuildTime(Timestamp helpBuildTime) {
		this.helpBuildTime = helpBuildTime;
	}

	public Integer getHelpLevel() {
		return this.helpLevel;
	}

	public void setHelpLevel(Integer helpLevel) {
		this.helpLevel = helpLevel;
	}

}