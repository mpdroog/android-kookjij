package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class ColumnFragment extends SherlockFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle bundle = getArguments();
		final TextView text = (TextView) getSherlockActivity().findViewById(R.id.column_message);
		final TextView title = (TextView) getSherlockActivity().findViewById(R.id.column_title);
		
		title.setText(bundle.getString("name"));
		text.setText(Html.fromHtml(bundle.getString("text")));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.column, container, false);
	}
}
