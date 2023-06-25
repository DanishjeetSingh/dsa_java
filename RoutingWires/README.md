[//]: # (overview of the algo)
clone the goals arraylist to another arraylist goals 2, since it needs to be sorted so we can find the shortest wire first
a array of wires to store all the wires from the goals2 arrayllist (uses the manhattan distance formula to sort in increasing order)
a for loop that goes through all the endpoints in the goals2 AL and puts each coord in a goal into the visited hashset and also adds the coords into the parents hashmap.
a queue in instantiated to perform BFS.
a while loop to run till the queue gets empty 
another for loop to go through all the neighbors of each Coord
after this, the parent hashmap is full 
then every coord is traced from goal.end to goal.start and is added to the wire ArrayList 
and then the wire arraylist is added to the wires arraylist

[//]: # (example)
1 2 -1 2 0
0 0  0 0 0
0 0  0 0 0
3 0  3 0 1

in the above example
it will sort the endpoints based on their distance, in ascending order
then it will connect the 3 wire first since that is the shortest
then move on to 2 and at last 1
at the end the board will look like this
1 2 -1 2 0
1 2  2 2 0
1 1  1 1 1
3 3  3 0 1

[//]: # (eval of algo)
in the above example, 
the initial series was 1,3,2 
if we follow that then wire 2 will never happen
to overcome that i sorted the endpoints based on their size so we always make the shortest wire first. 
the sorting of endpoints helps a lot to minimize wire layouts.

[//]: # (eval of time and space complexity)
worst case time-complexity is O(n^2)

