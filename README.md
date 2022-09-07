# JATOMA

Tool for automatic miniaturization of Java Desktop applications into Android mobile applications.
Last version date: 01/12/18
Directory Testing_Tools: contains some tools over which we can execute JATOMA tools for conversion From Java App into Android app
Directory JATOMA: contains the JATOMA tool that make the automatic conversion From Java App into Android app

REQUIREMENTS:

Apache Cordova (https://cordova.apache.org/)
Android virtual device (for starting the emulator)

USES:

Main class 'test.BuilderMain'
The program needs 3 arguments to start:
1. Absolute path of the Java project
2. Name of the Java project
3. Absolute path of the exit

Startup parameters:
-path: specifies that the paths have been passed (points 1, 2, 3)
-emulate: for the app on the emulator at the end of the process
