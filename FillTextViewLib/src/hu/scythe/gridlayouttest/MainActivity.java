package hu.scythe.gridlayouttest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private ViewGroup verticalGroup;
	private ViewGroup horizontalGroup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		verticalGroup = (ViewGroup) findViewById(R.id.layoutVertical);
		horizontalGroup = (ViewGroup) findViewById(R.id.layoutHorizontal);
	}

	public void onVerticalPlus(View v) {
		LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) verticalGroup.getLayoutParams();

		p.weight += 0.5;

		verticalGroup.setLayoutParams(p);
	}

	public void onVerticalMinus(View v) {
		LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) verticalGroup.getLayoutParams();

		if (p.weight > 1) {
			p.weight -= 0.5;
		}

		verticalGroup.setLayoutParams(p);
	}
	
	public void onHorizontalPlus(View v) {
		LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) horizontalGroup.getLayoutParams();
		
		p.weight += 0.5;
		
		horizontalGroup.setLayoutParams(p);
	}
	
	public void onHorizontalMinus(View v) {
		LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) horizontalGroup.getLayoutParams();
		
		if (p.weight > 1) {
			p.weight -= 0.5;
		}
		
		horizontalGroup.setLayoutParams(p);
	}
}
