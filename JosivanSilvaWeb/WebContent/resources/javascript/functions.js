/**
 * Shows a message according with its type (1=success,2=warning,3=error).
 * 
 * @param type the message type.
 * @param message the message.
 */
function showMessage (type, message) {
   var className = null;
   switch (type) {
	   case 1: // success
		 className = "success";
	     break;
	   case 2: // warning
		 className = "warning";
	     break;
	   case 3: // error
		 className = "error";
		 break;			   
   }
   className += "-message";
   $("#message-paragraph").text ("");
   $("#message-paragraph").text (message);
   $("#message-paragraph").removeClass();
   $("#message-paragraph").addClass (className);
   $("#message-container").fadeIn('slow');   
   // hides the message after 5 seconds
   setTimeout(function(){hideMessage()},5000);   
}

/**
 * Shows a message according with its type (1=success,2=warning,3=error), paragraph id and container id.
 * 
 * @param type the message type.
 * @param containerId the container id.
 * @param paragraphId the paragraph id.
 * @param message the message.
 */
function showMessageByContainer (type, containerId, paragraphId, message) {
   var className = null;
   switch (type) {
	   case 1: // success
		 className = "success";
	     break;
	   case 2: // warning
		 className = "warning";
	     break;
	   case 3: // error
		 className = "error";
		 break;			   
   }
   className += "-message";
   $("#"+paragraphId).text ("");
   $("#"+paragraphId).text (message);
   $("#"+paragraphId).removeClass();
   $("#"+paragraphId).addClass (className);
   $("#"+containerId).fadeIn('slow');   
   // hides the message after 5 seconds
   setTimeout(function(){hideMessageByContainer (containerId)},5000);   
}

/**
 * Hides the message container according with its container.
 * 
 * @param containerId the container id. 
 */
function hideMessageByContainer (containerId) {
	$("#"+containerId).fadeOut('slow');
}

/**
 * Hides the message container.
 */
function hideMessage () {
	$("#message-container").fadeOut('slow');
}

/**
 * Checks if a given field value is empty or not.
 * 
 * @param value the field value.
 */
function isEmpty (value) {
	var success = false;
	if (value == null || value.length == 0) {
		success = true;
	}
	return success;
}

/**
 * JQuery function that redirects to a specified url.
 * 
 * @param url the url.
 */
function redirectTo (url) {
	$(location).attr ('href', url);
}

/**
 * Checks if a given email is valid or not.
 * 
 * @param url the url.
 */
function isValidEmail (value) {
    var PATTERN =/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
    if (PATTERN.test (value)){         
		return true;
    } else {
    	return false; 
    }
}

/**
 * Changes the image source attribute.
 * 
 * @param obj the object which the image src will be modified.
 * @param newImage the new image name.
 * 
 */
function changeImageSrc (obj, newImage) {
	var IMAGE_PATH_PREFIX = "/josivansilvaweb/javax.faces.resource/";
	var IMAGE_PATH_SUFIX = ".page?ln=images";
	obj.src = IMAGE_PATH_PREFIX + newImage + IMAGE_PATH_SUFIX;	
}

/**
 * Changes the style background color.
 * 
 * @param obj the object.
 * @param color the color.
 * 
 */
function changeBackgroundColor (obj, color) {
	obj.style.backgroundColor = color;
}

/**
 * Cleans the fields values given their ids.
 * 
 * @param idFieldArr the id field array.
 * 
 */
function cleanFields (idFieldArr) {
	for (var i = 0; i < idFieldArr.length; i++) {
		var id = idFieldArr[i];
		$("#"+id).val("");
	}
}

/**
 * Gets the url parameters from the URL.
 * 
 * @returns url get parameters
 */
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

/**
 * Checks or not all the table checkbox items.
 */
function changeCheckboxesStatus (prefixId) {
	var inputArr = document.getElementsByTagName ("input");
	var statusAll = document.getElementById ("chkSelectAll").checked;
	for (var i = 0; i < inputArr.length; i++) {
		var input = inputArr[i];
		if (input.id.indexOf( prefixId ) != -1) {
			input.checked = (statusAll) ? true : false;
		}
	}
}

/**
 * Set all the selected checkboxes in an input hidden field.
 */
function setSelectedCheckboxes (prefixId) {
	var inputArr = document.getElementsByTagName ("input");
	var selectedIdArr = new Array();
	var selectedIds   = "";	
	for (var i = 0; i < inputArr.length; i++) {
		var input = inputArr[i];
		if (input.id.indexOf( prefixId ) != -1 
				&& input.checked == true) {			
			selectedIdArr.push (input.value);			
		}
	}
	var size = selectedIdArr.length - 1;
	for (var j = 0; j < selectedIdArr.length; j++) {
		var id = "";
		id = selectedIdArr[j];
		var isLastItem = (j == size) ? true : false;
		if (!isLastItem) {
			selectedIds += id + ",";	
		} else {
			selectedIds += id;
		}
		document.getElementById("form-search:form-remove:removeItems").value = selectedIds;	
	}
	
	
}

/**
 * Gets the row index from a given string.
 * 
 * @param str the string.
 * @returns the index.
 */
function getRowIndexFromStr (str) {
	var PATTERN  = null;
	var rowIndex = null;
	var index    = null;
	PATTERN = "[0-9]";
	index = str.search (PATTERN);
	if (index != -1) {
		rowIndex = str.charAt (index);
	}	
	return rowIndex;
}
