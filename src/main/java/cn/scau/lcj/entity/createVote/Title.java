package cn.scau.lcj.entity.createVote;

/**
 * Title entity. @author MyEclipse Persistence Tools
 */

public class Title implements java.io.Serializable {

	// Fields

	private Integer titleId;
	private Integer pageId;
	private String titleContent;
	private Integer titleType = 0;//投票类型，0代表单选，1代表多选
	private Integer otherOption = 0;//启用其他项,0代表否，1代表是
	private Integer titleSelectTimes = 0;
	private Integer titlePosition;
	private Integer otherOptionSelectTimes = 0;//其他项被选择的次数
	private String titleOtherContent;
	// Constructors

	/** default constructor */
	public Title() {
	}

	/** full constructor */
	public Title(Integer pageId, String titleContent, Integer titleType,
			Integer otherOption, Integer titleSelectTimes, Integer titlePosition) {
		this.pageId = pageId;
		this.titleContent = titleContent;
		this.titleType = titleType;
		this.otherOption = otherOption;
		this.titleSelectTimes = titleSelectTimes;
		this.titlePosition = titlePosition;
	}

	// Property accessors

	public Integer getTitleId() {
		return this.titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getTitleContent() {
		return this.titleContent;
	}

	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}

	public Integer getTitleType() {
		return this.titleType;
	}

	public void setTitleType(Integer titleType) {
		this.titleType = titleType;
	}

	public Integer getOtherOption() {
		return this.otherOption;
	}

	public void setOtherOption(Integer otherOption) {
		this.otherOption = otherOption;
	}

	public Integer getTitleSelectTimes() {
		return this.titleSelectTimes;
	}

	public void setTitleSelectTimes(Integer titleSelectTimes) {
		this.titleSelectTimes = titleSelectTimes;
	}

	public Integer getTitlePosition() {
		return this.titlePosition;
	}

	public void setTitlePosition(Integer titlePosition) {
		this.titlePosition = titlePosition;
	}

	public Integer getOtherOptionSelectTimes() {
		return otherOptionSelectTimes;
	}

	public void setOtherOptionSelectTimes(Integer otherOptionSelectTimes) {
		this.otherOptionSelectTimes = otherOptionSelectTimes;
	}

	public String getTitleOtherContent() {
		return titleOtherContent;
	}

	public void setTitleOtherContent(String titleOtherContent) {
		this.titleOtherContent = titleOtherContent;
	}
}