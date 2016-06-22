package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.scau.common.ObjectUtil;
import cn.scau.lcj.dao.ImageDao;
import cn.scau.lcj.dao.OptionDao;
import cn.scau.lcj.dao.OtherOptionDao;
import cn.scau.lcj.entity.createVote.Option;
import cn.scau.lcj.entity.createVote.OtherOption;
import cn.scau.lcj.service.OptionService;

@Service("OptionService")
public class OptionServiceImpl implements OptionService {

	@Autowired
	private OptionDao optionDao;
	@Autowired
	private OtherOptionDao otherOptionDao;
	@Autowired
	private ImageDao imageDao;

	@Override
	public Integer save(Option entity) {
		// TODO Auto-generated method stub
		return optionDao.save(entity);
	}

	@Override
	// 插入某一标题下的所有选项,以及根据图片id进行配置
	public void saveOptionAndUpdateImage(String[] optionArray, Integer titleId,
			Integer pageId) {
		// TODO Auto-generated method stub
		Integer optionId;
		Integer imageId;
		// 插入普通选项
		for (int i = 0; i < optionArray.length - 1; i++) {
			JSONObject json = JSONObject.parseObject(optionArray[i]);
			Option option = new Option();
			option.setOptionContent(json.getString("optionContent"));
			option.setOptionPosition(json.getInteger("optionPosition"));
			option.setTitleId(titleId);
			optionId = optionDao.save(option);
			imageId = json.getInteger("imageId");
			if (imageId != null) {
				// 有插图片
				String sqlString = new StringBuilder()
						.append("update image set ")
						.append("page_id = " + pageId)
						.append(" ,option_id = " + optionId)
						.append(" where image_id = " + imageId).toString();
				imageDao.updateImageById(sqlString);
			}
		}
		JSONObject json = JSONObject
				.parseObject(optionArray[optionArray.length - 1]);
		Integer useOtherOption = json.getInteger("otherOption");
		if (useOtherOption == 1) {
			// 启用其他项
			OtherOption otherOption = new OtherOption();
			otherOption.setOtherOptionContent(json
					.getString("otherOptionContent"));
			otherOption.setTitleId(titleId);
			otherOptionDao.save(otherOption);
		}
	}

	@Override
	public Integer saveOtherOption(OtherOption therOption) {
		// TODO Auto-generated method stub
		return otherOptionDao.save(therOption);
	}

	@Override
	public Option selectOptionByTitleSeq(Integer titleSeq, Integer optionSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select * from `option` ");
		sb.append(" where title_id = " + titleSeq);
		sb.append(" and option_id = " + optionSeq);
		List<Option> list = optionDao.selectOptionBySqlString(sb.toString());
		if (ObjectUtil.isEmptyList(list))
			return null;
		return list.get(0);
	}

	@Override
	public OtherOption selectOtherOptionByTitleSeq(Integer titleSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select * from other_option ");
		sb.append(" where title_id = " + titleSeq);
		List<OtherOption> list = otherOptionDao.selectOtherOptionBySqlString(sb
				.toString());
		if (ObjectUtil.isEmptyList(list))
			return null;
		return list.get(0);
	}

	@Override
	public void updateOptionTimesByObject(Option option) {
		// TODO Auto-generated method stub
		updateImageTimesByOptionSeq(option.getOptionId());
		optionDao.updateOptionBySqlString("update `option` set option_select_times = option_select_times+1 where option_id = "+option.getOptionId());
	}

	@Override
	public void updateOptionTimesByArray(Integer titleSeq, Integer[] optionSeq) {
		// TODO Auto-generated method stub
		if(optionSeq.length == 0)
			return;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE `option` set option_select_times = option_select_times+1 ");
		sb.append("where title_id = "+titleSeq);
		sb.append(" and option_id in ( ");
		for(int i=0; i<optionSeq.length-1; i++){
			sb.append(optionSeq[i]+",");
			//如果该项有图片，更新图片浏览次数
			updateImageTimesByOptionSeq(optionSeq[i]);
		}
		sb.append(optionSeq[optionSeq.length-1]+" )");
		optionDao.updateOptionBySqlString(sb.toString());		
	}
	public void updateImageTimesByOptionSeq(Integer optionSeq){
		StringBuilder sb = new StringBuilder();
		sb.append("update image set browse_times = browse_times+1 ");
		sb.append(" where option_id = "+optionSeq);
		imageDao.updateImageById(sb.toString());
	}

	@Override
	public void deleteByTitleSeq(Integer titleSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("delete from `option` where title_id = "+titleSeq);
		optionDao.deleteBySqlString(sb.toString());
	}

	@Override
	public void deleteOtherOptionByTitleSeq(Integer titleSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("delete from other_option where title_id = "+titleSeq);
		otherOptionDao.deleteBySqlString(sb.toString());
	}

	@Override
	public void update2ZreoByTitleSeq(Integer titleSeq) {
		// TODO Auto-generated method stub
		String sb = "update `option` set option_select_times = 0 where title_id = "+titleSeq;
		optionDao.updateOptionBySqlString(sb);
	}

	@Override
	public List<Option> selectByTitleSeq(Integer titleSeq) {
		// TODO Auto-generated method stub
		if(titleSeq==null)
			return null;
		String sb ="select * from `option` where title_id = "+titleSeq+
				" order by option_position";
		return optionDao.selectOptionBySqlString(sb);
	}
}
