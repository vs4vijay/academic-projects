/**
 * java Script for Registration Form
 */

function validateName() {

	var name = document.getElementById("name").value;

	document.getElementById("nameError").innerHTML = "";

	if (name == "") {
		document.getElementById("nameError").innerHTML = "Enter Name ";
		return false;
	} else
		return true;
}

function validateEmailId() {

	var emailId = document.getElementById("emailID").value;
	var pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9][a-zA-Z0-9.-]*[a-zA-Z0-9]\.[a-zA-Z]{2,4}$/;
	var result = pattern.test(emailId);
	document.getElementById("mailIDError").innerHTML = "";
	if (result == false) {
		document.getElementById("mailIDError").innerHTML = "Invalid Email Address";
		return false;
	} else
		return true;

}

function validateDOB() {

	var dob = document.getElementById("dob").value;

	document.getElementById("dobError").innerHTML = "";

	if (dob.length > 0) 
	{
		return true;
	} 
	else
	{
		document.getElementById("dobError").innerHTML = "Invalid Date of birth";
		return false;
	}
}

function validateContact() {

	var mobile = document.getElementById("contactNumber").value;
	var pattern = /^[0-9]$/;
	var result = pattern.test(mobile);

	document.getElementById("mobileError").innerHTML = "";

	if ((mobile.length > 10) || (mobile.length < 10) || (result == true)) {
		document.getElementById("mobileError").innerHTML = "Invalid Contact Number ";
		return false;
	} else
		return true;
}

function validateGroupName() {

	var groupIndex = document.getElementById("groupID").selectedIndex;
	document.getElementById("groupIDError").innerHTML = "";

	if (groupIndex == 0) {
		document.getElementById("groupIDError").innerHTML = "Please Select Group";
		return false;
	} else
		return true;
}

function validate() {
	var name = validateName();
	var mail = validateEmailId();
	var contact = validateContact();
	var group = validateGroupName();
	var dob = validateDOB();
	
	if ((name == false) || (mail == false) || (contact == false)
			|| (group == false) || (dob == false))
		return false;
	else
		return true;

}