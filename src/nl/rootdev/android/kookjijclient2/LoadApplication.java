package nl.rootdev.android.kookjijclient2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class LoadApplication extends LicenseCheckActivity {
	private Handler _handler;
	private boolean _validLicense;
	
	private Runnable runnable = new Runnable() {		
		@Override
		public void run() {
			if (_validLicense) {
				Intent intent = new Intent(LoadApplication.this, Main.class);
				startActivity(intent);	
				finish();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.load_app);
		
    	Toast.makeText(this, R.string.checking_license, Toast.LENGTH_SHORT).show();
        checkLicense();

		_handler = new Handler();
		_handler.postDelayed(runnable, 1000L);
	}

	@Override
	protected void updateSuccess() {
		_validLicense = true;
	}
}
