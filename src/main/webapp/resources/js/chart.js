const arrowLength = 7;
const lineWidth = 2;
const pointScale = 3;
const signSpace = 9;
const pointRadius = 1.5;

const axisesColor = "black";
const signsColor = axisesColor;

const signsFont = "14px monospace";

{
	let canvas = $("#task-chart")[0];
	canvas.width = canvas.offsetWidth;
	canvas.height = canvas.offsetHeight;
	draw();
	$("#task-chart").bind("click", click);
}

function clear() {
	let canvas = $("#task-chart")[0];
	let context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width, canvas.height);
}

function draw() {
	let canvas = $("#task-chart")[0];
	drawAxises(canvas);
	drawAxisesSigns(canvas);
	let R = document.getElementById('computation-form:slider-input-R').value;
	drawPointsSigns(canvas, R);
	if (!$("#result-table-container").hasClass("hidden")) {
		let results = $("#result-table tbody tr");
		for (var i = results.length - 1; i >= 0; i--) {
			let X = results.eq(i).find("td").eq(0).text();
			let Y = results.eq(i).find("td").eq(1).text();
			let R = document.getElementById('computation-form:slider-input-R').value;
			let originalX = toOriginalX(X, R);
			let originalY = toOriginalY(Y, R);
			if (results.eq(i).find("td").eq(4).text().trim() == "Да") {
				drawPoint(canvas, originalX, originalY, "GreenYellow");
			} else {
				drawPoint(canvas, originalX, originalY, "Red");
			}
		}
	}
}

function addLastPoint() {
	let canvas = $("#task-chart")[0];
	let results = $("#result-table tbody tr");
	let X = results.eq(0).find("td").eq(0).text();
	let Y = results.eq(0).find("td").eq(1).text();
	let R = document.getElementById('computation-form:slider-input-R').value;
	let originalX = toOriginalX(X, R);
	let originalY = toOriginalY(Y, R);
	if (results.eq(0).find("td").eq(4).text().trim() == "Да") {
		drawPoint(canvas, originalX, originalY, "GreenYellow");
	} else {
		drawPoint(canvas, originalX, originalY, "Red");
	}
}

function drawAxises(canvas) {
	let context = canvas.getContext("2d");

	context.beginPath();
	context.strokeStyle = axisesColor;
	context.lineWidth = lineWidth;

	context.moveTo(canvas.width / 2, canvas.height);
	context.lineTo(canvas.width / 2, 0);

	context.lineTo(canvas.width / 2 - arrowLength / 2, arrowLength);
	context.moveTo(canvas.width / 2, 0);
	context.lineTo(canvas.width / 2 + arrowLength / 2, arrowLength);

	context.moveTo(0, canvas.height / 2);
	context.lineTo(canvas.width, canvas.height / 2);

	context.lineTo(canvas.width - arrowLength, canvas.height / 2 + arrowLength / 2);
	context.moveTo(canvas.width, canvas.height / 2);
	context.lineTo(canvas.width - arrowLength, canvas.height / 2 - arrowLength / 2);


	context.moveTo(canvas.width * 0.1, canvas.height / 2 - pointScale);
	context.lineTo(canvas.width * 0.1, canvas.height / 2 + pointScale);

	context.moveTo(canvas.width * 0.3, canvas.height / 2 - pointScale);
	context.lineTo(canvas.width * 0.3, canvas.height / 2 + pointScale);

	context.moveTo(canvas.width * 0.7, canvas.height / 2 - pointScale);
	context.lineTo(canvas.width * 0.7, canvas.height / 2 + pointScale);

	context.moveTo(canvas.width * 0.9, canvas.height / 2 - pointScale);
	context.lineTo(canvas.width * 0.9, canvas.height / 2 + pointScale);

	context.moveTo(canvas.width / 2 - pointScale, canvas.height * 0.1);
	context.lineTo(canvas.width / 2 + pointScale, canvas.height * 0.1);

	context.moveTo(canvas.width / 2 - pointScale, canvas.height * 0.3);
	context.lineTo(canvas.width / 2 + pointScale, canvas.height * 0.3);

	context.moveTo(canvas.width / 2 - pointScale, canvas.height * 0.7);
	context.lineTo(canvas.width / 2 + pointScale, canvas.height * 0.7);

	context.moveTo(canvas.width / 2 - pointScale, canvas.height * 0.9);
	context.lineTo(canvas.width / 2 + pointScale, canvas.height * 0.9);

	context.stroke();
}

