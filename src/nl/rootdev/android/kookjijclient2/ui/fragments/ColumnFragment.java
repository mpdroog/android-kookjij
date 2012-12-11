package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.IColumn;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class ColumnFragment extends SherlockFragment {
	/** Downloaded recipie from the webserver */
	private final IColumn _column;
	
	public ColumnFragment(IColumn column) {
		_column = column;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final TextView text = (TextView) getSherlockActivity().findViewById(R.id.column_message);
		final TextView title = (TextView) getSherlockActivity().findViewById(R.id.column_title);
		
		title.setText(_column.getName());
		text.setText(Html.fromHtml(_column.getText()));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.column, container, false);
	}
}
