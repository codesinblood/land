// Exercise 1 - As Arrow Function
const double = (value: number) => value * 2;
console.log(double(10));

// Exercise 2 - If only we could provide some default values...
const greet = (name = "Max") => console.log("Hello, " + name);

greet();
greet("Anna");

// Exercise 3 - Isn't there a shorter way to get all these Values? 
const numbers = [-3, 33, 38, 5];
console.log(Math.min(...numbers));

// Exercise 4 - I have to think about Exercise 3 ...
const newArray = [55, 20];
newArray.push(...numbers);
console.log(newArray);

// Exercise 5 - That's a well-constructed array.
const testResults = [3.89, 2.99, 1.38];
const [result1, result2, result3] = testResults 
console.log(result1, result2, result3);

// Exercise 6 - And a well-constructed object!
const scientist = {firstName: "Vinoth", experience: 12};
const {firstName, experience} = scientist
console.log(firstName, experience);