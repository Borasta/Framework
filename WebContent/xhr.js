xhr = function(arg) {
	const ajax = new XMLHttpRequest();

	// Si es un get asiganmos los parametros a la url
	if(arg.method === "get" && arg.data) {
		arg.url += "?" + arg.data;
	}

	// Abrimos la conexion
	ajax.open(arg.method, arg.url, !arg.sync);

	//verificamos si los headers existen, si no colocamos uno por default
	var headers = arg.headers ? arg.headers : {
		"Content-type": 'application/x-www-form-urlencoded'
	};

	// Asignamos los headers
	for (var header in headers) {
		var value = headers[header];
		ajax.setRequestHeader(header, value);
	}

	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			var result = ajax.responseText;

			try {
				result = JSON.parse(result);
				if(arg.success)
					arg.success(result);
			} catch(e) {
				if(arg.fail)
					arg.fail(result, e);
			}
		}
	};

	console.log(arg.data)

	ajax.send(arg.data);

}

xhr.jsonToParams = function(arg) {
	console.log(arg);
	var query = "";
	var data = arg;

	for (var name in data) {
		var value = data[name];
		var type = typeof(data[name]);

		query += "&" + name + "=";

		query += (type === "object") ?
			JSON.stringify(data[name]) :
			data[name];
	}

	// Quitamos el & que sobra
	query = query.replace("&", "");

	return query;
}


// 'String Carlos, int 25'
xhr.varsToArguments = function(arr) {

	var query = "";
	
	for (var i = 0; i < arr.length; i++) {
		var type = arr[i].type;
		var value = arr[i].value;
		
		if( !type )
			type = typeof value;
		
		// Verificamos si el tipo es numerico y tiene un punto flotante
		if( type == "number" && value.toindexOf('.') != -1 )
			type = "double";
		else if( type == "number" )
			type = "int";

		// Escribimos el tipo y el valor
		query += type + " " + value;
		
		// Si aun hay otras variables por agregar colocammos una coma
		if( i < arr.length - 1 )
			query += ", ";
	}

	return query;
}

/*

	// Ejemplo de un get
	xhr({
		"method": 'get',
		"url": './server.php',
		"data": xhr.jsonToParams({
			"json": {
				"a": 'a'
			}
		}),
		"success": function (argument) {
			console.log(argument);
		},
		"fail": function (argument, error) {
			console.log(argument)
			console.log(error)
		}
	});

	// Servidor en php
	<?php

	$json = json_decode($_GET["json"]);

	echo $json->a;

*/

/*
	// Ejemplo de un post
	xhr({
		"method": 'get',
		"url": './server.php',
		"data": xhr.jsonToParams({
			"json": {
				"a": 'a'
			}
		}),
		"success": function (argument) {
			console.log(argument);
		},
		"fail": function (argument, error) {
			console.log(argument)
			console.log(error)
		}
	});

	// Servidor en php
	<?php

	$json = json_decode($_GET["json"]);

	echo $json->a;

*/