var LineData ={
        chart: {
            type: 'line'
        },
        title: {
            text: 'Monthly Average Temperature'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: ['Jan1', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: '票数'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: 'Tokyo',
            data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
        }, {
            name: 'London',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }]
    };
var RecData ={
		 chart: {
	            type: 'column'
	        },
	        title: {
	            text: ''
	        },
	        subtitle: {
	            text: ''
	        },
	        xAxis: {
	            type: 'category'
	        },
	        yAxis: {
	            title: {
	                text: '票数'
	            }
	        },
	        legend: {
	            enabled: false
	        },
	        plotOptions: {
	            series: {
	                borderWidth: 0,
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.y}票'
	                }
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b><br/>'
	        },
	        series: [{
	            name: '投票标题',
	            colorByPoint: true,
	            data: [{
	                name: '乐观',
	                y: 56.33,
	                drilldown: '乐观'
	            }, {
	                name: '中立',
	                y: 24.03,
	                drilldown: '中立'
	            }, {
	                name: '不看好',
	                y: 10.38,
	                drilldown: '不看好'
	            }, {
	                name: '非常不看好',
	                y: 4.77,
	                drilldown: '非常不看好'
	            }, {
	                name: '极度悲观',
	                y: 0.91,
	                drilldown: '极度悲观'
	            }, {
	                name: '其他',
	                y: 0.2,
	                drilldown: null
	            }]
	        }],
	        drilldown: {
	            series: [{
	                name: 'Microsoft Internet Explorer',
	                id: '乐观',
	                data: [
	                    [
	                        'v11.0',
	                        24.13
	                    ],
	                    [
	                        'v8.0',
	                        17.2
	                    ],
	                    [
	                        'v9.0',
	                        8.11
	                    ],
	                    [
	                        'v10.0',
	                        5.33
	                    ],
	                    [
	                        'v6.0',
	                        1.06
	                    ],
	                    [
	                        'v7.0',
	                        0.5
	                    ]
	                ]
	            }, {
	                name: 'Chrome',
	                id: 'Chrome',
	                data: [
	                    [
	                        'v40.0',
	                        5
	                    ],
	                    [
	                        'v41.0',
	                        4.32
	                    ],
	                    [
	                        'v42.0',
	                        3.68
	                    ],
	                    [
	                        'v39.0',
	                        2.96
	                    ],
	                    [
	                        'v36.0',
	                        2.53
	                    ],
	                    [
	                        'v43.0',
	                        1.45
	                    ],
	                    [
	                        'v31.0',
	                        1.24
	                    ],
	                    [
	                        'v35.0',
	                        0.85
	                    ],
	                    [
	                        'v38.0',
	                        0.6
	                    ],
	                    [
	                        'v32.0',
	                        0.55
	                    ],
	                    [
	                        'v37.0',
	                        0.38
	                    ],
	                    [
	                        'v33.0',
	                        0.19
	                    ],
	                    [
	                        'v34.0',
	                        0.14
	                    ],
	                    [
	                        'v30.0',
	                        0.14
	                    ]
	                ]
	            }, {
	                name: 'Firefox',
	                id: 'Firefox',
	                data: [
	                    [
	                        'v35',
	                        2.76
	                    ],
	                    [
	                        'v36',
	                        2.32
	                    ],
	                    [
	                        'v37',
	                        2.31
	                    ],
	                    [
	                        'v34',
	                        1.27
	                    ],
	                    [
	                        'v38',
	                        1.02
	                    ],
	                    [
	                        'v31',
	                        0.33
	                    ],
	                    [
	                        'v33',
	                        0.22
	                    ],
	                    [
	                        'v32',
	                        0.15
	                    ]
	                ]
	            }, {
	                name: 'Safari',
	                id: 'Safari',
	                data: [
	                    [
	                        'v8.0',
	                        2.56
	                    ],
	                    [
	                        'v7.1',
	                        0.77
	                    ],
	                    [
	                        'v5.1',
	                        0.42
	                    ],
	                    [
	                        'v5.0',
	                        0.3
	                    ],
	                    [
	                        'v6.1',
	                        0.29
	                    ],
	                    [
	                        'v7.0',
	                        0.26
	                    ],
	                    [
	                        'v6.2',
	                        0.17
	                    ]
	                ]
	            }, {
	                name: 'Opera',
	                id: 'Opera',
	                data: [
	                    [
	                        'v12.x',
	                        0.34
	                    ],
	                    [
	                        'v28',
	                        0.24
	                    ],
	                    [
	                        'v27',
	                        0.17
	                    ],
	                    [
	                        'v29',
	                        0.16
	                    ]
	                ]
	            }]
	        }	    
};
Highcharts.setOptions({
    lang:{
       contextButtonTitle:"图表导出菜单",
       decimalPoint:".",
       downloadJPEG:"下载JPEG图片",
       downloadPDF:"下载PDF文件",
       downloadPNG:"下载PNG文件",
       downloadSVG:"下载SVG文件",
       drillUpText:"返回{series.name}",
       loading:"加载中",
       months:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
       noData:"没有数据",
       numericSymbols: [ "千" , "兆" , "G" , "T" , "P" , "E"],
       printChart:"打印图表",
       resetZoom:"恢复缩放",
       resetZoomTitle:"恢复图表",
       shortMonths: [ "Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"],
       thousandsSep:",",
       weekdays: ["星期一", "星期二", "星期三", "星期三", "星期四", "星期五", "星期六","星期天"]
    }
  });
