{
	setCurrentTime();
	setInterval(setCurrentTime, 9000);
}

function setCurrentTime() {
	let date = new Date();
	let datetime = ("0" + date.getDate()).slice(-2) + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + date.getFullYear() + " " + ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2) + ":" + ("0" + date.getSeconds()).slice(-2);

	let = currentTimeElement = document.getElementById("current-time");
	if (currentTimeElement != null) {
		currentTimeElement.textContent = datetime;
	}
}