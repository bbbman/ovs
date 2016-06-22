<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="nav/userNav.jsp"%>
	<script type="text/javascript"
		src="<%=basePath%>/bootstrap/js/jquery.json.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/bootstrap/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/uploadImage.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/vote.js"></script>
</head>
<body onload="loadTemp();">	
	<div class="panel" style="background-color: white;">
		<h2>创建投票</h2>
		<div class="form-horizontal">
			<legend>投票基本设置</legend>
			<div class="vote-question">
				<div class="form-group">
					<label class="control-label"> 投票标题: </label>
					<div class="controls">
						<input name="titleContent" type="text" class="form-control"
							style="width:300px" placeholder="请输入投票标题">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label"> 快速投票: </label>
					<div class="controls" style="padding-top:5px">
						<input type="radio" name="fastVote" value="0" checked> 否
						&nbsp;<span style="color:#999999;">点击“提交投票”按钮后提交投票</span>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label"> </label>
					<div class="controls" style="padding-top:5px">
						<input type="radio" name="fastVote" value="1"> 是 &nbsp; <span
							style="color:#999999;"> 选择投票项后自动提交投票，不显示“提交投票“按钮（只适用于单选投票）
						</span>
					</div>
				</div>


				<div class="form-group" id="moreSet">
					<label class="control-label"> </label>
					<div class="controls" style="padding-top:5px">
						<button type="button" class="btn btn-default"
							onclick="seeMoreSet();">更多设置</button>
					</div>
				</div>
				<div id="ViewOfMoreSet" style="display:none;">

					<div class="form-group">
						<label class="control-label"> 投票隐私: </label>
						<div class="controls" style="padding-top:5px">
							<input type="radio" name="privateVote" value="0" checked>
							任何人可查看和投票
						</div>
					</div>

					<div class="form-group">
						<label class="control-label"> </label>
						<div class="controls" style="padding-top:5px">
							<input type="radio" name="privateVote" value="1">
							凭密码查看和投票
						</div>
					</div>

					<div class="form-group">
						<label class="control-label" style="margin-left:-50px;">
							投票后才能查看结果?: </label>
						<div class="controls" style="padding-top:5px">
							<input type="radio" name="seeAfterVote" value="0" checked>
							否&nbsp; <span style="color:#999999"> 不投票即可看到当前的投票选项分布情况 </span>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label"> </label>
						<div class="controls" style="padding-top:5px">
							<input type="radio" name="seeAfterVote" value="1">
							是&nbsp; <span style="color:#999999;"> 只有在提交投票后才可看到投票选项分布情况
							</span>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label" style="margin-left:0px;">
							投票截止时间: </label>
						<div class="controls">
							<input id="deadLine" type="date" id="birthday"
								class="form-control" style="width:300px;margin-left:15px;"
								value="" />
						</div>
					</div>
				</div>
			</div>




			<button type="button" class=" btn btn-default" id="addActivity"
				onclick="addAct();">添加活动介绍</button>
			<div id="Activity" style="display:none;">
				<legend>活动介绍</legend>
				<div>
					<textarea id="voteDesc" name="voteDesc" value=""
						class="form-control" style="width:700px" rows="6"></textarea>
				</div>
			</div>


			<br>
			<br>

			<legend style="margin-bottom: 0px;">投票选项</legend>

			<div class="vote-question" id="titleList">

        <div name="title">
           <div class="form-inline" name="titleContent">
              <div class="form-group" style="margin-left:0px;margin-top:20px;">
                <label class="sr-only" for="exampleInputAmount">请输入投票标题</label>
                <div class="input-group">
                  <div class="input-group-addon" name="titlePosition">1</div>
                  <input type="text" name="titleContents" value=""class="form-control" style="width:500px;"id="exampleInputAmount" placeholder="请输入投票标题"> 
                  <div class="input-group-addon">
                    <input type="radio" name="titleType" value="0" checked>单项选项
                     <input type="radio" name="titleType" value="1" >多项选项
                  </div>
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-plus" onclick="addTitle(this);"></span>
                  </div>
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-arrow-up" onclick="moveUpTitle(this)"></span>
                  </div>
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-arrow-down" onclick="moveDownTitle(this)"></span>
                  </div>
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-remove" onclick="removeTitle(this)"></span>
                  </div>
                </div>
              </div>
            </div>

          <div name="optionList" style="margin-left:35px">

            <div class="form-inline" name="option">
              <div class="form-group" style="margin-left:0px;margin-top:20px;">
                <label class="sr-only" for="exampleInputAmount">请输入投票选项</label>
                <div class="input-group">
                  <div class="input-group-addon" name="optionPosition">1</div>
                  <input type="text" name="optionContent" value=""
                    class="form-control" style="width:575px;"
                    id="exampleInputAmount" placeholder="请输入投票选项">
                  <div class="input-group-addon" name="imageId" value="" flag="0">
                    <button type="button" data-toggle="modal"
                      data-target="#addPhoto"
                      onclick="uploadMyImage(this.parentNode)">
                      <span class="glyphicon glyphicon-picture"></span>
                    </button>
                  </div>
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-plus"
                      onclick="addOption(this);"></span>
                  </div>
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-arrow-up"
                      onclick="moveUpOption(this);"></span>
                  </div>
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-arrow-down"
                      onclick="moveDownOption(this)"></span>
                  </div>
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-remove"
                      onclick="removeOption(this);"></span>
                  </div>
                </div>
              </div>
            </div>

            <div class="form-inline" name="otherOption">
              <div class="form-group" style="margin-left:0px;margin-top:20px;">
                <label class="sr-only" for="exampleInputAmount">其他</label>
                <div class="input-group">
                  <div class="input-group-addon">其他</div>
                  <input name="otherOptionContent" value="" type="text"
                    class="form-control" style="width:640px;"
                    id="exampleInputAmount"
                    placeholder="其他选项用于当没有用户满意的选项时，用户可以输入自己的内容">
                  <div class="input-group-addon">
                    <input name="otherOption" type="checkbox" value="0"
                      onclick="change2One(this);">&nbsp;&nbsp;启用其他选项
                  </div>
                </div>
              </div>
            </div>
            
          </div>
        </div>

      </div>
			<br>
			<button type="button" class=" btn btn-default"
				id="seeResultAfterVote" onclick="addResultAfterVote();">
				投票后显示的内容</button>
			<div id="ResultAfterVote" style="display:none;">
				<legend>投票后显示的内容</legend>
				<div>
					<textarea id="displayAfterVote" name="displayAfterVote" value=""
						class="form-control" style="width:700px" rows="6"></textarea>
				</div>
			</div>
			<br> <br>
			<button id="addCollect" name="addCollect" value="0" type="button"
				class=" btn btn-default" onclick="change2One(this);">
				增加用户信息收集功能表单</button>
			<br> <br>
			<div>
				<input id="agreeTerm" name="agreeTerm" value="0" type="checkbox"
					onclick="change2One(this);">&nbsp; 我同意投票服务条款 &nbsp;<a
					href="#">查看</a>
			</div>

			<br> <br>

			<button id="createHtml" type="" class=" btn btn-primary" onclick="submitFormData();">
				创建投票</button>

		</div>
	</div>
	<div id="addPhoto" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">添加图片</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal">
						<div class="form-group">
							<div class="row">
								<div class="col-xs-6 col-md-3 thumbnail"
									style="margin-left:25%;width:181px;text-align:center;border:1px solid #ddd;">
									<input type="hidden" id="hfThumbnail"
										value="\test\userImages\cc\20160216084116.jpeg" />
									<div class="imgdiv"></div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<input type="file" name="myImage" style="margin-left:23%;">
						</div>
						<button type="button" value="上传" class="upload" style="margin-left:23%;">上传</button>
					</form>

				</div>
				<div class="modal-footer">
					<div class="text-center">
						<button type="button" class="btn btn-default" data-dismiss="modal" >确认</button>
						<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>