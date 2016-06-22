package cn.scau.lcj.action.common;

import java.io.File;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.common.FileUtil;
import cn.scau.common.UUIDUtil;
import cn.scau.common.UpLoadUtil;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.entity.createVote.Image;
import cn.scau.lcj.service.ImageService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.struts.ExtendSupport;

import com.alibaba.fastjson.JSONObject;

public class FileUpLoadAction extends ExtendSupport {
	private static final long serialVersionUID = 572146812454l;
	private File myImage;
	private String myImageContentType;
	private String myImageFileName;
	private String caption;
	@Autowired
	private ImageService imageService;

	public void doee() {
		Log.log("上传文件开始========================");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if (myImage == null) {
			setReturnJson(json, 2, 1, "文件为空", "");
			responseJson(json);
			Log.log("上传文件结束========================");
			return;
		}

		// 找到用户标识符号
		String filePath = UpLoadUtil.biuldImagePath("cc", myImageContentType);
		File imageFile = FileUtil.createFile(filePath);
		// 判断file是否存在，如果不存在，则自动创建一个
		if (imageFile == null) {
			setReturnJson(json, 2, 2, "创建文件失败", "");
			responseJson(json);
			Log.log("上传文件结束========================");
			return;
		}
		UpLoadUtil.copy(myImage, imageFile); // 把图片写入到上面设置的路径里
		Image image = new Image();
		image.setImagePath(filePath);
		image.setUploadTime(new Timestamp(System.currentTimeMillis()));
		image.setOldName(myImageFileName);// 原来的名字
		image.setNowName(imageFile.getName());
		image.setImageType(myImageContentType);
		image.setUserId(user.getUserId());
		Integer imageId = imageService.saveImage(image);

		setReturnJson(json, 1, 0, "上传成功", "");
		json.put("imagePath", image.getImagePath());
		json.put("imageId", imageId);
		responseJson(json);
		Log.log("上传文件结束========================");
		return;
	} 
	
	public File getMyImage() {
		return myImage;
	}

	public void setMyImage(File myImage) {
		this.myImage = myImage;
	}

	public String getMyImageContentType() {
		return myImageContentType;
	}

	public void setMyImageContentType(String myImageContentType) {
		this.myImageContentType = myImageContentType;
	}

	public String getMyImageFileName() {
		return myImageFileName;
	}

	public void setMyImageFileName(String myImageFileName) {
		this.myImageFileName = myImageFileName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}
