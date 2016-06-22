package cn.scau.lcj.service;

import java.sql.Timestamp;

import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.entity.createVote.Title;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface AnalysisService {
	JSONObject getTitleAnalis(Integer timeType,Page page);
	JSONObject getOptionAnalis(Integer timeType,Page page,Title title);
}
