package cn.scau.lcj.entity.createVote;

/**
 * Option entity. @author MyEclipse Persistence Tools
 */

public class Option implements java.io.Serializable {

	// Fields

	private Integer optionId;
	private Integer titleId;
	private String optionContent;
	private Integer optionSelectTimes=0;
	private Integer optionPosition;
	private String imagePath = "";

	// Constructors

	/** default constructor */
	public Option() {
	}

	/** full constructor */
	public Option(Integer titleId, String optionContent,
			Integer optionSelectTimes, Integer optionPosition) {
		this.titleId = titleId;
		this.optionContent = optionContent;
		this.optionSelectTimes = optionSelectTimes;
		this.optionPosition = optionPosition;
	}

	// Property accessors

	public Integer getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Integer getTitleId() {
		return this.titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getOptionContent() {
		return this.optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public Integer getOptionSelectTimes() {
		return this.optionSelectTimes;
	}

	public void setOptionSelectTimes(Integer optionSelectTimes) {
		this.optionSelectTimes = optionSelectTimes;
	}

	public Integer getOptionPosition() {
		return this.optionPosition;
	}

	public void setOptionPosition(Integer optionPosition) {
		this.optionPosition = optionPosition;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	

}