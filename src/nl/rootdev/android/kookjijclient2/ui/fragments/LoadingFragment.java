package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class LoadingFragment extends SherlockFragment implements OnClickListener
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
		View screen = inflater.inflate(R.layout.loading, container, false);
		return screen;
	}
	
	public void setProgress(int percent, CharSequence message) {
		/** Dirty trick to read 'this windows id's' from another view */
		ProgressBar bar = (ProgressBar) getSherlockActivity().findViewById(R.id.progressBar1);
		bar.setProgress(percent);
		bar.refreshDrawableState();
		
		TextView text = (TextView) getSherlockActivity().findViewById(R.id.textView1);
		text.setText(message);
	}
}
