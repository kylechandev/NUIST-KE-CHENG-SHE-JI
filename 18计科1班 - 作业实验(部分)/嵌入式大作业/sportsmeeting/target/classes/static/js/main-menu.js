function initMenu() {
    $('#menu ul').hide();  /*隐藏二级ul*/   /*一级ul的id是menu*/
    $('#menu li a').click( /*设置一级ul内部li a的点击功能*/
        function() {
            var checkElement = $(this).next();   //获取二级ul元素
            if((checkElement.is('ul')) && (checkElement.is(':visible'))) { /*是二级ul，可见已展开*/
                $('#menu ul:visible').slideUp('fast');/*选择可见的二级ul隐藏 slideUp通过调整高度来滑动隐藏被选元素。毫秒 （比如 1500）"slow""normal""fast"*/
                return false;
            }
            if((checkElement.is('ul')) && (checkElement.is(':hidden'))) {/*二级ul，不可见未展开*/
                $("#menu ul li a").css('color','');
                $('#menu ul:visible').slideUp('fast');  /*选择可见的二级ul隐藏*/
                checkElement.slideDown('fast');         /*选择可见的二级ul显示  slideDown通过调整高度来滑动显示被选元素*/+

                    $("#menu ul li a").removeClass("a-color"); // 删除点击后a颜色样式

                $("#menu .parent-a").find('.i-icon').removeClass("fa-angle-double-down a-color"); // 删除下箭头图标
                $("#menu .parent-a").find('.i-icon').addClass("fa-angle-double-right"); // 添加右箭头图标

                $("#menu .parent-a").parent().removeClass("border-left-color");
                return false;
            }
        }
    );
    $('#menu ul li a').click(function () { /*选择二级菜单下的a标签  添加点击后颜色*/
        $("#menu ul li a").css('color','');
        $("#menu ul li a").removeClass("a-color"); // 删除样式
        $(this).addClass("a-color");
        $(this).css('color','white')
    })
    $('#menu .parent-a').click(function () { /*选择一级菜单下的a标签  添加点击后颜色*/
        $(this).find('.i-icon').toggleClass("fa-angle-double-right"); // 切换图标
        $(this).find('.i-icon').toggleClass("fa-angle-double-down"); // 切换图标
        $(this).parent().toggleClass("border-left-color");
    })
}
$(document).ready(function() {initMenu();});