function generateLine(axis, type, linePos, length){
    for(i = 1; i <= length; i++){
        if(axis == "y")
            console.log(type + "," + linePos + "," + i);
        else if(axis == "x")
            console.log(type + "," + i + "," + linePos);
    }
}

generateLine("x", "obstacle", 3, 10);