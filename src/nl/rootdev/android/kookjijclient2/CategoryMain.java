package nl.rootdev.android.kookjijclient2;

import nl.rootdev.android.kookjijclient2.ui.TabsAdapter;
import nl.rootdev.android.kookjijclient2.ui.fixes.MenuItemSearchAction;
import nl.rootdev.android.kookjijclient2.ui.fixes.SearchPerformListener;
import nl.rootdev.android.kookjijclient2.ui.fragments.GridFragment;
import nl.rootdev.android.kookjijclient2.ui.frames.CategoryFrame;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class CategoryMain extends SherlockFragmentActivity implements SearchPerformListener
{
	private TabsAdapter tabAdapter;
	private ViewPager pager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);    	
    	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED);
    	
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        
        bar.setTitle(getIntent().getExtras().getString("title"));

		pager = new ViewPager(this);
		pager.setId(R.id.normal);
		tabAdapter = new TabsAdapter(this, pager);
		String[] tabNames = getResources().getStringArray(getIntent().getExtras().getInt("categoryIndex"));
		for(String tabName : tabNames) {
	    	tabAdapter.addTab(bar.newTab().setText(tabName), CategoryFrame.class, null);
		}
    	setContentView(pager);
        
        Intent intent = getIntent();
        if(intent.ACTION_SEARCH.equals(intent.getAction())) {
        	String query = intent.getStringExtra(SearchManager.QUERY);
        	System.out.println(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	Context context = getSupportActionBar().getThemedContext();
    	new MenuItemSearchAction(context, menu, this); // not working??
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void performSearch(String query) {
		System.out.println(query);
	}
}
