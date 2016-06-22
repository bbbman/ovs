package cn.scau.lcj.entity.createVote;

import java.sql.Timestamp;

/**
 * Page entity. @author MyEclipse Persistence Tools
 */

public class Page implements java.io.Serializable {

	// Fields

	private Integer pageId;
	private Integer userId;
	private String url;
	private Timestamp buildTime;
	private String buildTimeString;
	
	private Timestamp modifyTime;
	private Short disable = 0;//禁用,0代表否,1代表是
	private Integer browseTimes = 0;//
	private Integer submitTimes = 0;//
	
	private Timestamp deadLine;
	private String deadLineString;
	
	private String mainTitle ="未添加标题";
	private Integer fastVote = 0;//快速投票,0代表否，1代表是
	private Integer privateVote = 0;//投票隐私,0代表任何人可查看和投票,1代表凭密码查看和投票
	private String pagePassword;
	private Integer seeAfterVote = 0;//投票后看结果,0代表否,1代表是
	private String voteDesc;
	private String displayAfterVote;
	private Integer addCollect =0;//添加收集用户信息的集合，0代表否，1代表是
	private Integer agreeTerm = 0;//同意条款，0代表否，1代表是
	
	private Integer isImageVote = 0;//默认是没图片
	private String  imageVoteString = "普通投票";
	
	private String username;

	// Constructors

	/** default constructor */
	public Page() {
	}

	/** full constructor */
	public Page(Integer userId, String url, Timestamp buildTime,
			Timestamp modifyTime, Short disable, Integer browseTimes,
			Integer submitTimes, Timestamp deadLine, String mainTitle,
			Integer fastVote, Integer privateVote, String pagePassword,
			Integer seeAfterVote, String voteDesc, String displayAfterVote,
			Integer addCollect, Integer agreeTerm,Integer isImageVote) {
		this.userId = userId;
		this.url = url;
		this.buildTime = buildTime;
		this.modifyTime = modifyTime;
		this.disable = disable;
		this.browseTimes = browseTimes;
		this.submitTimes = submitTimes;
		this.deadLine = deadLine;
		this.mainTitle = mainTitle;
		this.fastVote = fastVote;
		this.privateVote = privateVote;
		this.pagePassword = pagePassword;
		this.seeAfterVote = seeAfterVote;
		this.voteDesc = voteDesc;
		this.displayAfterVote = displayAfterVote;
		this.addCollect = addCollect;
		this.agreeTerm = agreeTerm;
		this.isImageVote = isImageVote;
	}

	// Property accessors

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getBuildTime() {
		return this.buildTime;
	}

	public void setBuildTime(Timestamp buildTime) {
		this.buildTime = buildTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Short getDisable() {
		return this.disable;
	}

	public void setDisable(Short disable) {
		this.disable = disable;
	}

	public Integer getBrowseTimes() {
		return this.browseTimes;
	}

	public void setBrowseTimes(Integer browseTimes) {
		this.browseTimes = browseTimes;
	}

	public Integer getSubmitTimes() {
		return this.submitTimes;
	}

	public void setSubmitTimes(Integer submitTimes) {
		this.submitTimes = submitTimes;
	}

	public Timestamp getDeadLine() {
		return this.deadLine;
	}

	public void setDeadLine(Timestamp deadLine) {
		this.deadLine = deadLine;
	}

	public String getMainTitle() {
		return this.mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public Integer getFastVote() {
		return this.fastVote;
	}

	public void setFastVote(Integer fastVote) {
		this.fastVote = fastVote;
	}

	public Integer getPrivateVote() {
		return this.privateVote;
	}

	public void setPrivateVote(Integer privateVote) {
		this.privateVote = privateVote;
	}

	public String getPagePassword() {
		return this.pagePassword;
	}

	public void setPagePassword(String pagePassword) {
		this.pagePassword = pagePassword;
	}

	public Integer getSeeAfterVote() {
		return this.seeAfterVote;
	}

	public void setSeeAfterVote(Integer seeAfterVote) {
		this.seeAfterVote = seeAfterVote;
	}

	public String getVoteDesc() {
		return this.voteDesc;
	}

	public void setVoteDesc(String voteDesc) {
		this.voteDesc = voteDesc;
	}

	public String getDisplayAfterVote() {
		return this.displayAfterVote;
	}

	public void setDisplayAfterVote(String displayAfterVote) {
		this.displayAfterVote = displayAfterVote;
	}

	public Integer getAddCollect() {
		return this.addCollect;
	}

	public void setAddCollect(Integer addCollect) {
		this.addCollect = addCollect;
	}

	public Integer getAgreeTerm() {
		return this.agreeTerm;
	}

	public void setAgreeTerm(Integer agreeTerm) {
		this.agreeTerm = agreeTerm;
	}

	public Integer getIsImageVote() {
		return isImageVote;
	}

	public void setIsImageVote(Integer isImageVote) {
		this.isImageVote = isImageVote;
	}

	public String getBuildTimeString() {
		return buildTimeString;
	}

	public void setBuildTimeString(String buildTimeString) {
		this.buildTimeString = buildTimeString;
	}

	public String getDeadLineString() {
		return deadLineString;
	}

	public void setDeadLineString(String deadLineString) {
		this.deadLineString = deadLineString;
	}

	public String getImageVoteString() {
		return imageVoteString;
	}

	public void setImageVoteString(String imageVoteString) {
		this.imageVoteString = imageVoteString;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}				

}