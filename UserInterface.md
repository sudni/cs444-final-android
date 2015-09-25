# User Interface #

This page will explain my experiences in building the user interface for this application. It will include a brief description of how the user interface works, different problems I encountered and how I solved them.


---


### Layout ###

  * Android interface is based in xml files with different attributes that account for the various features in a given window (i.e activity).

  * Layout structure can be nested (i.e a button inside a list or a test inside a tab). There is a tool that creates the XML ui pages automatically, but I quickly realized that the tool offered is too bulky, and makes the readability of the file very hard. In the automatic generation there is no appropriate spacing and no indentation at all.

  * While holding the machine in landscape mode, some of the buttons were cutout. I realized that Android enables us to create a separate layout for landscape (under /red/layout-land/file.xml) without any extra pointers. The existence of the file under that directory is sufficient for it to be used. I ended up creating a table and placing the buttons in separate columns, to prevent the cutoff.

### Manifest ###

  * Manifest in an xml file that includes information about all the activities of the application. Neglecting to mention an activity, will cause a the application to crash (they call it force close). The biggest problem with this is that when force closing, there is no reason, dump or any indication to what caused this. Debugging becomes a time consuming task that in my case took hours. The compiler doesn't inform you of any errors, or even warnings to that matter.

  * In manifest, we can decide on the visual style of said activity, by changing the theme. A good example of that can be seen in the manifest file line 19: "android:theme="@android:style/Theme.Dialog", which creates at activity that is shaped to the size of the the predefined layout (text in my case), while using a see-through background.

  * Permissions - Permissions are required to access many parts of the system and release portions of the API that grant access to different functionalities. To read more about the structure, go to [permissions](permissions.md).

### Menu ###

  * Android allows you to create a popup (from the bottom, so it actually pops upwards) menu. This is a good option for less common preferences and other information that does't require the user's immediate attention. I've decided to add the about button, and a settings button. In the settings I have put two check boxes that determine whether to save the data to file and whether to email the data. Of course at this point of the project (5/4/11 8:15 PM) the buttons are not connected to any functionality just yet.