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
		
		try {
			if (_category.getItems() != null) {
				JSONArray items = new JSONArray(_category.getItems());
				for(int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);
					listItems.addImageText(
						0, item.getString("name"),
						AndroidUtilities.getInstance().getDate(item.getLong("lastEdit"))
					);
				}
			}
		}
		catch(Exception e) {
			// TODO: What to do?
		}
		/*if (_category.getItemsList() != null) {
			for (ICategoryItem category : _category.getItemsList()) {
				items.addImageText(0,  category.getName(), AndroidUtilities.getInstance().getDate(category.getLastedit()));
			}
		}*/
/*		items.addImageText(0, "Fried chicken american style", "12 Feb 2012");
		items.addImageText(0, "Pinda-kokossoep", "12 Feb 2012");
		items.addImageText(0, "Tiramisu", "12 Feb 2012");*/
		view.setAdapter(listItems);
		return view;
	}	
}
