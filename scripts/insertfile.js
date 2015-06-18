#! /home/brandon/.nvm/versions/node/v0.12.4/bin/node

var fs = require('fs');
var number = process.argv[2];
console.log(number);
var dir = '../android/assets/maps/'

function makefiles(){
	for(var m = 1; m < 9; m++){
		fs.writeFile(dir + m + '.cltr', 'Toolbelt');
	}
}

function insertMap(number){

	fs.readdir(dir, function(err, files){
		console.log(files.length + " total maps.");

		for(var i = files.length; i >= number; i--){
			var ni = i + 1;
			console.log(dir + (i + 1) + '.cltr');
			fs.renameSync(dir + i + '.cltr', dir + '' + ni + '.cltr', function(err) {
				if (err) console.log(err);
			});
		}

		fs.writeFile(dir + number + '.cltr', 'n', function(err){
			if(err) console.log(err);
		});

	});
}

insertMap(number);