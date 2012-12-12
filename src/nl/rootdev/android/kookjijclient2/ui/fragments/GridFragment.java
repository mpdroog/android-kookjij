package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.RecipieActivity;
import nl.rootdev.android.kookjijclient2.datastructures.ICategory;
import nl.rootdev.android.kookjijclient2.datastructures.ICategoryItem;
import nl.rootdev.android.kookjijclient2.ui.ImageTextTuple;
import nl.rootdev.android.kookjijclient2.ui.ListImageTextAdapter;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;

public class GridFragment extends SherlockFragment implements OnItemClickListener {
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
				listItems.addImageText(new ImageTextTuple(
					0,
					category.getName(),
					AndroidUtilities.getInstance().getDate(category.getLastedit()),
					category.getId()
				));
			}
		}
		view.setAdapter(listItems);
		view.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ImageTextTuple item = (ImageTextTuple) parent.getItemAtPosition(position);
		Intent i = new Intent(getSherlockActivity().getApplicationContext(), RecipieActivity.class);
		i.putExtra("index", item.getRecipieIndex());
		startActivity(i);
	}	
}
