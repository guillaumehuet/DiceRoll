package com.pb.diceroll;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DiceRollActivity extends Activity {
	
	ImageView[] dice = {null, null};
	AnimationDrawable dice_anim[] = {null, null};
	Handler handler = new Handler();
	
	class ArgRunnable implements Runnable
	{
		ImageView v = null;
		
		public ArgRunnable(ImageView v)
		{
			this.v = v;
		}
		
		public void run()
		{
			v.setImageResource(getResources().getIdentifier("d"+((int)(Math.random()*6) + 1), "drawable", "com.pb.diceroll"));
		}
	};
	
	ArgRunnable fixDice[] = {null, null};
	
	private void setDice(int d)
    {
		dice[d].setImageResource(R.drawable.dice_anim);
		dice_anim[d] = (AnimationDrawable) dice[d].getDrawable();
		dice_anim[d].start();
		handler.removeCallbacks(fixDice[d]);
		handler.postDelayed(fixDice[d], 600);
	}
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dice[0] = (ImageView) findViewById(R.id.die1);
        dice[1] = (ImageView) findViewById(R.id.die2);
        fixDice[0] = new ArgRunnable(dice[0]);
        fixDice[1] = new ArgRunnable(dice[1]);
        Toast.makeText(this, getText(R.string.hint), Toast.LENGTH_LONG).show();
    }
    
    public void rollDice(View v)
    {
    	int d = Integer.parseInt(v.getTag().toString());
    	if(d > 1)
    	{
    		setDice(0);
    		setDice(1);
    	}
    	else
    		setDice(d);
    }
    
}