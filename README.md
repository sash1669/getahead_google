# getahead_google
Repo of programs for the Google Get Ahead 2021

# Unknown alphabet
relevant for students that are in their pre final/final year of Bachelor’s or currently pursuing a Masters/PhD degree.

Given a dictionary (a list of words in lexicographic order) of all words in a fictional language, find the alphabet (an ordered list of characters) of that language.

This language can contain any character (of the native char data type). Upper/lower case characters can be treated differently (for simplicity). Assume standard lexicographical ordering (order by characters from left to right, if X is a prefix of Y then X is sorted before Y), but where the order of characters is unknown.

Write a function that computes an alphabet (ordered list of characters) that is consistent with the given dictionary.

For example,
Given the dictionary: [ART, RAT, CAT, CAR]
A valid solution would be: [A, T, R, C] or [T, A, R, C].

# Counting Islands
relevant for students that are in their pre final/final year of Bachelor’s or currently pursuing a Masters/PhD degree.

You are given a 2-dimensional map made of square tiles. Each tile is either land or sea. Write a function that computes the number of islands on the map.

Two tiles belong to the same island if they share a side, i.e., if they are adjacent horizontally or vertically, but not diagonally.

The input to your function is a representation of the map of your choice. The function should return the number of islands, i.e., a number.

# HashTable with separate chaining
Implement a hash table with collisions handled by separate chaining.

The data structure is initialized with two parameters:

initial number of slots/buckets, and
maximum allowed load factor (number of entries / number of slots).
It needs to support the following operations:

put - map given key to a given value,
get - lookup a mapping for a given key,
remove - delete mapping for a given key,
size - number of entries currently in the data structure,
nslots - number of slots/buckets currently used by the data structure.
All operations are expected to have amortized time complexity of O(1). The load factor is expected to never exceed the given maximum.

# Rearranging cars
There is a parking lot with N spaces and N-1 cars in it. Your task is to write an algorithm to rearrange the cars in a given way. Only one car can be moved at a time to the empty slot.

You can assume each parking space has a unique identifier (which is a letter, starting with 'A' and allocated in order) and each car has a unique identifier which is an integer.

The state of the parking lot is then represented by the location of each car in a parking lot. This will be done in a Parking class.

You should then define a function that, given a start state and an end state for the parking lot, generates a sequence of moves from the start state to the end state.

# Rectangle Sums
Given a list of lists of positive integers that represent arrangements of numbers as shown in the test cases below, find the rectangle containing the largest sum such that the rectangle doesn't contain any empty/missing cells.

Your program should return the pair of array indices that represent the rectangle. If there's more than one rectangle with an optimal sum, any arbitrary one may be returned.
