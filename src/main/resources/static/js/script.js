(function ($) {
	
	
  'use strict';
  var nbreJourAnterieur;
  //  Count Up
  function counter() {
    var oTop;
    if ($('.counter').length !== 0) {
      oTop = $('.counter').offset().top - window.innerHeight;
    }
    if ($(window).scrollTop() > oTop) {
      $('.counter').each(function () {
        var $this = $(this),
          countTo = $this.attr('data-count');
        $({
          countNum: $this.text()
        }).animate({
          countNum: countTo
        }, {
          duration: 1000,
          easing: 'swing',
          step: function () {
            $this.text(Math.floor(this.countNum));
          },
          complete: function () {
            $this.text(this.countNum);
          }
        });
      });
    }
  }
  $(window).on('scroll', function () {
    counter();
  });

  // bottom to top
  $('#top').click(function () {
    $('html, body').animate({
      scrollTop: 0
    }, 'slow');
    return false;
  });
  // bottom to top

  $(document).on('ready', function () {

    // Nice Select
    $('select').niceSelect();
    // -----------------------------
    //  Client Slider
    // -----------------------------
    $('.category-slider').slick({
      slidesToShow: 8,
      infinite: true,
      arrows: false,
      autoplay: false,
      autoplaySpeed: 2000
    });
    // -----------------------------
    //  Select Box
    // -----------------------------
    // $('.select-box').selectbox();
    // -----------------------------
    //  Video Replace
    // -----------------------------
    $('.video-box img').click(function () {
      var video = '<iframe allowfullscreen src="' + $(this).attr('data-video') + '"></iframe>';
      $(this).replaceWith(video);
    });
    // -----------------------------
    //  Coupon type Active switch
    // -----------------------------
    $('.coupon-types li').click(function () {
      $('.coupon-types li').not(this).removeClass('active');
      $(this).addClass('active');
    });
    // -----------------------------
    // Datepicker Init
    // -----------------------------
    $('.input-group.date').datepicker({
      format: 'dd/mm/yy'
    });
    // -----------------------------
    // Datepicker Init
    // -----------------------------

    // -----------------------------
    // Button Active Toggle
    // -----------------------------
    $('.btn-group > .btn').click(function () {
      $(this).find('i').toggleClass('btn-active');
    });
    // -----------------------------
    // Coupon Type Select
    // -----------------------------
    $('#online-code').click(function () {
      $('.code-input').fadeIn(500);
    });
    $('#store-coupon, #online-sale').click(function () {
      $('.code-input').fadeOut(500);
    });
    /***ON-LOAD***/
    jQuery(window).on('load', function () {

    });

  });

  // niceSelect

  $('select:not(.ignore)').niceSelect();

  // blog post-slider
  $('.post-slider').slick({
    dots: false,
    arrows: false,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    fade: true
  });

  // Client Slider 
  $('.category-slider').slick({
    dots: false,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 1500,
    nextArrow: '<i class="fa fa-chevron-right arrow-right"></i>',
    prevArrow: '<i class="fa fa-chevron-left arrow-left"></i>',
    responsive: [{
        breakpoint: 1024,
        settings: {
          slidesToShow: 4,
          slidesToScroll: 1,
          infinite: true,
          dots: false
        }
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1,
          arrows:false
        }
      }
      // You can unslick at a given breakpoint now by adding:
      // settings: "unslick"
      // instead of a settings object
    ]
  });

  $(".show_more").click(function(e){
     
      $(".show_less").fadeIn();
      $(".category").fadeIn(); 
       $(".show_more").hide();
  });

  $(".show_less").click(function(e){
      
      $(".show_more").fadeIn();
      $(".category").hide(); 
      $(".show_less").hide();
  });

  // trending-ads-slide 

  $('.trending-ads-slide').slick({
    dots: false,
    arrows: false,
    slidesToShow: 3,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 800,
    responsive: [{
        breakpoint: 1024,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
          infinite: true,
          dots: false
        }
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1
        }
      }
      // You can unslick at a given breakpoint now by adding:
      // settings: "unslick"
      // instead of a settings object
    ]
  });


  // product-slider
  $('.product-slider').slick({
    dots: true,
    arrows: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: false,
    autoplaySpeed: false,
    nextArrow: '<i class="fa fa-chevron-right arrow-right"></i>',
    prevArrow: '<i class="fa fa-chevron-left arrow-left"></i>',
    
  });

  

  // tooltip
  $(function () {
    $('[data-toggle="tooltip"]').tooltip();
  });

     // bootstrap slider range
  $('.range-track').slider({});
  $('.range-track').on('slide', function (slideEvt) {
    $('.value').text('$' + slideEvt.value[0] + ' - ' + '$' + slideEvt.value[1]);
  });



  $('.form_date').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        startDate: new Date(),
        minView: 2,
        forceParse: 0,
        format:'yyyy-mm-dd'
        });


    $("#Specify").click(function(e){
      $("#incorrect_day").hide();
      var date2 = new Date(document.getElementById("to").value);
      var date1 = new Date(document.getElementById("from").value);
      var nbreJour=(date2.getTime()-date1.getTime())/86400000;
      nbreJourAnterieur=nbreJour;
      if(nbreJour>=0){
            
            for (var i = 0; i <= nbreJour; i++) {
              $("#SpecifyForm").append('<div class="rounded sessions"  style="border: 1px solid #C3C3C3;padding: 10px;margin-top: 10px;"><i class="fa fa-clock-o " style="padding:5px;"></i><span class="font-weight-bold pt-4 pb-1" >Timing of Day '+(i+1)+':</span><div class="row" style="margin-top:8px;"><div class="col-sm-6 px-1" style="margin-top: 10px;"><span class="font-weight-bold pt-4 pb-1" style="font-size: 15px;margin-left: 10px;">From:</span><input type="time" name="Specifyfrom'+(i+1)+'" style="height: 48px;width: 74%;float: right;margin-right: 10px;border:1px solid #CED4DA;"></div><br/><div class="col-sm-6 px-1" style="margin-top: 10px;"><span class="font-weight-bold pt-4 pb-1" style="font-size: 15px;margin-left: 20px;">to:</span><input type="time" name="Specifyto'+(i+1)+'" style="height: 48px;width: 74%;float: right;margin-right: 10px;border:1px solid #CED4DA; "></div></div>')};
            $("#Specify").hide();
      }

      else{
         $("#incorrect_day").hide();
        $("#msgInvalid").append('<p id="incorrect_day" style="color:red;font-weight:bold;">Check the days you choosed</p>')
      }
      

    });

    document.getElementsByClassName("carousel-item")[0].className="carousel-item active";
 
    
    
})(jQuery);