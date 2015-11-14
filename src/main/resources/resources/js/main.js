$('#toggleMaxDistance').click(function(){
  var thisCheck = $(this);

  if (thisCheck.is(':checked')) {
    $('#maxDistanceContainer').show();
  } else {
    $('#maxDistanceContainer').hide();
  }
});

$('#toggleTerrainType').click(function(){
  var thisCheck = $(this);

  if (thisCheck.is(':checked')) {
    $('#terrainTypeContainer').show();
  } else {
    $('#terrainTypeContainer').hide();
  }
});

$('#toggleParkSize').click(function(){
  var thisCheck = $(this);

  if (thisCheck.is(':checked')) {
    $('#parkSizeContainer').show();
  } else {
    $('#parkSizeContainer').hide();
  }
});