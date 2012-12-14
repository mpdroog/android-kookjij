package nl.rootdev.android.kookjijclient2;

import nl.rootdev.android.kookjijclient2.ui.TabsAdapter;
import nl.rootdev.android.kookjijclient2.ui.fixes.MenuItemSearchAction;
import nl.rootdev.android.kookjijclient2.ui.fixes.SearchPerformListener;
import nl.rootdev.android.kookjijclient2.ui.fragments.CategoriesFragment;
import nl.rootdev.android.kookjijclient2.ui.frames.ColumnFrame;
import nl.rootdev.android.kookjijclient2.ui.frames.RecipieFrame;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;

public class Main extends LicenseCheckActivity implements SearchPerformListener {
	private TabsAdapter tabAdapter;
	private ViewPager pager;

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        AndroidUtilities.instantiate(this);
    	
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
		        
		pager = new ViewPager(this);
		pager.setId(R.id.normal);
		tabAdapter = new TabsAdapter(this, pager);
    	tabAdapter.addTab(bar.newTab().setText(R.string.tab_category), CategoriesFragment.class, null);
		tabAdapter.addTab(bar.newTab().setText(R.string.tab_recipie_day), RecipieFrame.class, null, true);
    	tabAdapter.addTab(bar.newTab().setText("Column"), ColumnFrame.class, null);
    	//tabAdapter.addTab(bar.newTab().setText("Favorieten"), FavoriteGridFragment.class, null);

    	setContentView(AndroidUtilities.getInstance().injectAdvertisement(this, pager));
    	Toast.makeText(this, R.string.checking_license, Toast.LENGTH_SHORT).show();
        checkLicense();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	Context context = getSupportActionBar().getThemedContext();
    	//MenuItem item = menu.add(0,4,0,"H");
    	//item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    	new MenuItemSearchAction(context, menu, this);
        return true;
    }
    
	@Override
	public void performSearch(String query) {
		throw new Error("Should not come here?");
	}    
}
