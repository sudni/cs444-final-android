package cs444.and;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Service1 extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	boolean paused = false;
	
	public void onCreate(){
		super.onCreate();
		Toast.makeText(this, "Service 1 created", Toast.LENGTH_LONG).show();	
		paused = false;
	}
	
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "Service 1 destroyed", Toast.LENGTH_LONG).show();
		paused = true;
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        
        return START_STICKY;
    }
	
	
}
