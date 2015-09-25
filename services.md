# Services #

A Service is an application component that can perform long-running operations in the background and does not provide a user interface. Another application component can start a service and it will continue to run in the background even if the user switches to another application. Additionally, a component can bind to a service to interact with it and even perform interprocess communication (IPC). For example, a service might handle network transactions, play music, perform file I/O, or interact with a content provider, all from the background. (Android official Dev)


### Start, stop & switch ###

As everything else in Android, when it comes to services, you need to make sure you account for a service in multiple places that need to know about it. I started by creating a Java file names Service1.java, and made sure that `public class Service1 extends Service`.
Eclipse was kind enough to auto-implement the needed methods, and left me with the relevant chunk of coding. Since this is just a dummy service, I didn't have to give it any specific role, and just made methods what will bring it to life and kill it.
```
//This is just a standard create service
public void onCreate(){
		super.onCreate();
		Toast.makeText(this, "Service 1 created", Toast.LENGTH_LONG).show();	
		paused = false;
	}
```

```
public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "Service 1 destroyed", Toast.LENGTH_LONG).show();
		paused = true;
	}
```

This screen shot shows the two services running on the Android emulator, running version 2.2 (Froyo)
![http://www.liorbk.com/img/runningservices1.png](http://www.liorbk.com/img/runningservices1.png)

I've created a second service called Service2.java that does the exact same thing. The second part was to account for the services in the manifest file (otherwise the program will crash).

```
<service 
        android:name=".Service1">
</service>
        
<service
        android:name=".Service2">
</service>
```

The third place is in Time.java, where I call the start and stopping of the service calls. Time.java is also responsible for the time taking and calculating.
```
//start
startService(new Intent(Time.this, Service1.class));

//stop
stopService(new Intent(Time.this, Service1.class));
```

Timing is a part of a package called currentTimeMillis, that is under System. The call returns a long with current time in milliseconds. All I did for this practice in terms of timing was to take the current time after each operation and subtract it from the most recent time before that.

### Running services ###

Android offers a services manager that is being supported natively since version 2.0. This services manager will display all the running services and group them by process (usually a process is connected to one app, unless binded with another). Running services also shows available memory and running time.

![http://ofps.oreilly.com/static/titles/9781449390501/screens/RunningServices.png](http://ofps.oreilly.com/static/titles/9781449390501/screens/RunningServices.png)

I was trying to create a button that will send the user to the running services activity window. Though Google does not provide any documentation to this activity, I thought I will be able to find it by myself. I researched for over 30 hours, and sent emails to other developers who managed to link their software with this activity. I knew this tool will make the management of the timing feature easier to use. Eventually I realized this task will be either impossible for me to perform (due to signing issues) or will take too long to reconstruct the window from scratch.
I manages to get the following list that provided me with a list of running services:
```
...
ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    	List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(50);
    	for (int i = 0; i < runningServices.size(); i++) {
    		ActivityManager.RunningServiceInfo runningServiceInfo = runningServices.get(i);
	    	Log.i(APP_TAG, "Process " + runningServiceInfo.process + " with component " + 
	    			runningServiceInfo.service.getClassName());
    	}
...
```

In order to get the correct layout shown, I changed the manifest file to accomodate for my request with the following:

```
...
 <activity android:name="runningServices"
              android:label="@string/runningservices_settings_title"
              android:clearTaskOnLaunch="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.MONKEY" />
            <category android:name="android.intent.category.VOICE_LAUNCH" />
            <category android:name="com.android.settings.SHORTCUT" />
        </intent-filter>
    </activity>
...
```

To get the layout ifself to show my new made list, I created a Linear layout and nested a list view inside:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
	xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    >
    
    <ListView 
    	android:id="@+id/listView1" 
    	android:layout_width="match_parent" 
    	android:layout_height="fill_parent">
    </ListView>
        
</LinearLayout>
```

Despite my efforts, I ended up with a non working solution. It's hard to pinpoint the heart of the problem. All the feedback I've received was a force close (crash) when I load up the services activity. It could be that I did not ask for enough [permissions](permissions.md), or mis-configured the manifest activity. Either way, I've spent too many hours and deviated too far from the main goal (for what I thought will take and hour or two, and ended up being a 30 hour obsession). I will not implement the running services activity (it's not even in the defined deliverable), though the work that was invested in it deserve to be mentioned.

Throughout the process I learned the manipulation of services, which I am sure will help me in getting the timing done correctly and efficiently.