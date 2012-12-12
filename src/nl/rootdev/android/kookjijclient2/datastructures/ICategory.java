package nl.rootdev.android.kookjijclient2.datastructures;

import java.util.List;

import nl.rootdev.android.kookjijclient2.datastructures.pb.CategoryItem;

public interface ICategory {
	public List<CategoryItem> getItemsList();
    public Integer getSetCurpage();
    public Integer getSetResultpages();	
}
