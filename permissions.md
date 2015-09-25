# Permissions #

When Android market was first introduced, it brought with it a bag load of security concerns. Some of which are about developers writing malicious code, trying to infect the host machine, or just mining data about the user's location, contacts and phone usage.

Android came up with an elegant fix to this problem by introducing the permission sets, grouped in permission levels.

```
//from developer.android.com
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.me.app.myapp" >
    <permission android:name="com.me.app.myapp.permission.DEADLY_ACTIVITY"
        android:label="@string/permlab_deadlyActivity"
        android:description="@string/permdesc_deadlyActivity"
        android:permissionGroup="android.permission-group.COST_MONEY"
        android:protectionLevel="dangerous" />
    ...
</manifest>
```

The relevant permissions I started with were:
```
"android.permission-group.SYSTEM_TOOLS"
"android.permission-group.HARDWARE_CONTROLS"
"android.permission-group.DEVELOPMENT_TOOLS"
```
After getting those, I started removing unnecessary permissions, until I managed to get my code to work with no external permissions. Since all my services run under my authority (my process), and when unbound, I leave them to be orphaned under null service. I didn't need any permissions.
Perhaps I needed some to get the "running services" activity to work, but I could not manage to get it to run, not matter how many permissions I threw in there.