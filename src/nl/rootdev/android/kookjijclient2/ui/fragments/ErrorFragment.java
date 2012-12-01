package nl.rootdev.android.kookjijclient2.ui.fragments;

import java.io.PrintWriter;
import java.io.StringWriter;

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
	private Exception _exception;
	
	public ErrorFragment(Exception e) {
		_exception = e;
	}
	    
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final EditText stacktrace = (EditText) getSherlockActivity().findViewById(R.id.stacktrace);
		final TextView reason = (TextView) getSherlockActivity().findViewById(R.id.reason);
		
		// Update window
		{	
			final StringWriter str = new StringWriter();
			final PrintWriter writer = new PrintWriter(str);
			_exception.printStackTrace(writer);
	
			reason.setText(_exception.getMessage());		
			stacktrace.setText(str.toString());
		}
		
		// I/O
		final Button button = (Button) getSherlockActivity().findViewById(R.id.openStacktrace);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stacktrace.setVisibility(View.VISIBLE);
				button.setEnabled(false);
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
