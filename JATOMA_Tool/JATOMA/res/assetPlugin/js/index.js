var awtPlugin = {

load: function(successCallback, errorCallback) {
		//var nome= document.getElementsByTagName("div")[0].id;
	    cordova.exec(
	        successCallback,
	        errorCallback,
	        'AWTPlugin',
	        'load',
	        [{

	        }]
	    );
	 },
		fire: function(id,successCallback, errorCallback) {
		//var nome= document.getElementsByTagName("div")[0].id;
	    cordova.exec(
	        successCallback,
	        errorCallback,
	        'AWTPlugin',
	        'fire',
	        [{
	        	"id": id
	        }]
	    );
	 },

	 firee: function(id,a,successCallback, errorCallback) {
	 //var nome= document.getElementsByTagName("div")[0].id;
         	    cordova.exec(
         	        successCallback,
         	        errorCallback,
         	        'AWTPlugin',
         	        'firee',
         	        [{
         	         "id": id,
         	         "element": a

         	        }]
         	    );
         	 },

	insert: function(id,a,successCallback, errorCallback) {
	//var nome= document.getElementsByTagName("div")[0].id;
	    cordova.exec(
	        successCallback,
	        errorCallback,
	        'AWTPlugin',
	        'insert',
	        [{
	         "id": id,
	         "element": a

	        }]
	    );
	 },
	 insertTable: function(id, row, column, value, successCallback,errorCallback) {
         cordova.exec(
             successCallback,
             errorCallback,
             'AWTPlugin',
             'insertTable',
             [{
             "id": id,
             "row": row,
             "column": column,
             "value": value
             }]);
     },

	 start: function(successCallback, errorCallback) {
     	    cordova.exec(
     	        successCallback,
     	        errorCallback,
     	        'AWTPlugin',
     	        'start',
     	         [{

     	         }]
     	    );
     	 },
     	 fireCell: function(id, row, column, successCallback,errorCallback) {
             cordova.exec(
             successCallback,
             errorCallback,
             'AWTPlugin',
             'fireCell',
             [{
                "id": id,
                "row": row,
                "column": column
                }]);
        },
	    fireRow: function(id, row, successCallback, errorCallback) {
	        cordova.exec(
	            successCallback,
	            errorCallback,
	            'AWTPlugin',
	            'fireRow',
	     	    [{"id": id,
	     	    "row": row
	     	    }]
	     	);
	     }
     	 
};



