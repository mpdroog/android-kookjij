package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.ui.frames.IConnectionHandle;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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
	/** Interval used to check download delta */
	private final static long DOWNLOAD_INTERVAL_CHECK = 3000L;

	/** Reference to parent fragment */
	private final IConnectionHandle _handle;
	/** Timer for updating progress-screen */
	private Handler _timer;
	
	private int _lastPercentage;

	
	/**
	 * 
	 * @param handle Parent fragment to communicate back too
	 */
	public LoadingFragment(IConnectionHandle handle) {
		_handle = handle;
		_timer = new Handler();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		_timer.removeCallbacks(_updateTask);
	}
	
	private Runnable _updateTask = new Runnable() {
		
		@Override
		public void run() {
			final TextView percentage = (TextView)getSherlockActivity().findViewById(R.id.percentage);

			int currentPercentage = _handle.getLoadingPercentage();
			System.out.println("Loading " + currentPercentage + "%");
			if(currentPercentage - _lastPercentage >= 10) {
				// Normal speed
				percentage.setText(currentPercentage + "%");
			} else {
				// SLOOOOW, 3sec and less than 10 percent
				percentage.setText(currentPercentage + "%");
			}
			_lastPercentage = currentPercentage;
			_timer.postDelayed(this, DOWNLOAD_INTERVAL_CHECK);
		}
	};
	
	/**
	 * Fill view with data.
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		final Button stop = (Button)getSherlockActivity().findViewById(R.id.button_download_stop);
		final Button retry = (Button)getSherlockActivity().findViewById(R.id.button_retry);

		_timer.postDelayed(_updateTask, DOWNLOAD_INTERVAL_CHECK);		
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stop.setEnabled(false);
				TextView text = (TextView) getSherlockActivity().findViewById(R.id.textView1);
				text.setText(R.string.stopped_download);				
				
				_timer.removeCallbacks(_updateTask);
				_handle.stopDownload();				
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
}
