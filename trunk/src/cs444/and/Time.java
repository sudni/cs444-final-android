package cs444.and;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Time extends Activity implements OnClickListener{
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);
		
		//click listener
        View startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
    	case R.id.start_button:
    		Intent i = new Intent(this, About.class);
    		startActivity(i);
    		break;
		}
		
	}

}