function getTree(){
	var pageSeq = $('#container').attr('page');
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/user/analysis!getTree.action',
		dataType:'json',
		data:{'pageSeq':pageSeq},
		success:function(msg){
			if(msg.status==1){
				//提交成功	
				console.log(JSON.stringify(msg.data))
				zNodes = msg.data;
				console.log(zNodes);
				$.fn.zTree.init($("#tree-item"), setting, zNodes);				
			}else{
				alert(msg.message);
			}
			console.log(msg);			
		},
		error:function(){
			console.log("=========ddd");
		}
	});
}

function getRecData(){
	var pageSeq = $('#container').attr('page');
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/user/analysis!getRecData.action',
		dataType:'json',
		data:{'pageSeq':pageSeq},
		success:function(msg){
			if(msg.status==1){
				//提交成功					
				RecData.title.text = msg.data.text;
				RecData.subtitle.text = '点击跳到投票页面. Source: <a href="http://netmarketshare.com">netmarketshare.com</a>.';
				RecData.series[0].data = msg.data.seriesData;	
				RecData.drilldown.series = msg.data.drilldown;
				 showRecView(msg.data);
			}else{
				alert(msg.message);
			}
			console.log(msg);			
		},
		error:function(){
			console.log("=========ddd");
		}
	});
}
function showRecView(result){
	
	$(function () {
	    // Create the chart
	    
	    $('#container').highcharts(RecData);	
	    var htmlstr = "<button id=\"toLine\" class=\"btn btn-sm aButton\">切换到曲线图</button><button id=\"back\" class=\"btn btn-sm aButton\">返回投票页面</button>";	
	    $('#buttonArea').html(htmlstr);
	    $('#back').on('click',function(){
	    	window.location.href='user/managercenter/vote-manager.action';
	    });
	    $('#toLine').on('click',function(){
	    	$('.menu-tree-container').css('display','block');
	    	if($('.tree-bar-switch').find('i').hasClass('tree-open')){
	    		$('#outerContainer').css('padding-left','261px');
	        } else {
	        	$('#outerContainer').css('padding-left','20px');
	        }
	    	var htmlstr = "<select id=\"timeType\" class=\"form-control\">";
	    	htmlstr += "<option value=\"0\" checked>日</option>";
	    	htmlstr += "<option value=\"1\">时</option>";
	    	htmlstr += "</select>";
	    	htmlstr += "<button id=\"toRec\" class=\"btn btn-sm aButton\">切换到柱形图</button>";
	    	htmlstr += "<button id=\"back\" class=\"btn btn-sm aButton\">返回投票页面</button>";
	    	$('#buttonArea').html(htmlstr);
	    	$('#back').on('click',function(){
	    		window.location.href='user/managercenter/vote-manager.action';
	    	});
	    	$('#toRec').on('click',function(){
	    		$('.menu-tree-container').css('display','none');
	    	    $('#outerContainer').css('padding-left','0px');
	    	    showRecView();
	    	});
	    	$('#timeType').on('change',function(){
	    		getLineData(cc);
	    	});	    	
	    	getLineData(cc);
	    });
	    
	});
}
var cc={
		id:$('#container').attr('page'),
       pId:0
};
function beforeClick(treeId, treeNode) {
	var pageSeq = $('#container').attr('page');
	if(treeNode.pId ==pageSeq || treeNode.pId==0){
		cc=treeNode;	
		getLineData(treeNode);
	}	
}

function getLineData(myTreeNode){
	var pageSeq = $('#container').attr('page');
	var timeType = $('#timeType').val();
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/user/analysis!getLineData.action',
		dataType:'json',
		data:{'pageSeq':pageSeq,'parentSeq':myTreeNode.pId,'dataSeq':myTreeNode.id,'dataType':timeType},
		success:function(msg){
			if(msg.status==1){
				//提交成功
				console.log(msg.data.categories);
				console.log(msg.data.series);
				LineData.xAxis.categories=msg.data.categories;
				LineData.series = msg.data.series;
				LineData.title.text=msg.data.text;
				showLine();
			}else{
				alert(msg.message);
			}
			console.log(msg);			
		},
		error:function(){			
		}
	});
}
function showLine(){
	$('#container').highcharts(LineData);
}
$(document).ready(function(){
	getTree();	
	getRecData();
});