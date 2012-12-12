package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class ErrorFragment extends SherlockFragment
{
	private String _stacktrace;
	private String _error;
	
	@Override
	public void setArguments(Bundle args) {
		super.setArguments(args);
		_stacktrace = args.getString("stacktrace");
		_error = args.getString("error");
	}
	    
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final EditText stacktrace = (EditText) getSherlockActivity().findViewById(R.id.stacktrace);
		final TextView reason = (TextView) getSherlockActivity().findViewById(R.id.reason);
	
		reason.setText(_error);
		stacktrace.setText(_stacktrace);
		
		// I/O
		final Button button = (Button) getSherlockActivity().findViewById(R.id.openStacktrace);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stacktrace.setVisibility(View.VISIBLE);
				button.setEnabled(false);
			}
		});
		
		final Button retry = (Button) getSherlockActivity().findViewById(R.id.retry);
		retry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//_handle.retryDownload();
			}
		});
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View screen = inflater.inflate(R.layout.error, container, false);
		return screen;
	}
}
