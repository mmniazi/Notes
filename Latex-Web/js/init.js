/*
 Big Picture by HTML5 UP
 html5up.net | @n33co
 Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
 */

(function ($) {

    skel.init({
        reset: 'full',
        breakpoints: {
            'max': {
                range: '*',
                href: 'css/style.css',
                containers: 1440,
                viewport: {scalable: false},
                grid: {gutters: 40}
            },
            'wide': {range: '-1920', href: 'css/style-wide.css', containers: 1360},
            'normal': {range: '-1680', href: 'css/style-normal.css', containers: 1200},
            'narrow': {range: '-1280', href: 'css/style-narrow.css', containers: 960},
            'narrower': {range: '-1000', href: 'css/style-narrower.css', containers: '95%'},
            'mobile': {range: '-736', href: 'css/style-mobile.css', grid: {gutters: 20}},
            'mobile-narrow': {range: '-480', containers: '95%!', grid: {collapse: true}}
        }
    });

    $(function () {

        var $window = $(window),
            $body = $('body'),
            $header = $('#header'),
            $all = $body.add($header);

        // Disable animations/transitions until the page has loaded.
        $body.addClass('is-loading');

        $window.on('load', function () {
            window.setTimeout(function () {
                $body.removeClass('is-loading');
            }, 0);
        });

        // Touch mode.
        skel.change(function () {

            if (skel.vars.isMobile || skel.isActive('mobile'))
                $body.addClass('is-touch');
            else
                $body.removeClass('is-touch');

        });

        // CSS polyfills (IE<9).
        if (skel.vars.IEVersion < 9)
            $(':last-child').addClass('last-child');

        // Section transitions.
        if (!skel.vars.isMobile
            && skel.canUseProperty('transition')) {

            var on = function () {

                // Generic sections.
                $('.main.style1')
                    .scrollex({
                        mode: 'middle',
                        delay: 100,
                        initialize: function () {
                            $(this).addClass('inactive');
                        },
                        terminate: function () {
                            $(this).removeClass('inactive');
                        },
                        enter: function () {
                            $(this).removeClass('inactive');
                        },
                        leave: function () {
                            $(this).addClass('inactive');
                        }
                    });

                $('.main.style2')
                    .scrollex({
                        mode: 'middle',
                        delay: 100,
                        initialize: function () {
                            $(this).addClass('inactive');
                        },
                        terminate: function () {
                            $(this).removeClass('inactive');
                        },
                        enter: function () {
                            $(this).removeClass('inactive');
                        },
                        leave: function () {
                            $(this).addClass('inactive');
                        }
                    });

                // further.
                $('#further')
                    .scrollex({
                        top: '40vh',
                        bottom: '30vh',
                        delay: 50,
                        initialize: function () {

                            var t = $(this);

                            t.find('.row.images')
                                .addClass('inactive');

                        },
                        terminate: function () {

                            var t = $(this);

                            t.find('.row.images')
                                .removeClass('inactive');

                        },
                        enter: function () {

                            var t = $(this),
                                rows = t.find('.row.images'),
                                length = rows.length,
                                n = 0;

                            rows.each(function () {
                                var row = $(this);
                                window.setTimeout(function () {
                                    row.removeClass('inactive');
                                }, 100 * (length - n++));
                            });

                        },
                        leave: function (t) {

                            var t = $(this),
                                rows = t.find('.row.images'),
                                length = rows.length,
                                n = 0;

                            rows.each(function () {
                                var row = $(this);
                                window.setTimeout(function () {
                                    row.addClass('inactive');
                                }, 100 * (length - n++));
                            });

                        }
                    });

                // reference.
                $('#reference')
                    .scrollex({
                        top: '50%',
                        delay: 50,
                        initialize: function () {
                            $(this).addClass('inactive');
                        },
                        terminate: function () {
                            $(this).removeClass('inactive');
                        },
                        enter: function () {
                            $(this).removeClass('inactive');
                        },
                        leave: function () {
                            $(this).addClass('inactive');
                        }
                    });

            };

            var off = function () {

                // Generic sections.
                $('.main.style1')
                    .unscrollex();

                $('.main.style2')
                    .unscrollex();

                // further.
                $('#further')
                    .unscrollex();

                // reference.
                $('#reference')
                    .unscrollex();

            };

            skel.change(function () {

                if (skel.isActive('mobile'))
                    (off)();
                else
                    (on)();

            });

        }

        // Events.
        var resizeTimeout, resizeScrollTimeout;

        $window
            .resize(function () {

                // Disable animations/transitions.
                $body.addClass('is-resizing');

                window.clearTimeout(resizeTimeout);

                resizeTimeout = window.setTimeout(function () {

                    // Update scrolly links.
                    $('a[href^=#]').scrolly({
                        speed: 1500,
                        offset: $header.outerHeight() - 1
                    });

                    // Re-enable animations/transitions.
                    window.setTimeout(function () {
                        $body.removeClass('is-resizing');
                        $window.trigger('scroll');
                    }, 0);

                }, 100);

            })
            .load(function () {
                $window.trigger('resize');
            });

    });

})(jQuery);