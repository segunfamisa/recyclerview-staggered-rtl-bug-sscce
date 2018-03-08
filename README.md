# RecyclerView + horizontal StaggeredGridLayoutManager + RTL
SSCCE - Simple Self Contained Correct Example for RecyclerView + Horizontal StaggeredGridLayoutManager + RTL Bug

This project demonstrates a bug that happens when you use RecyclerView, horizontal StaggeredGridLayoutManager in RTL mode.
* The list jumps back by a couple of items (perhaps by the number of visible items on the screen). 
* The item animation is also off. If an item is removed, it appears to call the remove animation for multiple items on 
the screen.
