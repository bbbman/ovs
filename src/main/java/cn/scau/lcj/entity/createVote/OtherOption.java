package cn.scau.lcj.entity.createVote;

import java.sql.Timestamp;

/**
 * OtherOption entity. @author MyEclipse Persistence Tools
 */

public class OtherOption implements java.io.Serializable {

	// Fields

	private Integer otherOptionId;
	private Integer titleId;
	private String otherOptionContent;
	private Integer otherOptionSelectTimes;//提交后被选中的次数
	private Short otherOptionDisable;//禁用,0代表否,1代表是
	private Timestamp otherBuildTime;
	// Constructors

	/** default constructor */
	public OtherOption() {
	}
	
	

	public OtherOption(Integer titleId, String otherOptionContent) {
		super();
		this.titleId = titleId;
		this.otherOptionContent = otherOptionContent;
		this.otherBuildTime = new Timestamp(System.currentTimeMillis());
	}



	/** full constructor */
	public OtherOption(Integer titleId, String otherOptionContent,
			Integer otherOptionSelectTimes) {
		this.titleId = titleId;
		this.otherOptionContent = otherOptionContent;
		this.otherOptionSelectTimes = otherOptionSelectTimes;
	}

	// Property accessors

	public Integer getOtherOptionId() {
		return this.otherOptionId;
	}

	public void setOtherOptionId(Integer otherOptionId) {
		this.otherOptionId = otherOptionId;
	}

	public Integer getTitleId() {
		return this.titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getOtherOptionContent() {
		return this.otherOptionContent;
	}

	public void setOtherOptionContent(String otherOptionContent) {
		this.otherOptionContent = otherOptionContent;
	}

	public Integer getOtherOptionSelectTimes() {
		return this.otherOptionSelectTimes;
	}

	public void setOtherOptionSelectTimes(Integer otherOptionSelectTimes) {
		this.otherOptionSelectTimes = otherOptionSelectTimes;
	}

	public Short getOtherOptionDisable() {
		return otherOptionDisable;
	}

	public void setOtherOptionDisable(Short otherOptionDisable) {
		this.otherOptionDisable = otherOptionDisable;
	}



	public Timestamp getOtherBuildTime() {
		return otherBuildTime;
	}

	public void setOtherBuildTime(Timestamp otherBuildTime) {
		this.otherBuildTime = otherBuildTime;
	}
}