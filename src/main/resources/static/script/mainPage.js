$(document).ready(function () {
    screenCheck();

    $('.scroll-control .one').click(function () {
        $.scrollify.move('#s-one');
    });
    $('.scroll-control .two').click(function () {
        $.scrollify.move('#s-two');

    });
    $('.scroll-control .three').click(function () {
        $.scrollify.move('#s-three');
    });
});

$(window).on('resize', function () {
    screenCheck();
});

function applyScroll() {
    $.scrollify({
        section: '.scroll',
        sectionName: 'section-name',
        easing: 'easeOutExpo',
        scrollSpeed: 900,
        offset: 0,
        scrollbars: true,
        setHeights: true,
        overflowScroll: true,
        updateHash: false,
        touchScroll: true,
        before: function(index, sections) {
            sections[index].addClass('fade-in');
            if (index > 0 && index < sections.length-1) {
                sections[index-1].removeClass('fade-in');
                sections[index+1].removeClass('fade-in');
            }
            else if (index == sections.length - 1) {
                sections[index-1].removeClass('fade-in');
            }
            else if (index === 0) {
                sections[index+1].removeClass('fade-in');
            }

            var currentSection = sections[index].attr("id");
            if (currentSection !== "s-two") {
                $(".header").css("color", "black");
                $(".header .navbar a").css("color", "black"); 
                $(".header .icons").css("color", "black"); 
                $(".header a").css("color", "black");
                $(".scroll-control span").css("background-color", "black");
                if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
                    $(".header").css("color", "white"); 
                    $(".header .navbar a").css("color", "white"); 
                    $(".header a").css("color", "white"); 
                    $(".header .icons").css("color", "white"); 
                    $(".scroll-control span").css("background-color", "white"); 
                }
            } else {
                $(".header").css("color", "white"); 
                $(".header .navbar a").css("color", "white"); 
                $(".header a").css("color", "white"); 
                $(".header .icons").css("color", "white"); 
                $(".scroll-control span").css("background-color", "white"); 
            }
        },
    });
}

function screenCheck() {
    var deviceAgent = navigator.userAgent.toLowerCase();
    var agentID = deviceAgent.match(/(iphone|ipod|ipad)/);
    if (agentID || $(window).width() <= 1024) {
        // its mobile screen
        $.scrollify.destroy();
        $('section').removeClass('scroll').removeAttr('style');
        $.scrollify.disable();
    } else {
        // its desktop
        $('section').addClass('scroll');
        applyScroll();
        $.scrollify.enable();
    }
}