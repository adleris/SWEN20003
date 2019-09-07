TODO LIST / NOTES

* Need to swap all of the handling of locations from *Point*s to *Rectangle*s
    * May even be best off just removing the reliance on *Point*s entirely?
    * The initialising of the point can be done in an *Entity* function that's overriden for particular size contraints.
    * Movement of the ball could be entirely handle by the *Rectangle*'s *MoveTo()*
    
 