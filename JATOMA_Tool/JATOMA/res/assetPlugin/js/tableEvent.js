mapTapHammer = {};
mapPressHammer = {};

var tables = document.getElementsByTagName("table");
for(var i=0; i<tables.length; i++){
    var tds = tables[i].getElementsByTagName("td");
    for(var j=0; j<tds.length; j++){
        var idElm = tds[j].id;
        var valueElm = tds[j].innerHTML;
            
        addFireCellListener(tds[j], idElm, valueElm);
    }
    
    trs = tables[i].getElementsByTagName("tr");
    for(k=1; k<trs.length; k++){
        addFireRowListener(trs[k], trs[k].id);
    }
}
	
    function addFireRowListener(obj, id){
		mapTapHammer[id] = new Hammer(obj);
		mapTapHammer[id].on('tap', function(ev) {
            app.fireRow(id);
        });  
    }
	
    function addFireCellListener(obj, id, value){
        mapPressHammer[id] = new Hammer(obj);
        mapPressHammer[id].on('press', function(ev) {
            app.fireCell(id, value);
        });
    }
	    
    function addChangeCellListener(obj, id){
        var contenteditable = document.querySelector('[contenteditable]');
            obj.addEventListener('keyup',function(e){
            app.insertTable(id, contenteditable.textContent);
        },false);
    }
    
    function removeTableListener(obj, event, functionName){
        obj.removeEventListener(event, functionName);
    }

