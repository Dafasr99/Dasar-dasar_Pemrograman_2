## B) Objects Parameter

### The file ChangingPeople.java contains a program that illustrates parameter passing. The program uses Person objects defined in the file Person.java. Do the following:
- On a piece of paper, trace the execution of the program using diagrams and show what is printed by the program.

``` 
Execution starts at main method:
 
    Person person1 = new Person ("Sally", 13); // creates a new Person object with name Sally and age 13
    Person person2 = new Person ("Sam", 15); // creates a new Person object with name Sam and age 15
    int age = 21; // creates an integer variable age and assigns the value 21 to it
    String name = "Jill"; // creates a String variable name and assigns the value "Jill" to it
    
    System.out.println ("\nParameter Passing... Original values..."); // prints a message
    System.out.println ("person1: " + person1); // prints the value of person1
    System.out.println ("person2: " + person2); // prints the value of person2
    System.out.println ("age: " + age + "\tname: " + name + "\n"); // prints the values of age and name
 
    changePeople (person1, person2, age, name); // calls the changePeople method with person1, person2, age, and name as arguments
 
    System.out.println ("\nValues after calling changePeople..."); // prints a message
    System.out.println ("person1: " + person1); // prints the value of person1
    System.out.println ("person2: " + person2); // prints the value of person2
    System.out.println ("age: " + age + "\tname: " + name + "\n"); // prints the values of age and name
    
At the changePeople method:
 
    System.out.println ("\nInside changePeople... Original parameters..."); // prints a message
    System.out.println ("person1: " + p1); // prints the value of p1
    System.out.println ("person2: " + p2); // prints the value of p2
    System.out.println ("age: " + age + "\tname: " + name + "\n"); // prints the values of age and name
 
    Person p3 = new Person (name, age); // creates a new Person object p3 with name and age
    p2.changeName(name); // changes the name of p2 to name
    p2.changeAge(age); // changes the age of p2 to age
    name = "Jack"; // assigns the value "Jack" to the variable name
    age = 101; // assigns the value 101 to the variable age
    p1.changeName (name); // changes the name of p1 to name
    p1.changeAge (age); // changes the age of p1 to age
    
    System.out.println ("\nInside changePeople... Changed values..."); // prints a message
    System.out.println ("person1: " + p1); // prints the value of p1
    System.out.println ("person2: " + p2); // prints the value of p2
    System.out.println ("age: " + age + "\tname: " + name + "\n"); // prints the values of age and name
```

``` 
Output the program: 

Parameter Passing... Original values...
person1: Sally - Age 13
person2: Sam - Age 15
age: 21    name: Jill


Inside changePeople... Original parameters...
person1: Sally - Age 13
person2: Sam - Age 15
age: 21    name: Jill


Inside changePeople... Changed values...
person1: Jack - Age 101
person2: Jill - Age 21
age: 101   name: Jack


