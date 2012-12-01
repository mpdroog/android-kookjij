package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.ui.frames.IConnectionHandle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * Loading-screen during the download of something.
 * 
 * Useful so the user can see how far the download progress
 * is and intervene in case he's too impatient or changed
 * his mind and want's to safe some bytes in network traffic.
 * 
 * @author mark
 */
public class LoadingFragment extends SherlockFragment
{
	/** Reference to parent fragment */
	private final IConnectionHandle _handle;
	
	/**
	 * 
	 * @param handle Parent fragment to communicate back too
	 */
	public LoadingFragment(IConnectionHandle handle) {
		_handle = handle;
	}
	
	/**
	 * Fill view with data.
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final Button stop = (Button)getSherlockActivity().findViewById(R.id.button_download_stop);
		final Button retry = (Button)getSherlockActivity().findViewById(R.id.button_retry);
		
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stop.setEnabled(false);
				_handle.stopDownload();
				ProgressBar bar = (ProgressBar) getSherlockActivity().findViewById(R.id.progressBar1);
				bar.setEnabled(false);
				
				TextView text = (TextView) getSherlockActivity().findViewById(R.id.textView1);
				text.setText(R.string.stopped_download);				
			}
		});
		
		retry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				_handle.retryDownload();
			}
		});
	}
    
	/**
	 * Construct view from XML.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View screen = inflater.inflate(R.layout.loading, container, false);
		return screen;
	}
	
	/**
	 * Update this view's progress.
	 * @param percent
	 * @param message
	 */
	public void setProgress(int percent, CharSequence message) {
		ProgressBar bar = (ProgressBar) getSherlockActivity().findViewById(R.id.progressBar1);
		bar.setProgress(percent);
		bar.refreshDrawableState();
		
		TextView text = (TextView) getSherlockActivity().findViewById(R.id.textView1);
		text.setText(message);
	}
}