function drawAxisesSigns(canvas) {
	let context = canvas.getContext("2d");
    context.font = signsFont;
    context.fillStyle = signsColor;

	context.fillText("Y", canvas.width / 2 + signSpace / 2, signSpace);
	context.fillText("X", canvas.width - signSpace, canvas.height / 2 - signSpace / 2);
}

function drawPointsSigns(canvas, r) {
	let context = canvas.getContext("2d");
	context.font = signsFont;
	context.fillStyle = signsColor;

	let rIsNumber = !isNaN(Number(r));

	let sign;
	rIsNumber ? sign = -r + "" : sign = "-" + r;
	if (rIsNumber && (Math.abs(sign) - Math.floor(Math.abs(sign))) == 0) {
		sign = Number(sign).toFixed(1);
	}
	context.fillText(sign, canvas.width * 0.1 - 0.5 * sign.length * signSpace, canvas.height / 2 - signSpace / 2);
	context.fillText(sign, canvas.width / 2 + signSpace / 2, canvas.height * 0.9 + signSpace / 2);
	rIsNumber ? sign = -r / 2 + "" : sign = "-" + r + "/2";
	if (rIsNumber && (Math.abs(sign) - Math.floor(Math.abs(sign))) == 0) {
		sign = Number(sign).toFixed(1);
	}
	context.fillText(sign, canvas.width * 0.3 - 0.5 * sign.length * signSpace, canvas.height / 2 - signSpace / 2);
	context.fillText(sign, canvas.width / 2 + signSpace / 2, canvas.height * 0.7 + signSpace / 2);
	rIsNumber ? sign = r / 2 + "" : sign = r + "/2";
	if (rIsNumber && (Math.abs(sign) - Math.floor(Math.abs(sign))) == 0) {
		sign = Number(sign).toFixed(1);
	}
	context.fillText(sign, canvas.width * 0.7 - 0.5 * sign.length * signSpace, canvas.height / 2 - signSpace / 2);
	context.fillText(sign, canvas.width / 2 + signSpace / 2, canvas.height * 0.3 + signSpace / 2);
	sign = r + "";
	if (rIsNumber && (Math.abs(sign) - Math.floor(Math.abs(sign))) == 0) {
		sign = Number(sign).toFixed(1);
	}
	context.fillText(sign, canvas.width * 0.9 - 0.5 * sign.length * signSpace, canvas.height / 2 - signSpace / 2);
	context.fillText(sign, canvas.width / 2 + signSpace / 2, canvas.height * 0.1 + signSpace / 2);
}

function drawPoint(canvas, x, y, pointColor) {
	let context = canvas.getContext("2d");
	context.beginPath();
	context.strokeStyle = pointColor;
	context.fillStyle = pointColor;

	context.arc(x, y, pointRadius, 0, 2 * Math.PI);
	context.closePath();
	context.fill();
	context.stroke();
}

function click(event) {
	let canvas = event.target;

	let originalX = event.pageX - canvas.offsetLeft;
	let originalY = event.pageY - canvas.offsetTop;
	let R = document.getElementById('computation-form:slider-input-R').value;

	document.getElementById('canvas-form:task-chart-x').value = String(toComputingX(originalX, R)).substring(0, 10);
	document.getElementById('canvas-form:task-chart-y').value = String(toComputingY(originalY, R)).substring(0, 10);
	document.getElementById('canvas-form:canvas-form-button').click();
}

function updateR() {
	let R = document.getElementById('computation-form:slider-input-R').value;
	document.getElementById('r-update-form:current-r').value = R;
	document.getElementById('r-update-form:r-update-form-button').click();
}

function redraw() {
	clear();
	draw();
}