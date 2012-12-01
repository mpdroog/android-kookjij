package nl.rootdev.android.kookjijclient2.ui.frames;

/**
 * Methods for intervening from the UI on network actions.
 * 
 * This class was initially added to the RecipieFrame so it's children
 * can communicate back to it in case the user changed his mind.
 * 
 * @author mark
 */
public interface IConnectionHandle {
	/**
	 * Stop the current download.
	 */
	public void stopDownload();
	
	/**
	 * Start a new download connection dropping
	 * the current.
	 */
	public void retryDownload();
}
