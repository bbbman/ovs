package cn.scau.lcj.action.user.managercenter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.lcj.entity.User;
import cn.scau.lcj.entity.beanmapper.Image2Page;
import cn.scau.lcj.entity.createVote.Image;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.service.ImageService;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.struts.ExtendSupport;

import com.alibaba.fastjson.JSONObject;

public class ImageManagerAction extends ExtendSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5592154693284318966L;

	private Integer delImageId;
	
	private Integer[] delImageIds;

	@Autowired
	private ImageService imageService;

	@Autowired
	private PageService pageService;
		
	public Integer[] getDelImageIds() {
		return delImageIds;
	}
	public void setDelImageIds(Integer[] delImageIds) {
		this.delImageIds = delImageIds;
	}
	public Integer getDelImageId() {
		return delImageId;
	}
	public void setDelImageId(Integer delImageId) {
		this.delImageId = delImageId;
	}
	public String execute() {
		Log.log("图片管理网页开始========================");
		request.setAttribute("pageActive", "managerCenter");
		User user = (User) session.get(Constant.USER_SESSION);
		List<Image2Page> imageList = new ArrayList<Image2Page>();
		List<Image> list = imageService.selectImageByUserId(user.getUserId());
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Image image = list.get(i);
				Page page = pageService.selectPageByPrimaryKey(image
						.getPageId());
				if (page == null)
					imageList.add(new Image2Page(image, "", null));
				else
					imageList.add(new Image2Page(image, page.getUrl(), page
							.getMainTitle()));
			}
		} else
			imageList = null;
		
		request.setAttribute("imageList", imageList);
		Log.log("图片管理网页结束========================");
		return SUCCESS;
	}
	public void deleteOneImage(){
		Log.log("删除单张图片开始========================");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if(delImageId==null){
			setReturnJson(json,2,1,"图片id不能为空","");
			responseJson(json);
			Log.log("删除单张图片结束========================");
			return;
		}
		imageService.deleteOneImageById(delImageId,user.getUserId());
		setReturnJson(json,1,0,"图片删除成功","");
		responseJson(json);
		Log.log("删除单张图片结束========================");
		return;
	}
	public void deleteMoreImage(){
		Log.log("批量删除图片开始========================");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if(delImageIds==null){
			setReturnJson(json,2,1,"图片id不能为空","");
			responseJson(json);
			Log.log("批量删除图片开始========================");
			return;
		}
		imageService.deleteMoreimageByIds(delImageIds,user.getUserId());
		setReturnJson(json,1,0,"图片删除成功","");
		responseJson(json);
		Log.log("批量删除图片开始========================");
		return;
	}
}
