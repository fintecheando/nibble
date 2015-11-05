function cloneOption(option) {
  var out = new Option(option.text,option.value);
  out.selected = option.selected;
  out.defaultSelected = option.defaultSelected;
  return out;
}
function moveSelected(from,to) {
  newTo = new Array();
  for(i=0; i<from.options.length; i++) {
    if (from.options[i].selected) {
      newTo[newTo.length] = cloneOption(from.options[i]);
      from.options[i] = null;
      i--;
    }
  }

  for(i=0; i<to.options.length; i++) {
    newTo[newTo.length] = cloneOption(to.options[i]);

    newTo[newTo.length-1].selected = false;
  }

  to.options.length = 0;

  for(i=0; i<newTo.length; i++) {
    to.options[to.options.length] = newTo[i];
  }
}


function updateHiddenChooserField(chosen,hidden) {
  hidden.value='';
  var opts = chosen.options;
  for(var i=0; i<opts.length; i++) {
    hidden.value = hidden.value + opts[i].value+",";
    if (i<opts.length-1) hidden.value = hidden.value + "\n";
  }
}

