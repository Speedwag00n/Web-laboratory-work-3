const chartWidth = 330;
const chartHeight = chartWidth;
const rCoefficient = 0.4;

function toOriginalX(computingX, R) {
    let X = computingX / R;
    X *= rCoefficient * chartWidth;
    X += chartWidth / 2;

    return X;
}

function toOriginalY(computingY, R) {
    let Y = computingY / R;
    Y *= rCoefficient * chartHeight;
    Y = -Y + chartHeight / 2;

    return Y;
}

function toComputingX(originalX, R) {
    let X = originalX - chartWidth / 2;
    X /= rCoefficient * chartWidth;
    X *= R;

    return X;
}

function toComputingY(originalY, R) {
    let Y = -originalY + chartHeight / 2;
    Y /= rCoefficient * chartHeight;
    Y *= R;

    return Y;
}