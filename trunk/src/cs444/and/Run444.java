package cs444.and;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Run444 extends Activity implements OnClickListener{
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
   
        //click listeners for all the buttons
        View servicesButton = findViewById(R.id.services_button);
        servicesButton.setOnClickListener(this);
        
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }
    
    //actions that respond to main menu buttons
    public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.about_button:
    		Intent i = new Intent(this, About.class);
    		startActivity(i);
    		break;
    	case R.id.services_button:
    		i = new Intent(this, Future.class);
    		startActivity(i);
    		break;
    	case R.id.new_button:
    		i = new Intent(this, Time.class);
    		startActivity(i);
    		break;
    		
    	case R.id.exit_button:
    		finish();
    		break;
    	}
    }
    
    //create the pop-up menu on the bottom
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    //details the different buttons on the pop-up menu
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	case R.id.settings:
    		startActivity(new Intent(this, Prefs.class));
    		return true;
    	case R.id.about:
    		startActivity(new Intent(this, About.class));
    		return true;
    	//might add more later if I have the time
    	}
    	return false;
    }
    
}