package nl.rootdev.android.kookjijclient2.ui.fragments;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.RecipieActivity;
import nl.rootdev.android.kookjijclient2.ui.ImageTextTuple;
import nl.rootdev.android.kookjijclient2.ui.ListImageTextAdapter;
import nl.rootdev.android.kookjijclient2.ui.fixes.ExtendedSherlockFragment;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class CategoryFragment extends ExtendedSherlockFragment implements OnItemClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		GridView grid = (GridView) getView().findViewById(R.id.gridView1);
		if (grid != null) {
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				grid.setColumnWidth(320);
			} else {
				grid.setColumnWidth(240);
			}
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view;
		
		Bundle bundle = getBundle(savedInstanceState);
		if (bundle == null) {
			return inflater.inflate(R.layout.category_empty, container, false);
		}

		String[] names = bundle.getStringArray("names");
		if (names != null) {
			GridView grid = (GridView) inflater.inflate(R.layout.grid, container, false);
			ListImageTextAdapter listItems = new ListImageTextAdapter();
			long[] lastEdits = bundle.getLongArray("lastEdits");
			long[] ids = bundle.getLongArray("ids");
			
			for (int i = 0; i < names.length; i++) {
				listItems.addImageText(new ImageTextTuple(
					0,
					names[i],
					AndroidUtilities.getInstance().getDate(lastEdits[i]),
					ids[i]
				));
				grid.setAdapter(listItems);
				grid.setOnItemClickListener(this);
			}
			view = grid;
		} else {
			view = inflater.inflate(R.layout.category_empty, container, false);
		}
		
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
