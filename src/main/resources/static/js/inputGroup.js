/**
 * Created by DreamBoy on 2016/4/29.
 */
$(function() {
    $.fn.initInputGroup = function (options) {
        //1.Settings 初始化设置
        var c = $.extend({
            'widget' : 'input',
            'add' : "<span class=\"glyphicon glyphicon-plus\"></span>",
            'del' : "<span class=\"glyphicon glyphicon-minus\"></span>",
            'category':1
        }, options);

        var _this = $(this);
        var sort_puzzle="";
        var sort_question=""

        //添加序号为1的输入框组
        addInputGroup(1);
//http://dx.sc.chinaz.com/Files/DownLoad/sound1/201607/7499.mp3
//window.location.href="http://zjdx1.sc.chinaz.com/Files/DownLoad/sound1/201610/7920.mp3"
//window.open('http://zjdx1.sc.chinaz.com/Files/DownLoad/sound1/201610/7920.mp3');
//document.getElementById('query_ticket').click();


        /**
         * 添加序号为order的输入框组
         * @param order 输入框组的序号
         */
        function addInputGroup(order) {
            //1.创建输入框组
            var inputGroup = $("<div class='input-group' style='margin: 10px 0'></div>");
            //2.输入框组的序号
            var inputGroupAddon1 = $("<span class='input-group-addon'></span>");
            //3.设置输入框组的序号
            inputGroupAddon1.html(" " + order + " ");

            //4.创建输入框组中的输入控件（input或textarea）
            var widget = '', inputGroupAddon2;
            if(c.widget == 'textarea') {
                widget = $("<textarea class='form-control' style='resize: vertical;'></textarea>");
                inputGroupAddon2 = $("<span class='input-group-addon'></span>");
            } else if(c.widget == 'input') {
                widget = $("<input class='form-control' type='text'/>");
                inputGroupAddon2 = $("<span class='input-group-btn'></span>");
            }else if(c.widget == 'div') {
            if(c.category==1){
                widget=sort_puzzle;
            }else{
                widget=sort_question;
            }
//                widget = $("<div class='form-group'><div class='input-group'><div class='input-group-addon'>@</div><input class='form-control' type='email' placeholder='Enter email'></div></div>");
                inputGroupAddon2 = $("<span class='input-group-btn'></span>");
            }


            //5.创建输入框组中最后面的操作按钮
            var addBtn = $("<button class='btn btn-default' type='button'>" + c.add + "</button>");
            addBtn.appendTo(inputGroupAddon2).on('click', function() {
                //6.响应删除和添加操作按钮事件
                if($(this).html() == c.del) {
                    $(this).parents('.input-group').remove();
                } else if($(this).html() == c.add) {
                    if(order<=6){
                    $(this).html(c.del);
                    addInputGroup(order+1);
                    }
                }
                //7.重新排序输入框组的序号
                resort();
            });

            inputGroup.append(inputGroupAddon1).append(widget).append(inputGroupAddon2);

            _this.append(inputGroup);
        }

        function resort() {
            var child = _this.children();
            $.each(child, function(i) {
                $(this).find(".input-group-addon").eq(0).html(' ' + (i + 1) + ' ');
            });
        }
    }
});