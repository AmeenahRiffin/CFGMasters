# Tree Planting App

```
       ,.,
      MMMM_    ,..,
        "_ "__"MMMMM          ,...,,
 ,..., __." --"    ,.,     _-"MMMMMMM
MMMMMM"___ "_._   MMM"_."" _ """"""
 """""    "" , \_.   "_. ."
        ,., _"__ \__./ ."
       MMMMM_"  "_    ./
        ''''      (    )
 ._______________.-'____"---._.
  \                          /
   \________________________/
   (_)                    (_)

```

^ ASCII Tree Credit: [Corwyn Yasuo Miyagishima](https://www.asciiart.eu/art/019606cace4da8c9)

As part of our assignment we were asked on making a programme that meets the criteria of our assignment.

This required me to touch up on some basics that were required by our assignment. I'll walk through the requirements:

```
* Output & input use - 20 marks
* Classes, objects, & inheritance - 24 marks
* Methods - 8 marks
* General Coding - 18 marks
```

## Vision

Based on what was provided as an example on the assignment sheet, I decided to go with the Tree Planting app but expand it into a small farming game. The idea is that we'd have little plots of land where we could plant seeds, water them and watch them grow into trees (or flowers). I ended up branching as I went along, changing the directly slightly to meet requirements but not too much to avoid over-scoping.

## Research

### Outputs:

I already understood that for terminal programs will usually use print or `System.out.println` as a form of output. So this was used as the main method of displaying information to the user. I also felt instead of using just text, to display the plant plots as ascii art, IE: "[ ]" for an empty plot of land, for visual appeal.

### Inputs:

I wanted to find a way of taking inputs for the user. On w3schools, I found that the correct syntax would be

```java
System.out.print("Enter choice: ");
int plantChoice = scanner.nextInt();
```

The `nextLine()` part initially confused me because when handling inputs in JS (a language I'm more familiar with), inputs are usually handled in a way where you just take it as is. But in Java you use `nextInt()` to take the input as an integer. According to w3 this only applies to integers, so you can have others like `nextLine()` if you wanted to take strings, I was considering having a name input based on this for the player but dashed the idea just to not overdo things.

It doesn't work on its own, I had to import it at the top of the code using `import java.util.Scanner;` so it would work.

Reference:
https://www.w3schools.com/java/java_user_input.asp

### Enums

I am familiar with the concept of Enums from working with SQL, they allow you to select from an array of values and are constrained to those values only.

To add my enum, I made a class called `PlantType` and defined them there. I could then reference them by using them like PlantType.TREE

If I had more time and were to improve the code I would have made one for trees and seedlings as well.

### Objects

The plantbeds themselves use objects. I have setters (to set the plant state) and getters (to get the plant state) methods. The objects are created when the user requests a number of Plant beds.

The app also prints all of the flower beds and lets us choose one, so hopefully this fufills the request of displaying these objects in their current form (IE: grown/seed state etc)

### Classes

Java requires us to use classes in order to make variables and methods. I decided to work with this to make a structure like this:

- Our main body for planting trees and taking inputs, this is the `TreePlanting` class.
- Our abstract class, PlantBed, this is used to define the basic properties of a plant bed, like what it's made of, what can be planted in it and what not.
- Then we have two subclasses, TreeBed and FlowerBed, that extend from PlantBed - this is for inheritance purposes. These are used to define the properties of a tree bed and a flower bed, like what can be planted in it.

### Void Classes

Void classes are classes that don't return anything. I made this `DisplayBed()` in my code because it only outputs to the screen but doesn't return an actual value.

### Abstract Classes

Abstract classes are classes that can't be used to create objects. They're generally intended to be a template so you use for inheritance. So, we have a PlantBed, which is meant to be used for both trees and flowers - but we can't make objects from Plantbed directly. I made trees and flowers inherit from it PlantBed()

### Inheritance

TreeBed and FlowerBed classes inherit from PlantBed, they have the same features but some alterations to make sense for what they are. The purpose of inheritance is so we don't have to repeat code and to keep a sense of consistency.

This is done by using this when defining the class:
`class TreeBed extends PlantBed`

This ensures it knows which class it borrows from.

### Return Classes

I used `countTotalPlants()` as a method to return the total number of plants in our beds.
