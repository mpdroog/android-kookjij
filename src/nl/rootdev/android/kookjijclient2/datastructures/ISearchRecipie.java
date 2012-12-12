package nl.rootdev.android.kookjijclient2.datastructures;

import java.util.List;

import nl.rootdev.android.kookjijclient2.datastructures.pb.Recipie;

public interface ISearchRecipie {
    public Integer getSetResultpages();
    public List<Recipie> getRecipiesList();
}
