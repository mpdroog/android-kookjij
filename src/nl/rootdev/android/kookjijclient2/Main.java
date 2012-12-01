package nl.rootdev.android.kookjijclient2;

import nl.rootdev.android.kookjijclient2.ui.TabsAdapter;
import nl.rootdev.android.kookjijclient2.ui.fixes.MenuItemSearchAction;
import nl.rootdev.android.kookjijclient2.ui.fixes.SearchPerformListener;
import nl.rootdev.android.kookjijclient2.ui.fragments.RecipieFragment;
import nl.rootdev.android.kookjijclient2.ui.frames.RecipieFrame;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class Main extends SherlockFragmentActivity implements SearchPerformListener {
	private TabsAdapter tabAdapter;
	private ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    	
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        AndroidUtilities.instantiate(this);
		
		pager = new ViewPager(this);
		pager.setId(R.id.normal);
		tabAdapter = new TabsAdapter(this, pager);
    	//tabAdapter.addTab(bar.newTab().setText("Categorieen"), CategoriesFragment.class, null);
		tabAdapter.addTab(bar.newTab().setText("Recept v/d Dag"), RecipieFrame.class, null, true);
    	//tabAdapter.addTab(bar.newTab().setText("Column"), ColumnFragment.class, null);
    	//tabAdapter.addTab(bar.newTab().setText("Favorieten"), FavoriteGridFragment.class, null);
    	setContentView(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	Context context = getSupportActionBar().getThemedContext();
    	new MenuItemSearchAction(context, menu, this);
        return true;
    }

    public static class MyTabListener<T extends SherlockFragment> implements TabListener {
    	private Class<T> _fragment;
    	private Fragment _curFragment;
    	private SherlockFragmentActivity _activity;
    	
		public MyTabListener(SherlockFragmentActivity activity, Class<T> fragment) {
			_fragment = fragment;
			_activity = activity;
		}

		@Override
		public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ignoreFt) {
			FragmentManager fragMgr = _activity.getSupportFragmentManager();
		    FragmentTransaction ft = fragMgr.beginTransaction();
		    
			if(_curFragment == null) {
				_curFragment = SherlockFragment.instantiate(_activity, _fragment.getName());
				ft.add(android.R.id.content, _curFragment);
			}
			else {
				//ft.setCustomAnimations(android.R.animator.fade_in, 100);
				ft.attach(_curFragment);
			}
			ft.commit();
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if(_curFragment != null) {
				ft.detach(_curFragment);
			}
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
    }

	@Override
	public void performSearch(String query) {
		throw new Error("Should not come here?");
	}    
}
