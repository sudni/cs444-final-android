package cs444.and;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Service2 extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	boolean paused = false;
	
	public void onCreate(){
		super.onCreate();
		Toast.makeText(this, "Service 2 created", Toast.LENGTH_LONG).show();
		
		paused = false;
		
	}
	
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "Service 2 destroyed", Toast.LENGTH_LONG).show();
		
		paused = true;
	}

}
