$(function(){
    setMainConTop();
    $('.tree-bar-switch').on('click',function(e){
        var $this = $(e.currentTarget);
        if($this.find('i').hasClass('tree-open')){
            $('.menu-tree-container').animate({'width':'20px'},300);
            $('.tree-box').addClass('hidden');            
	    	$('#outerContainer').css('padding-left','21px');
	    	showLine();
            $this.find("i").removeClass('tree-open fa-angle-left').addClass('fa-angle-right');
            $(this).addClass('tree-close');
        }else{
            $('.menu-tree-container').animate({'width':'260px'},300);
            $('#outerContainer').css('padding-left','261px');
            showLine();
            $('.tree-box').removeClass('hidden');
            $this.find("i").removeClass('fa-angle-right').addClass('tree-open fa-angle-left');
            $this.removeClass('tree-close');
        }
    })
})

function setMainConTop(){
    var headHeight = parseInt($(".main-header").height());
    $('.menu-tree-container,.main-table-container').css('top',headHeight+"px");
}

//构建树
var setting = {
    view: {
      showIcon: false
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    edit: {
        enable: true
    },
    callback: {
        beforeClick: beforeClick
    }
};

var zNodes =[
    { id:1, pId:0, name:"父节点1", open:true},
    { id:11, pId:1, name:"父节点11",childern:[{'cc':12}]},
    { id:111, pId:11, name:"叶子节点111"},
    { id:112, pId:11, name:"叶子节点112"},
    { id:113, pId:11, name:"叶子节点113"},
    { id:114, pId:11, name:"叶子节点114"},
    { id:12, pId:1, name:"父节点12"},
    { id:121, pId:12, name:"父节点121"},
    { id:1211, pId:121, name:"叶子节点1211"},
    { id:1212, pId:122, name:"叶子节点1212"},
    { id:122, pId:12, name:"叶子节点122"},
    { id:123, pId:12, name:"叶子节点123"},
    { id:124, pId:12, name:"父节点124"},
    { id:1241, pId:124, name:"父节点1241"},
    { id:12411, pId:1241, name:"叶子节点12411"},
    { id:13, pId:1, name:"父节点13", isParent:true},
    { id:2, pId:0, name:"父节点2"},
    { id:21, pId:2, name:"父节点21", open:true},
    { id:211, pId:21, name:"叶子节点211"},
    { id:212, pId:21, name:"叶子节点212"},
    { id:213, pId:21, name:"叶子节点213"},
    { id:214, pId:21, name:"叶子节点214"},
    { id:22, pId:2, name:"父节点22"},
    { id:221, pId:22, name:"叶子节点221"},
    { id:222, pId:22, name:"叶子节点222"},
    { id:223, pId:22, name:"叶子节点223"},
    { id:224, pId:22, name:"叶子节点224"},
    { id:23, pId:2, name:"父节点23"},
    { id:231, pId:23, name:"叶子节点231"},
    { id:232, pId:23, name:"叶子节点232"},
    { id:233, pId:23, name:"叶子节点233"},
    { id:234, pId:23, name:"叶子节点234"},
    { id:3, pId:0, name:"父节点3", isParent:true}
];