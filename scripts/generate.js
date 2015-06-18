// #! /home/brandon/.nvm/versions/node/v0.12.4/bin/node

function generateLine(axis, type, linePos, start, length){
    for(i = 0; i < length; i++){
        if(axis == "y")
            console.log(type + "," + linePos + "," + (i + start));
        else if(axis == "x")
            console.log(type + "," + (i + start) + "," + linePos);
    }
}

generateLine("x", "obstacle", 9, 1, 5);

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

// makeRect('obstacle', 3, 3, 7, 7, true);