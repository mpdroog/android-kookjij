package nl.rootdev.android.kookjijclient2.ui.tasks.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProgressInputStream extends FilterInputStream {
	private final long _maxBytes;
	private volatile Long _readBytes;
	
	/**
	 * 
	 * @param in
	 * @param maxBytes Should be available from the HTTP-header Content-length
	 */
	public ProgressInputStream(InputStream in, long maxBytes) {
		super(in);
		_maxBytes = maxBytes;
		_readBytes = Long.valueOf(0);
	}
	
	/**
	 * Get percentual progress for this download stream.
	 * @return
	 */
	public int getProgress() {
		synchronized (_readBytes) {
			return (int) (_readBytes / _maxBytes) * 100;
		}
	}
	
	private int updateReadBytes(int bytes)
	{
		synchronized (_readBytes) {
			_readBytes += bytes;
		}
		return bytes;
	}

	@Override
	public synchronized void mark(int readlimit) {
		throw new UnsupportedOperationException("Mark not supported in ProgressInputStream");
	}

	@Override
	public int read() throws IOException {
		return updateReadBytes(super.read());
	}

	@Override
	public int read(byte[] buffer, int offset, int count) throws IOException {
		return updateReadBytes(super.read(buffer, offset, count));
	}

	@Override
	public synchronized void reset() throws IOException {
		throw new UnsupportedOperationException("Mark not supported in ProgressInputStream");
	}

	@Override
	public long skip(long byteCount) throws IOException {
		throw new UnsupportedOperationException("Skip not supported in ProgressInputStream");
	}
}
