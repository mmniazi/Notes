var animals = [
	{
		name: "animal1",
		type: "beast"
	},
	{
		name: "animal2",
		type: "beast"
	},
	{
		name: "animal3",
		type: "beast"
	},
	{
		name: "animal4",
		type: "non-beast"
	},
	{
		name: "animal5",
		type: "non-beast"
	},
];
var names = animals.map((animal) => animal.name + ' is a ' + animal.type);
// console.log(names);
var amounts = [
	{
		amount: 1
	},
	{
		amount: 2
	},
	{
		amount: 3
	},
	{
		amount: 4
	},
	{
		amount: 5
	},
];
var total = amounts.reduce((sum, amount) => sum += amount, 0);

import fs from "fs";

var output = fs.readFileSync('data.txt', 'utf8')
	.trim()
	.split('\n')
	.map((line) => line.split(' '))
	.reduce((customers, line) => {
		customers[line[0]] = customers[line[0]] || [];
		customers[line[0]].push({
			name: line[1],
			price: line[2],
			quantity: line[3],
		});
		return customers;
	}, {});

console.log(JSON.stringify(output, null, 2));
