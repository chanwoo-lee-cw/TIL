 (function($){
    "use strict";
    $(function(){
        var $shrinkHeaderHeight = 300;
        var $fixedHeader = $('#header');
        var $gnb = $('.gnb');
        var $hoverEl = $('.hover');
        var $searchEl = $('.click > a');
        var $searchForm = $('.search_area');

        // Gnb
        if($(document).width() > 480){
	        $gnb.addClass('pc-gnb');
        }
        
        var $pc_gnb = $('.pc-gnb');
        $pc_gnb.find('>ul>li>a')
        .mouseover(function(){
            $gnb.find('>ul>li>ul:visible').hide().parent('li').removeClass('on');
            $(this).next('ul:hidden').stop().fadeIn(200).parent('li').addClass('on')
        })
        .focus(function(){
            $(this).mouseover();
        })
        .end()
        .mouseleave(function(){
            $gnb.find('>ul>li>ul').hide().parent().removeClass('on')
        });

		$pc_gnb.find('>ul>li>ul>li>a')
        .mouseover(function(){
            $gnb.find('>ul>li>ul>li>ul:visible').hide().parent('li').removeClass('on');
            $(this).next('ul:hidden').stop().fadeIn(200).parent('li').addClass('on')
        })
        .focus(function(){
            $(this).mouseover();
        })
        .end()
        .mouseleave(function(){
            $gnb.find('>ul>li>ul>li>ul').hide().parent().removeClass('on')
        });
        
		$("#mobile_menu_btn").on('click', function(){
			var isOpened = $(this);
			if(isOpened.hasClass('opened')){
				$("#gnb").find(">ul").slideUp(200);
			}else{
				$("#gnb").find(">ul:not(:animated)").slideDown(200);
			}
			isOpened.toggleClass('opened');
		});
        // login popup
        $hoverEl.on('mouseenter mouseleave focusin focusout',function(e){
            e.preventDefault();
            if(e.type == 'mouseenter' || e.type == 'focusin'){
                $(this).addClass('on');
            } else {
                $(this).removeClass('on');
            }
        });

        // Login help, warning
        $("#warning").on('touchend', function(e){
	        $(this).css('display', 'none');
        });

        // Sub Header Parallax
        $('.sub_type3 .bg_img').parallax('50%',0.4);
    })
})(jQuery);
(function($) {
    "use strict";
    var $window = $(window);
    var windowHeight = $window.height();

    $window.resize(function() {
        windowHeight = $window.height()
    });

    $.fn.parallax = function(xpos, speedFactor, outerHeight) {
        var $this = $(this);
        var getHeight;
        var firstTop;
        $this.each(function() {
            firstTop = $this.offset().top;
        });

        if (outerHeight) {
          getHeight = function(object) {
            return object.outerHeight(true)
          }
        } else {
          getHeight = function(object) {
            return object.height()
          }
        }
        if (arguments.length < 1 || xpos === null)
          xpos = "50%";
        if (arguments.length < 2 || speedFactor === null)
          speedFactor = 0.1;
        if (arguments.length < 3 || outerHeight === null)
          outerHeight = true;
        function update() {
          var pos = $window.scrollTop();
          $this.each(function() {
            var $element = $(this);
            var top = $element.offset().top;
            var height = getHeight($element);

            if (top + height < pos || top > pos + windowHeight) {
              return
            }
            $this.css('backgroundPosition', xpos + " " + Math.round((firstTop - pos) * speedFactor) + "px")
          })
        }
        $window.bind('scroll', update).resize(update);
        update()
  }
})(jQuery);