$('#toggleMaxDistance').click(function(){
  var thisCheck = $(this);

  if (thisCheck.is(':checked')) {
    $('#maxDistanceContainer').show();
  } else {
    $('#maxDistanceContainer').hide();
  }
});
