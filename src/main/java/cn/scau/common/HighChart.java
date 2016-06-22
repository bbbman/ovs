package cn.scau.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HighChart {
	
	private JSONObject json;
	
	private JSONObject chart;
	
	private JSONObject title;
	
	private JSONObject subtitle;
	
	private JSONObject xAxis;
	
	private JSONObject yAxis;
	
	private JSONObject legend;
	
	private JSONObject plotOptions;
	
	private JSONObject tooltip;
	
	private JSONArray series;
	
	private JSONObject drilldown;
		
	
	public HighChart(){
		json = new JSONObject();
		chart = new JSONObject();
		title = new JSONObject();
		subtitle = new JSONObject();
		xAxis =  new JSONObject();
		yAxis = new JSONObject();
		legend = new JSONObject();
		plotOptions = new JSONObject();
		tooltip = new JSONObject();
		series = new JSONArray();
		drilldown = new JSONObject();
	}
	
	public void setChartType(String type){
		JSONObject chartJson = new JSONObject();
		chartJson.put("type", type);
		json.put("chart", chartJson);
	}
	
}
