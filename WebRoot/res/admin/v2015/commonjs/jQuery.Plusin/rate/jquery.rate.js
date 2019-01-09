/*
 *	jquery.rate 1.0 - 2009-09-29
 *
 *	All the stuff written by pwwang (pwwang.com)	
 *	Feel free to do whatever you want with this file
 *
 */
(function ($) {
    $.rate = function (wraper, options) {
        var $wraper = $(wraper);
        var $rateUnits = [];
        for (var i = 0; i < options.rateMax; i++) {
            $rateUnits[i] = $(document.createElement("a")).attr("href", "javascript:;").addClass(options.rateClass);
            $rateUnits[i].attr("data-index", i + 1);
            if (i < options.rated) $rateUnits[i].addClass(options.ratedClass);
            $wraper.append($rateUnits[i]);
        }
        $.each($rateUnits, function () {
            $(this).hover(
				function () {
				    $(this).prevAll().add($(this)).addClass(options.rateOverClass);

				    var index = parseInt($(this).attr("data-index"));
				    if ($.isArray(options.starMsg) && options.starMsg.length >= index)
				        $("body").append("<p id='star-msg'>" + options.starMsg[index - 1] + "</p>");
				},
				function () {
				    $(this).prevAll().add($(this)).removeClass(options.rateOverClass);
				    $(options.starMsg_id).remove();
				}
			);
            $(this).mousemove(function (e) {
                $(options.starMsg_id).css("top", (e.pageY - options.starMsg_xOffset) + "px").css("left", (e.pageX + options.starMsg_yOffset) + "px").fadeIn("fast");
            });
            $(this).click(function () {
                //var index = $wraper.find("a").index($(this));
                var index = parseInt($(this).attr("data-index"));

                if (options.ratePage && options.ratePage != "") {
                    $.get(options.ratePage, { rate: index }, function (data) {
                        (options.rateAfterEvent)(data);
                    });
                } else {
                    (options.rateAfterEvent)(index);
                }
                $(this).prevAll().add($(this)).addClass(options.ratedClass);
                $(this).nextAll().removeClass(options.ratedClass);
            });
        });
    }
    $.fn.rate = function (options) {
        options = options || {};
        options.rated = options.rated || 0;
        options.rateMax = options.rateMax || 5;
        options.rateClass = options.rateClass || "star";
        options.rateOverClass = options.rateOverClass || "star_on";
        options.ratedClass = options.ratedClass || "star_yes";
        options.rateAfterEvent = options.rateAfterEvent || function () { };
        options.ratePage = options.ratePage || "";
        options.starMsg = options.starMsg || [];
        options.starMsg_id = options.starMsg_id || "#star-msg";
        options.starMsg_xOffset = options.starMsg_xOffset || 10;
        options.starMsg_yOffset = options.starMsg_yOffset || 30;
        $.rate(this, options);
        return this;
    };
})(jQuery);