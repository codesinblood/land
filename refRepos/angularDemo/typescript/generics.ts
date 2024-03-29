// Create a generic Map (an Object like an Array, but instead with Key-Value Pairs). The key will always be a string.

// Let's keep it simple and only add the following methods to the Map:

// setItem(key: string, item: T) // should create a new key-value pair

// getItem(key: string) // should retrieve the value of the provided key
// clear() // should remove all key-value pairs
// printMap() // should output key-value pairs
// The map should be usable like shown below:

class MyMap<T> {
    temp = [];
    setItem<T>(key: string, item: T) {
        this.temp[key] = item;
    }
    getItem<T>(key: string) {
        console.log(this.temp[key]);
    }
    printMap() {
        console.log(this.temp);
    }
}

const numberMap = new MyMap<number>();
numberMap.setItem('apples', 5);
numberMap.setItem('bananas', 10);
numberMap.printMap();

const stringMap = new MyMap<string>();
stringMap.setItem('name', "Max");
stringMap.setItem('age', "27");
stringMap.printMap();
