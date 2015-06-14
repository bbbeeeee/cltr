function generateLine(axis, type, linePos, length){
    for(i = 1; i <= length; i++){
        if(axis == "y")
            console.log(type + "," + linePos + "," + i);
        else if(axis == "x")
            console.log(type + "," + i + "," + linePos);
    }
}

// generateLine("x", "obstacle", 3, 10);

function makeRect(type, startingX, startingY, endingX, endingY, filled){
	for(x = startingX, j = 1; x <= endingX; x++){
		console.log(type + "," + x + "," + startingY);
		console.log(type + "," + x + "," + endingY);
		if(!filled){
			if(j == 1 || x == endingX){
				for(k = startingY + 1; k < endingY; k++){
					console.log(type + "," + x + "," + k);
				}
			}
		} else {
			for(k = startingY + 1; k < endingY; k++){
				console.log(type + "," + x + "," + k);
			}
		}
		j++; 
	}
}

makeRect('obstacle', 3, 3, 7, 7, true);