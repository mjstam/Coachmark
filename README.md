# Coachmark
This is a Kotlin library which can be used to add custom Coachmarks to you application.
It requires a minimum SDK of **16**.

The library includes a sample application with examples on how to create various types
of Coackmarks. It also has examples of callback functions invoked when the coachmark is touched and
functions for controlling when the coachmarks are displayed.

![Image of Saimple App Main Page](http://hmravens.com/coachmark/coachmark_main.png)

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

4, You could also try using the Androidx KTX extensions to determine when layout is complete. I have not verified
   that this works, but it should :)


**Why do you need to wait until the UI is rendered?** 

This library uses the position and size of individual components to determine how the coachmarks should be 
sized and oriented. Prior to layout all these values are zero. If called before layout is complete nothing gets
displayed (because all sizes and positions are reported as 0 ).

**Adding the library to your project**

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
    dependencies {
	        implementation 'com.github.mjstam:Coachmark:Tag'
	}
    
    Note: Tag needs to be changed to the version you are using ( i.e 1.0 )
    
    
    