var app = {

setVisible: function(id, val) {

		var obj = document.getElementById(id);
		if(obj) {
			if(val=="false"){
			obj.style.visibility="hidden";
			}
			else if(val=="true"){
				obj.style.visibility="visible";}
				//obj.setAttribute("value",val);
	}
	},

    load: function() {
        var success = function(message) {
                console.log('Received Data: '+message);
                //document.getElementById('result').value=message;
        };

        var error = function(message) { alert("Error:"+message); };

        awtPlugin.load(success,error);

    },
       fire: function(id) {
        var success = function(message) {
                console.log('Received Data: '+message);
                //document.getElementById('result').value=message;
        };

        var error = function(message) { alert("Error:"+message); };

        awtPlugin.fire(id,success,error);

    },
    
    insert: function(id,a) {
        var success = function(message) {
        };

        var error = function(message) {};
        
        awtPlugin.insert(id,a);
    },

    firee: function(id,a) {
                var success = function(message) {
                };

                var error = function(message) {};

                awtPlugin.firee(id,a);
            },

    scrollTo: function(anchor) {
        frame = document.getElementById(anchor);
        while(!frame.classList.contains("frame") && frame != null){
            frame = frame.parentElement;
         }
        location.hash = "#" + frame.id;
    },

    setta: function(id, val) {
        var obj = document.getElementById(id);
            if(obj) {
                if( obj.value != val){
                    if(obj.tagName=="LABEL"){
                         if(obj.childNodes[0].tagName!=null){
                            obj.childNodes[0].innerHTML = val;
                         }
                         else{
                           obj.innerHTML = val;
                         }
                    }
                    else if(obj.tagName=="LI"){
                        if(obj.className=="colorMenu"){
                            var textnode = document.createTextNode(val);
                            var item = document.getElementById(id);
                            item.replaceChild(textnode, item.childNodes[0]);
                        }
                        else{
                            obj.innerHTML=val;
                        }
                    }
                    else
                        obj.value=val;
                }	//obj.setAttribute("value",val);
            }
	},
	
	settaTextArea: function(id, val){
	    var obj = document.getElementById(id);
	    if(obj){
	        obj.value+=val;
	    }
	},

	colora: function(id, col) {
        	//alert("setta:"+val);
        	var obj = document.getElementById(id);
            obj.style.backgroundColor = col;

        	},

        	showDialog: function(id) {

        		var obj = document.getElementById(id);
        		if(obj) {
        			obj.setAttribute("open", "");
        		}
        	},
        	
        closeDialog: function(id){
                var obj;
                if(id=="chiudi"){
                    obj = document.getElementById(id).parentElement;
        		}
        		else{
        		    obj = document.getElementById(id);
        		}
        		if(obj) {// se la closeDialog la lancia un bottone o un altra componente diversa dal bottone x
                   obj.close();
                }
        },

    	
    setProgressMax: function(id, val){
    
    		document.getElementById(id).max=val;
    
    	},



    setEnabled: function(id, val) {

                		var obj = document.getElementById(id);

                		if(obj) {
                		    if(val=="true"){

                                obj.style.color = "#818181";
                                obj.removeAttribute("class");
                                obj.setAttribute("class", "classnav");
                                alert("Attivo il menu");

                		    }else if(val=="false"){

                		        obj.style.color = "#606060";
                		        obj.removeAttribute("class");
                                obj.setAttribute("class", "classnav disabled");
                		        alert("Disattivo il menu");
                		    }

                		}else{
                		    console.log("Oggetto non trovato");
                		}


    	},

    	fireRow: function(id){
        		var success = function(message) {};
        		var error = function(message) {};

        		var result = id.split("-");
        		var row =[];
        		result[1].match(/\d+/g).forEach(function(i,j){row[j]=parseInt(i);});
                awtPlugin.fireRow(result[0],row[0]);
        	},

        	fireCell: function(id){
        		var success = function(message) {};
                var error = function(message) {};


                var result = id.split("-");
        		var cell =[];
        		result[1].match(/\d+/g).forEach(function(i,j){cell[j]=parseInt(i);});
        		awtPlugin.fireCell(result[0],cell[0], cell[1]);
        	},

        	insertTable: function(id, value){
        		var success = function(message) {};
        		var error = function(message) {};

        		var result = id.split("-");
        		var cell =[];
        		result[1].match(/\d+/g).forEach(function(i,j){cell[j]=parseInt(i);});
        		awtPlugin.insertTable(result[0],cell[0], cell[1], value);
        	},


        	setProperty: function(id, property, oldValue, newValue, type, selection){
        		var styleSheets = window.document.styleSheets;
        		var styleSheetsLength = styleSheets.length;
        		for(var i = 0; i < styleSheetsLength; i++){
        			var classes = styleSheets[i].rules || styleSheets[i].cssRules;
        			if (!classes)
        				continue;
        			var classesLength = classes.length;
        			for (var x = 0; x < classesLength; x++) {
        				if(selection != "selection"){
        					if (classes[x].selectorText == "."+id.toLowerCase()+"-"+type.toLowerCase()) {
        						classes[x].style[property.toLowerCase()] = newValue;
        					}
        					if(classes[x].selectorText == "."+id.toLowerCase()+"-"+type.toLowerCase()+"-selected"){
        						if(property.toLowerCase() != "background" && property.toLowerCase() != "color"){
        							classes[x].style[property.toLowerCase()] = newValue;
        						}
        					}
        				}
        			else{
        					if(classes[x].selectorText == "."+id.toLowerCase()+"-"+type.toLowerCase()+"-selected"){
        						classes[x].style[property.toLowerCase()] = newValue;
        					}
        				}
        			}
                }
        	},

            setRowSelection: function(id, rowsOld, rowsNew) {
        		var tds = document.getElementById(id).getElementsByTagName("td");
                for(var j=0; j<tds.length; j++){
                    var result = tds[j].id.split("-");
        			var cell =[];
        			result[1].match(/\d+/g).forEach(function(i,j){cell[j]=parseInt(i);});
                    if(cell[0] == rowsOld){
                        tds[j].setAttribute("class", ""+id+"-column");
                        tds[j].removeAttribute("contenteditable");
                    }
                    else if(cell[0] == rowsNew){
                        tds[j].setAttribute("class", ""+id+"-column-selected");
                        tds[j].removeAttribute("contenteditable");
                    }else {
                        tds[j].setAttribute("class", ""+id+"-column");
                        tds[j].removeAttribute("contenteditable");
                    }
                }
        	},

            setCellSelection: function(id, editingRow, editingColumn) {
                var tds = document.getElementById(id).getElementsByTagName("td");
        		for(var j=0; j<tds.length; j++){
        			var result = tds[j].id.split("-");
        			var cell =[];
        			result[1].match(/\d+/g).forEach(function(i,j){cell[j]=parseInt(i);});
        			if(cell[0] == editingRow && cell[1] == editingColumn){
        				tds[j].setAttribute("class", ""+id+"-column-selected");
        				tds[j].setAttribute("contenteditable", true);
        				addChangeCellListener(tds[j], tds[j].id);
        			}else{
        				tds[j].setAttribute("class", ""+id+"-column");
        				tds[j].removeAttribute("contenteditable");
        			}
        		}
        	},

            setValue: function(id, row, column, value) {
                var tds = document.getElementById(id).getElementsByTagName("td");
                for(var j=0; j<tds.length; j++){
                    var result = tds[j].id.split("-");
        			var cell =[];
        			result[1].match(/\d+/g).forEach(function(i,j){cell[j]=parseInt(i);});
                    if(cell[0] == row && cell[1] == column){
                        tds[j].innerHTML = value;
                    }
                }
        	},

        	insertRow: function(id, data) {
                var tab = document.getElementById(id);
        		var trs = tab.getElementsByTagName("tr");
                var index = trs.length -1;

        		var row = tab.insertRow(trs.length);
        		row.setAttribute("id", id+"-row"+index);
        		row.setAttribute("class", ""+id+"-row");
        		addFireRowListener(row, row.id); //tableEvent.js

                var obj = JSON.parse(data);
        		for(var k in obj) {
        			var cell = row.insertCell(k);
        			cell.innerHTML = ""+obj[k];
        			cell.setAttribute("id", ""+id+"-col"+index+","+k);
        			cell.setAttribute("class", ""+id+"-column");
        			addFireCellListener(cell, cell.id, cell.innerHTML); //tableEvent.js
        		}
        	},

        	removeRow: function(id, row) {
                var tab = document.getElementById(id);
        		var rowIndexDel = parseInt(row)+1;
        		tab.deleteRow(rowIndexDel);
        		var trs = tab.getElementsByTagName("tr");
        		var index = 0;
        		for(var i=1; i<trs.length; i++){
        			mapTapHammer[trs[i].id].off("tap");
        			delete mapTapHammer[trs[i].id];
        			trs[i].setAttribute("id", id+"-row"+index);
        			addFireRowListener(trs[i], trs[i].id); //tableEvent.js
        			var tds = trs[i].getElementsByTagName("td");
        			for(var j=0; j<tds.length; j++){
        				mapPressHammer[tds[j].id].off("press");
        				delete mapPressHammer[tds[j].id];
        				tds[j].removeEventListener("keyup", undefined, !1);
        				tds[j].setAttribute("id", id+"-col"+index+","+j);
        				addFireCellListener(tds[j], tds[j].id, tds[j].innerHTML);
        			}
        			index++;
        		}
        	},

        	 start: function() {
                        var success = function(message) {
                                console.log('Received Data: '+message);
                                //document.getElementById('result').value=message;
                        };

                        var error = function(message) { alert("Error:"+message); };

                        awtPlugin.start(success,error);
                    }

};

