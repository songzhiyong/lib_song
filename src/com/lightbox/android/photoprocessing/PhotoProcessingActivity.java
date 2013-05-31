package com.lightbox.android.photoprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lightbox.android.photoprocessing.utils.BitmapUtils;
import com.lightbox.android.photoprocessing.utils.MediaUtils;

public class PhotoProcessingActivity extends Activity implements
		OnLongClickListener {
	private static final String TAG = "PhotoProcessingActivity";

	public static final int REQUEST_CODE_SELECT_PHOTO = 1;

	private static final String SAVE_STATE_PATH = "com.lightbox.android.photoprocessing.PhotoProcessing.mOriginalPhotoPath";
	private static final String SAVE_CURRENT_FILTER = "com.lightbox.android.photoprocessing.PhotoProcessing.mCurrentFilter";

	private String mOriginalPhotoPath = null;
	private Bitmap mBitmap = null;
	private ImageView mImageView = null;
	private Gallery mGallery;
	//
	private int mCurrentFilter = 0;

	private static FilterTask sFilterTask;
	private static SavePhotoTask sSavePhotoTask;

	private ProgressDialog mProgressDialog = null;

	private String[] samples = { "girl_001", "girl_002", "girl_003",
			"girl_004", "girl_005", "girl_006", "girl_007", "girl_008",
			"girl_009", "girl_010", "girl_011" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mImageView = (ImageView) findViewById(R.id.imageViewPhoto);
		mGallery = (Gallery) findViewById(R.id.gallery);
		GalleryAdapter adapter = new GalleryAdapter();
		mGallery.setAdapter(adapter);
		mGallery.setSelection(3, true);
		mGallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(android.widget.AdapterView<?> arg0,
					View arg1, int arg2, long arg3) {
				if (mBitmap != null) {
					sFilterTask = new FilterTask(PhotoProcessingActivity.this);
					mCurrentFilter = arg2 + 1;
					sFilterTask.execute(arg2 + 1);
				}

			}
		});
		findViewById(R.id.buttonSave).setEnabled(false);
		ImageButton galleryButton = (ImageButton) findViewById(R.id.buttonGallery);
		galleryButton.setOnLongClickListener(this);
		ImageButton saveButton = (ImageButton) findViewById(R.id.buttonSave);
		saveButton.setOnLongClickListener(this);
		saveButton.setEnabled(false);

	}

	@Override
	protected void onPause() {
		hideProgressDialog();
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (sFilterTask != null) {
			sFilterTask.reattachActivity(this);
		}
		if (sSavePhotoTask != null) {
			sSavePhotoTask.reattachActivity(this);
		}
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(SAVE_STATE_PATH, mOriginalPhotoPath);
		outState.putInt(SAVE_CURRENT_FILTER, mCurrentFilter);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		mOriginalPhotoPath = savedInstanceState.getString(SAVE_STATE_PATH);
		mCurrentFilter = savedInstanceState.getInt(SAVE_CURRENT_FILTER);
		if (mOriginalPhotoPath != null) {
			loadFromCache();
			mImageView.setImageBitmap(mBitmap);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_SELECT_PHOTO && resultCode == RESULT_OK) {
			Uri photoUri = data.getData();
			mImageView.setImageBitmap(null);
			mOriginalPhotoPath = MediaUtils.getPath(this, photoUri);
			loadPhoto(mOriginalPhotoPath);
			mImageView.setImageBitmap(mBitmap);
			saveToCache(mBitmap);
		}
	}

	private void enableFilterEditAndSaveButtons() {
		findViewById(R.id.buttonSave).setEnabled(true);
	}

	public void onGalleryButtonClick(View v) {
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, REQUEST_CODE_SELECT_PHOTO);
	}

	public void onSaveButtonClick(View v) {
		sSavePhotoTask = new SavePhotoTask(this);
		sSavePhotoTask.execute();
	}

	private void saveToCache(Bitmap bitmap) {
		if (bitmap == null || bitmap.isRecycled()) {
			return;
		}
		File cacheFile = new File(getCacheDir(), "cached.jpg");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(cacheFile);
		} catch (FileNotFoundException e) {
			// do nothing
		} finally {
			if (fos != null) {
				bitmap.compress(CompressFormat.JPEG, 100, fos);
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					// Do nothing
				}
			}
		}
	}

	private String savePhoto(Bitmap bitmap) {
		File file = new File(mOriginalPhotoPath);
		File saveDir = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/Lightbox/");
		saveDir.mkdir();
		String name = file.getName().substring(0,
				file.getName().lastIndexOf('.'))
				+ "_";
		int count = 0;
		String format = String.format("%%0%dd", 3);
		File saveFile;
		do {
			count++;
			String filename = name + String.format(format, count) + ".jpeg";
			saveFile = new File(saveDir, filename);
		} while (saveFile.exists());

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(saveFile);
			bitmap.compress(CompressFormat.JPEG, 95, fos);

			return saveFile.getAbsolutePath();
		} catch (FileNotFoundException e) {
			Log.w(TAG, e);
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					// Do nothing
				}
			}
		}

		return "";
	}

	private void loadPhoto(String path) {
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

		if (mBitmap != null) {
			mBitmap.recycle();
		}

		mBitmap = BitmapUtils.getSampledBitmap(path,
				displayMetrics.widthPixels, displayMetrics.heightPixels);

		if (mBitmap != null && !mBitmap.isMutable()) {
			mBitmap = PhotoProcessing.makeBitmapMutable(mBitmap);
		}

		int angle = MediaUtils.getExifOrientation(path);
		mBitmap = PhotoProcessing.rotate(mBitmap, angle);

		enableFilterEditAndSaveButtons();
	}

	private void showTempPhotoInImageView() {
		if (mBitmap != null) {
			Bitmap bitmap = Bitmap.createScaledBitmap(mBitmap,
					mBitmap.getWidth() / 4, mBitmap.getHeight() / 4, true);
			mImageView.setImageBitmap(bitmap);
		}
	}

	private void loadFromCache() {
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

		if (mBitmap != null) {
			mBitmap.recycle();
		}

		File cacheFile = new File(getCacheDir(), "cached.jpg");
		mBitmap = BitmapUtils.getSampledBitmap(cacheFile.getAbsolutePath(),
				displayMetrics.widthPixels, displayMetrics.heightPixels);

		enableFilterEditAndSaveButtons();
	}

	private void showFilterProgressDialog() {
		mProgressDialog = ProgressDialog.show(this, "",
				getString(R.string.applying_filter));
	}

	private void showSavingProgressDialog() {
		String message = "保存中...";
		mProgressDialog = ProgressDialog.show(this, "", message);
	}

	private void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	@Override
	public boolean onLongClick(View view) {
		switch (view.getId()) {
		case R.id.buttonGallery:
			Toast.makeText(this, R.string.toast_hint_gallery,
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.buttonSave:
			Toast.makeText(this, R.string.toast_hint_save, Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return false;
	}

	private static class EditListAdapter extends BaseAdapter {
		private LayoutInflater mInflator;
		private Context mContext;

		public EditListAdapter(Context context) {
			mContext = context;
			mInflator = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return PhotoProcessing.EDIT_ACTIONS.length;
		}

		@Override
		public Object getItem(int position) {
			return mContext.getString(PhotoProcessing.EDIT_ACTIONS[position]);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				view = mInflator.inflate(R.layout.edit_list_item, null);
			}

			((TextView) view.findViewById(R.id.filterNameTextView))
					.setText((CharSequence) getItem(position));

			return view;
		}
	}

	private static class FilterTask extends AsyncTask<Integer, Void, Bitmap> {
		WeakReference<PhotoProcessingActivity> mActivityRef;

		public FilterTask(PhotoProcessingActivity activity) {
			mActivityRef = new WeakReference<PhotoProcessingActivity>(activity);
		}

		public void reattachActivity(PhotoProcessingActivity activity) {
			mActivityRef = new WeakReference<PhotoProcessingActivity>(activity);
			if (getStatus().equals(Status.RUNNING)) {
				activity.showFilterProgressDialog();
			}
		}

		private PhotoProcessingActivity getActivity() {
			if (mActivityRef == null) {
				return null;
			}

			return mActivityRef.get();
		}

		@Override
		protected void onPreExecute() {
			PhotoProcessingActivity activity = getActivity();
			if (activity != null) {
				activity.showFilterProgressDialog();
				activity.showTempPhotoInImageView();
			}
		}

		@Override
		protected Bitmap doInBackground(Integer... params) {
			PhotoProcessingActivity activity = getActivity();

			if (activity != null) {
				activity.loadPhoto(activity.mOriginalPhotoPath);
				int position = params[0];
				Bitmap bitmap = PhotoProcessing.filterPhoto(activity.mBitmap,
						position);
				activity.saveToCache(bitmap);
				return bitmap;
			}

			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			PhotoProcessingActivity activity = getActivity();
			if (activity != null) {
				activity.mBitmap = result;
				activity.mImageView.setImageBitmap(result);
				activity.hideProgressDialog();
			}
		}
	}

	private static class EditActionTask extends
			AsyncTask<Integer, Void, Bitmap> {
		WeakReference<PhotoProcessingActivity> mActivityRef;

		public EditActionTask(PhotoProcessingActivity activity) {
			mActivityRef = new WeakReference<PhotoProcessingActivity>(activity);
		}

		public void reattachActivity(PhotoProcessingActivity activity) {
			mActivityRef = new WeakReference<PhotoProcessingActivity>(activity);
			if (getStatus().equals(Status.RUNNING)) {
				activity.showFilterProgressDialog();
			}
		}

		private PhotoProcessingActivity getActivity() {
			if (mActivityRef == null) {
				return null;
			}

			return mActivityRef.get();
		}

		@Override
		protected void onPreExecute() {
			PhotoProcessingActivity activity = getActivity();
			if (activity != null) {
				activity.showTempPhotoInImageView();
			}
		}

		@Override
		protected Bitmap doInBackground(Integer... params) {
			PhotoProcessingActivity activity = getActivity();

			if (activity != null) {
				int position = params[0];
				Bitmap bitmap = PhotoProcessing.applyEditAction(
						activity.mBitmap, position);
				activity.saveToCache(bitmap);

				return bitmap;
			}

			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			PhotoProcessingActivity activity = getActivity();
			if (activity != null) {
				activity.mBitmap = result;
				activity.mImageView.setImageBitmap(result);
				activity.hideProgressDialog();
			}
		}
	}

	private static class SavePhotoTask extends AsyncTask<Void, Void, Void> {
		private WeakReference<PhotoProcessingActivity> mActivityRef;
		private String mSavePath;

		public SavePhotoTask(PhotoProcessingActivity activity) {
			mActivityRef = new WeakReference<PhotoProcessingActivity>(activity);
		}

		public void reattachActivity(PhotoProcessingActivity activity) {
			mActivityRef = new WeakReference<PhotoProcessingActivity>(activity);
			if (getStatus().equals(Status.RUNNING)) {
				activity.showSavingProgressDialog();
			}
		}

		private PhotoProcessingActivity getActivity() {
			if (mActivityRef == null) {
				return null;
			}

			return mActivityRef.get();
		}

		@Override
		protected void onPreExecute() {
			PhotoProcessingActivity activity = getActivity();
			if (activity != null) {
				activity.showSavingProgressDialog();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			PhotoProcessingActivity activity = getActivity();
			if (activity != null) {
				File jpegFile = new File(activity.mOriginalPhotoPath);
				// try {
				// byte[] jpegData =
				// FileUtils.readFileToByteArray(jpegFile);
				// PhotoProcessing.nativeLoadResizedJpegBitmap(jpegData,
				// jpegData.length, 1024 * 1024 * 2);
				// Bitmap bitmap = PhotoProcessing.filterPhoto(
				// activity.mBitmap, activity.mCurrentFilter);
				// int angle = MediaUtils
				// .getExifOrientation(activity.mOriginalPhotoPath);
				// bitmap = PhotoProcessing.rotate(bitmap, angle);
				// for (Integer editAction : activity.mEditActions) {
				// bitmap = PhotoProcessing.applyEditAction(bitmap,
				// editAction);
				// }
				mSavePath = activity.savePhoto(activity.mBitmap);
				// } catch (IOException e) {
				// Log.w(TAG, e);
				// }
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			PhotoProcessingActivity activity = getActivity();
			if (activity != null) {
				activity.hideProgressDialog();
				Toast.makeText(
						activity,
						activity.getString(R.string.saved_photo_toast_message,
								mSavePath), Toast.LENGTH_LONG).show();
			}
		}
	}

	class GalleryAdapter extends BaseAdapter {
		int width = getResources().getDisplayMetrics().widthPixels / 5;

		@Override
		public int getCount() {
			return samples.length;
		}

		@Override
		public Object getItem(int position) {
			return samples[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.item_filter, null);
				holder = new ViewHolder();
				holder.ivSample = (ImageView) convertView
						.findViewById(R.id.item_iv);
				holder.tvName = (TextView) convertView
						.findViewById(R.id.item_tv);
				convertView.setTag(holder);

				convertView.setLayoutParams(new Gallery.LayoutParams(width,
						Gallery.LayoutParams.WRAP_CONTENT));
				convertView.setBackgroundResource(R.drawable.item_selector);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.ivSample.setImageResource(getResource(samples[position]));
			holder.tvName
					.setText(getString(PhotoProcessing.FILTERS[position + 1]));
			return convertView;
		}

		class ViewHolder {
			ImageView ivSample;
			TextView tvName;
		}

		public int getResource(String imageName) {
			Context ctx = getBaseContext();
			int resId = getResources().getIdentifier(imageName, "drawable",
					ctx.getPackageName());
			return resId;
		}
	}

}