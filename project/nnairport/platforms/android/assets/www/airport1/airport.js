$.ui.useOSThemes = false;
$.ui.disableTabBar();

$(function() {
    // ���࿨Ƭ,ֻ��һ��ʱ��ȥ��border
    var $categoryCard = $('.category_card');
    console.log($categoryCard.find('tr').length);
    $categoryCard.each(function() {
        if($(this).find('tr').length == 1) {
            $(this).find('td').css('border', 'none');
        }
    });

    // ѡ�����
    $('#btnCatering').click(function() {
        $(this).addClass('pressed');
        $('#btnHotel').removeClass('pressed');
        $('#cateringList').show();
        $('#hotelList').hide();
    });
    // ѡ��Ƶ�
    $('#btnHotel').click(function() {
//        $(this).addClass('pressed');
        $('#btnCatering').removeClass('pressed');
        $(this).toggleClass('pressed');
        $('#cateringList').hide();
        $('#hotelList').show();
    });
});