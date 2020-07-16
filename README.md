# Coachmark
This is a Kotlin library which can be used to add custom Coachmarks to you application.
It requires a minimum SDK of **16**.

The library included a sample application with exampled on how to create various types
of Coackmarks. It also has examples of callback functions when the coachmark is touched and
functions for controlling when the coachmarks are displayed.

# Important

This library requires that the rendering of the UI be complete before the coachmark is 
created. 

1, You can override onWindowFocusChanged and implement your coachmark code there.

2, You can use ViewTreeObserver and build your coachmark code when the focus changes

    contentView.getViewTreeObserver().addOnWindowFocusChangeListener(
        new ViewTreeObserver.OnWindowFocusChangeListener() {
 
             @Override public void onWindowFocusChanged(boolean hasFocus) {  
                 // Your coachmark code here
             }
        });
            

        
3, You can do a handler.postDelayed  ( This is a little cheesy but does work ok. It is used in the example app ). 

    handler.postDelayed( Runnable { buildCoachmarks() }, 300 )

4, You could also try using the Androidx KTX extensions to determine when layout is complete.


**Why do you need to do this?** 

This library uses the position and size of individual components to determine how the coachmarks should be 
sized and oriented. Prior to layout all these values are zero. If called before layout is complete nothing gets
displayed.

**Adding the library to your project**



