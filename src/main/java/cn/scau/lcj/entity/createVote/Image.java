package cn.scau.lcj.entity.createVote;

import java.sql.Timestamp;

import cn.scau.lcj.utils.common.StringUtil;

/**
 * Image entity. @author MyEclipse Persistence Tools
 */

public class Image implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Integer optionId;
	private Integer userId;
	private Integer pageId;
	private String imagePath;
	private String oldName;
	private String nowName;
	private Timestamp uploadTime;
	private String uploadTimeString;
	private String imageType;
	private Integer browseTimes = 0;//点击次数，默认是0

	// Constructors

	/** default constructor */
	public Image() {
	}

	/** full constructor */
	public Image(Integer optionId, Integer userId, Integer pageId,
			String imagePath, String oldName, String nowName,
			Timestamp uploadTime,String imageType,Integer browseTimes) {
		this.optionId = optionId;
		this.userId = userId;
		this.pageId = pageId;
		this.imagePath = imagePath;
		this.oldName = oldName;
		this.nowName = nowName;
		this.uploadTime = uploadTime;
		this.imageType = imageType;
		this.browseTimes = browseTimes;
	}

	// Property accessors
	
	

	
	public Integer getImageId() {
		return this.imageId;
	}

	public Integer getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(Integer browseTimes) {
		this.browseTimes = browseTimes;
	}

	public String getUploadTimeString() {
		return uploadTimeString;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Integer getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getOldName() {
		return this.oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNowName() {
		return this.nowName;
	}

	public void setNowName(String nowName) {
		this.nowName = nowName;
	}

	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
		this.uploadTimeString = StringUtil.getStringFromTimestamp(uploadTime, "");
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	

}