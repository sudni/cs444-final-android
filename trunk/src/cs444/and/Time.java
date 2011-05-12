package cs444.and;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Time extends Activity implements OnClickListener{
	
	private static final String TAG = "Test";
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);
		
		//click listener
        View startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        
	}

	//when clicked start test it will prompt for test type in a new dialog
	public void onClick(View v) {
		switch (v.getId()) {
    	case R.id.start_button:
    		newTestDialog();
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
			newSPTest();
		else
			newUBTest();
	}
	
	//Single process - services started here will stay under a single process
	public void newSPTest(){
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
	    time1 = System.currentTimeMillis();
	    status = status + "\n" + (time1-time2) + ": Services switched";
	    t.setText(status);
	    
	    //Kill Service 1 - optional
	    
	    //Kill Service 2 - optional
		
		
	}
	
	//Unbound - Services started here will be separated into different processes
	public void newUBTest(){
		//system time in milliseconds
		long baseTime;
		long time2;
		
		
		//set status label
		TextView t=new TextView(this); 
	    t=(TextView)findViewById(R.id.time_status); 
		
		//get base time
		String status = "0: Test Initiated";
		baseTime = System.currentTimeMillis();
	    t.setText(status);
	    
	    //start service 1
	    startService(new Intent(Time.this, Service1.class));
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2 - baseTime) + ": Service 1 Started";
	    t.setText(status);
	    
	    //start service 2
	    startService(new Intent(Time.this, Service2.class));
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2-baseTime) + ": Service 2 Started";
	    t.setText(status);
	    
	    //unbind services
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2-baseTime) + ": Services unbound";
	    t.setText(status);

	    
	    //switch services
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2-baseTime) + ": Services switched";
	    t.setText(status);
		
		
	}

}
