package nl.rootdev.android.kookjijclient2.ui.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.ICategory;
import nl.rootdev.android.kookjijclient2.datastructures.ICategoryItem;
import nl.rootdev.android.kookjijclient2.ui.ListImageTextAdapter;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;

public class GridFragment extends SherlockFragment {
	private ICategory _category;
	
	public GridFragment(ICategory category) {
		_category = category;
	}
	
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
		ListImageTextAdapter listItems = new ListImageTextAdapter();
		
		if (_category.getItemsList() != null) {
			for (ICategoryItem category : _category.getItemsList()) {
				listItems.addImageText(0,  category.getName(), AndroidUtilities.getInstance().getDate(category.getLastedit()));
			}
		}
		view.setAdapter(listItems);
		return view;
	}	
}
