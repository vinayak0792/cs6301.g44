Group: g44
Long Project 5: Implementation of Skip List.
Team members: Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal

Files:
SkipList.Java : Program that initializes and allows the use of Skip List, the functionality/operations are as follows:

* find: Helper function to find an element in the list. Returns the array of nodes till the element or the nodes where the element could be inserted.

* getIndex: Function to get the index of the given data

* add: Adds element to list. If element already exists, replace it. Returns true if new node is added to list

* remove: Removes element from list. Removed element is returned. Returns null if element not in list

* get: Return element at index n of list. First element is at index 0.

* rebuild: Recursive helper function that reorganizes the skip list into a perfect skip list.

* iterator: Iterates through the elements of list in sorted order

* isEmpty: checks if list is empty. Returns boolean

* last: Returns last element of list

* ceiling: Finds smallest element that is greater or equal to x

* contains: Checks if list contains given element

* first: Returns first element of list

* floor: Finds largest element that is less than or equal to x

* size: Returns the number of elements in the list

* printList: Function to print the elements of the list in sorted order.

Sample run:

Intial list
10 11 12 13 14 15 16 17 18 19 
Last element: 19
After removing element 12
10 11 13 14 15 16 17 18 19 
Does list contain 15? true
List size: 9
List after rebuilding
10 11 13 14 15 16 17 18 19 


References:

The below website was used to visualize the skip list for better understanding of the ADT. 
https://people.ok.ubc.ca/ylucet/DS/SkipList.html

The below youtube video was referred to better understand the ADT in depth.
https://www.youtube.com/watch?v=2g9OSRKJuzM

