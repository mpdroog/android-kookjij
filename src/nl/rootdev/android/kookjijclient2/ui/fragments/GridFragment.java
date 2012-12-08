package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.ui.ListImageTextAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;

public class GridFragment extends SherlockFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}
	
	@Override
	public void onDestroyView() {
		// Close all connections from the background processes
		super.onDestroyView();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		GridView view = (GridView) inflater.inflate(R.layout.grid, container, false);
		ListImageTextAdapter items = new ListImageTextAdapter();
		items.addImageText(0, "Fried chicken american style", "12 Feb 2012");
		items.addImageText(0, "Pinda-kokossoep", "12 Feb 2012");
		items.addImageText(0, "Tiramisu", "12 Feb 2012");
		view.setAdapter(items);
		return view;
	}	
}
