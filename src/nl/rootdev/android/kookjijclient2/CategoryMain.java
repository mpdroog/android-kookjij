package nl.rootdev.android.kookjijclient2;

import nl.rootdev.android.kookjijclient2.ui.TabsAdapter;
import nl.rootdev.android.kookjijclient2.ui.fixes.MenuItemSearchAction;
import nl.rootdev.android.kookjijclient2.ui.fixes.SearchPerformListener;
import nl.rootdev.android.kookjijclient2.ui.frames.CategoryFrame;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class CategoryMain extends SherlockFragmentActivity implements SearchPerformListener
{
	private TabsAdapter tabAdapter;
	private ViewPager pager;
	private Intent _intent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);    	
        AndroidUtilities.instantiate(this);
    	
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        
        _intent = getIntent();
        bar.setTitle(_intent.getExtras().getString("title"));

		pager = new ViewPager(this);
		pager.setId(R.id.normal);
		tabAdapter = new TabsAdapter(this, pager);
		String[] tabNames = getResources().getStringArray(_intent.getExtras().getInt("categoryIndex"));
		for(String tabName : tabNames) {
			Bundle bundle = new Bundle();
			bundle.putString("name", tabName);
	    	tabAdapter.addTab(bar.newTab().setText(tabName), CategoryFrame.class, bundle);
		}
    	setContentView(AndroidUtilities.getInstance().injectAdvertisement(this, pager));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	Context context = getSupportActionBar().getThemedContext();
    	new MenuItemSearchAction(context, menu, this); // not working??
        return true;
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
