package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class ErrorFragment extends SherlockFragment implements OnClickListener
{
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("Klik klik!");
	}
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// //        bar.setTitle(getIntent().getExtras().getString("title"));
		View screen = inflater.inflate(R.layout.error, container, false);
		return screen;
	}
}
