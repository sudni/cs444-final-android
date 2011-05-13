package cs444.and;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Time extends Activity implements OnClickListener{
	
	private static final String TAG = "Test";
	private boolean servicesState = false;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);
		
		//click listeners
        View startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        
        View killButton = findViewById(R.id.kill_button);
        killButton.setOnClickListener(this);
	}

	//when clicked start test it will prompt for test type in a new dialog
	public void onClick(View v) {
		switch (v.getId()) {
    	case R.id.start_button:
    		newTestDialog();
    		break;
    	case R.id.kill_button:
    		killServices();
    		break;
		}
		
	}
	
	//test types are incremental timing or cumulative
	private void newTestDialog(){
		new AlertDialog.Builder(this)
			.setTitle("Select timing")
			.setItems(R.array.timing_type, 
					new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					startTest(which);
				}
			}).show();
	}
	
	private void startTest(int which){
		//for debugging
		Log.d(TAG, "clicked on " + which);
		
		//start test here
		if(which == 0)
			newMultiProcessesTest();
		else
			newSingleProcessTest();
	}
	
	//Single process - services started here will stay under a single process
	public void newMultiProcessesTest(){
		//system time in milliseconds
		long time1;
		long time2;
		
		
		//set status label
		TextView t=new TextView(this); 
	    t=(TextView)findViewById(R.id.time_status); 
		
		//get base time
		String status = "0: Test Initiated";
		time1 = System.currentTimeMillis();
	    t.setText(status);
	    
	    //start service 1
	    startService(new Intent(Time.this, Service1.class));
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2 - time1) + ": Service 1 Started";
	    t.setText(status);
	    
	    //start service 2
	    startService(new Intent(Time.this, Service2.class));
	    time1 = System.currentTimeMillis();
	    status = status + "\n" + (time1-time2) + ": Service 2 Started";
	    t.setText(status);
	    
	    //switch services
	    this.getWallpaper();
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2-time1) + ": Services switched";
	    t.setText(status);
	    
		servicesState = true;
		
	}
	
	//Unbound - Services started here will be separated into different processes
	public void newSingleProcessTest(){
		//system time in milliseconds
		long time1;
		long time2;
		
		
		//set status label
		TextView t=new TextView(this); 
	    t=(TextView)findViewById(R.id.time_status); 
		
		//get base time
		String status = "0: Test Initiated";
		time1 = System.currentTimeMillis();
	    t.setText(status);
	    
	    //start service 1
	    startService(new Intent(Time.this, Service1.class));
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2 - time1) + ": Service 1 Started";
	    t.setText(status);
	    
	    //start service 2
	    startService(new Intent(Time.this, Service2.class));
	    time1 = System.currentTimeMillis();
	    status = status + "\n" + (time1-time2) + ": Service 2 Started";
	    t.setText(status);
	    
	    //switch services1
	    bindService(new Intent(Time.this, 
	            Service1.class), null, Context.BIND_AUTO_CREATE);
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2-time1) + ": Switched to service 1";
	    t.setText(status);
	    
	  //switch services
	    bindService(new Intent(Time.this, 
	            Service2.class), null, Context.BIND_AUTO_CREATE);
	    time1 = System.currentTimeMillis();
	    status = status + "\n" + (time1-time2) + ": Switched to service 2";
	    t.setText(status);
	    
	    unbindService(null);
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2-time1) + ": Bound back together";
	    t.setText(status);
	    
	    
		servicesState = true;
	}

	private void killServices(){
		TextView t=new TextView(this); 
	    t=(TextView)findViewById(R.id.time_status); 
	    
		if(servicesState) { 
			stopService(new Intent(Time.this, Service1.class));
			stopService(new Intent(Time.this, Service2.class));
			t.setText("Killed services 1 & 2");
			servicesState = false;
		}else {
			t.setText("Service has not been initiated");
		}
		
		
		
		
		
	}
}