function updateFooter(){
    loadArrows();
    loadFramesBullets();
}

function loadArrows(){
    var buttonLeft = document.getElementById("buttonLeft");
    var buttonRight = document.getElementById("buttonRight");
    var frameNumber = parseInt(location.hash.replace( /^\D+/g, ''));
    var frameLeftID = "frame"+(frameNumber-1);
    var frameRightID = "frame"+(frameNumber+1);
    var frameLeft = document.getElementById(frameLeftID);
    var frameRight = document.getElementById(frameRightID);
    if(frameLeft != null){
        buttonLeft.classList.remove("hide");
        buttonLeft.classList.add("show");
    } else {
        buttonLeft.classList.remove("show");
        buttonLeft.classList.add("hide");
    }
    if(frameRight != null){
        buttonRight.classList.remove("hide");
        buttonRight.classList.add("show");
    } else {
        buttonRight.classList.remove("show");
        buttonRight.classList.add("hide");
    }
}

function defaultFrame(){
	//setting the menu logo
    var obj = document.getElementById("myNav");
    if(obj){
        var n = document.createTextNode("||| MENU");
        document.getElementsByClassName("scritta_menu")[0].appendChild(n);
    }
    
    console.log("going to frame0");
    location.hash = "#frame0";
    app.load();
}
function loadFramesBullets(){
    var frames = document.getElementsByClassName("frame");
    var frameNumber = 0;
    for(var i = 0; i < frames.length; i++) {
        if(frames[i].className == "frame") {
            frameNumber++;
        }
    }
    var framesBullets = document.getElementById("framesBullets");
    var defaultBullet;
    var selectedBullet = document.createElement("i");
    selectedBullet.classList.add("icon");
    selectedBullet.classList.add("ion-ios-circle-filled");

    framesBullets.innerHTML = "";
    var selectedFrameNumber = parseInt(location.hash.replace( /^\D+/g, ''));
    for(var i=0; i<selectedFrameNumber; i++){
        defaultBullet = document.createElement("i");
        defaultBullet.classList.add("icon");
        defaultBullet.classList.add("ion-ios-circle-outline");
        framesBullets.appendChild(defaultBullet);
     }
    framesBullets.appendChild(selectedBullet);
    for(var i=(selectedFrameNumber+1); i<frameNumber; i++){
        defaultBullet = document.createElement("i");
        defaultBullet.classList.add("icon");
        defaultBullet.classList.add("ion-ios-circle-outline");
        framesBullets.appendChild(defaultBullet);
    }
}

