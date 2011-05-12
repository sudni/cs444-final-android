package cs444.and;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Time extends Activity implements OnClickListener{
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);
		
		//click listener
        View startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        
	}

	public void onClick(View v) {
		switch (v.getId()) {
    	case R.id.start_button:
    		newTestButton();
    		break;
		}
		
	}
	
	public void newTestButton(){
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
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2 - time1) + ": Service 1 Started";
	    t.setText(status);
	    
	    //start service 2
	    time1 = System.currentTimeMillis();
	    status = status + "\n" + (time1-time2) + ": Service 2 Started";
	    t.setText(status);
	    
	    //unbind services
	    time2 = System.currentTimeMillis();
	    status = status + "\n" + (time2-time1) + ": Services unbound";
	    t.setText(status);

	    
	    //switch services
	    time1 = System.currentTimeMillis();
	    status = status + "\n" + (time1-time2) + ": Services switched";
	    t.setText(status);
		
		
	}

}
