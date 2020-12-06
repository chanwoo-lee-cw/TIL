"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// import * as U from '../utils/makeRandomNumber'
var makeRandomNumber_1 = require("../utils/makeRandomNumber");
var Person = /** @class */ (function () {
    function Person(name, age) {
        this.name = name;
        this.age = age;
    }
    return Person;
}());
exports.default = Person;
exports.makePerson = function (name, 
// age : number = U.makeRandomNumber()) : IPerson => ({name,age})
age) {
    if (age === void 0) { age = makeRandomNumber_1.makeRandomNumber(); }
    return ({ name: name, age: age });
};
//# sourceMappingURL=Person.js.map