function spostamentoFrecciaDestra(){
        var frameNumber = parseInt(location.hash.replace( /^\D+/g, ''));
        var frameRightID = "frame"+(frameNumber+1);
        var frameRight = document.getElementById(frameRightID);
        if(frameRight != null){
        	   location.hash = frameRightID;
        }
}

function spostamentoFrecciaSinistra(){
         var frameNumber = parseInt(location.hash.replace( /^\D+/g, ''));
         var frameLeftID = "frame"+(frameNumber-1);
         var frameLeft = document.getElementById(frameLeftID);
         if(frameLeft != null){
       	     location.hash = "#"+frameLeftID;
       	 }
}

//GESTIONE DELLO SWIPE PER PASSARE DA UN FRAME AD UN ALTRO
var frames = document.getElementsByClassName("frame");

for(var i=0; i<frames.length; i++){
    if(frames[i].className == "frame"){
        var hammertime = new Hammer(frames[i]);
        hammertime.on('swipeleft', function(ev) {
            var frameNumber = parseInt(ev.target.id.replace( /^\D+/g, ''));
            var frameRightID = "frame"+(frameNumber+1);
            var frameRight = document.getElementById(frameRightID);
            if(frameRight != null)
                location.hash = "#"+frameRightID;
        });
        hammertime.on('swiperight', function(ev) {
            var frameNumber = parseInt(ev.target.id.replace( /^\D+/g, ''));
            var frameLeftID = "frame"+(frameNumber-1);
            var frameLeft = document.getElementById(frameLeftID);
            if(frameLeft != null)
	            location.hash = "#"+frameLeftID;
	    });
	}
}