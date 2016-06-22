package cn.scau.lcj.entity.beanmapper;

import java.sql.Timestamp;

import cn.scau.lcj.entity.createVote.Image;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.utils.common.StringUtil;

public class Image2Page {
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
	
	private String url;
	private String mainTitle;
	
	
	
	
	public Image2Page(Image image,String url,String mainTitle) {
		super();
		this.imageId = image.getImageId();
		this.optionId = image.getOptionId();
		this.userId = image.getUserId();
		this.pageId = image.getPageId();
		this.imagePath = image.getImagePath();
		this.oldName = image.getOldName();
		this.nowName = image.getNowName();
		this.uploadTime = image.getUploadTime();
		this.uploadTimeString = image.getUploadTimeString();
		this.imageType = image.getImageType();
		this.browseTimes = image.getBrowseTimes();
		
		this.url = url;
		this.mainTitle = mainTitle==null?"未添加标题":mainTitle;
	}
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getNowName() {
		return nowName;
	}
	public void setNowName(String nowName) {
		this.nowName = nowName;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getUploadTimeString() {
		return uploadTimeString;
	}
	public void setUploadTimeString(String uploadTimeString) {
		this.uploadTimeString = uploadTimeString;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public Integer getBrowseTimes() {
		return browseTimes;
	}
	public void setBrowseTimes(Integer browseTimes) {
		this.browseTimes = browseTimes;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMainTitle() {
		return mainTitle;
	}
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
	
}
