package com.moai101;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.moai101.data.DiaryContract;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private ImageView imageView ;
    private Uri CurrentUri;
    private static final int LOADER_ID= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Intent intent = getIntent();
        CurrentUri= intent.getData();
        imageView= (ImageView)findViewById(R.id.add_an_image);
        if(CurrentUri==null){
            setTitle("Add an image");
//            レイアウトを動的に変更するための処理
            invalidateOptionsMenu();
            imageView.setImageResource(R.mipmap.book);
        }
        else {
            setTitle("Edit this image");
            getLoaderManager().initLoader(LOADER_ID,null,this);
        }
    }
    //メニューバーのところのチェックボックスを準備する処理
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (CurrentUri == null) {
            MenuItem menuItem = menu.findItem(R.id.delte_image);
            menuItem.setVisible(false);
        }
        return true;
    }
    //メニューバーを作る処理
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_menu,menu);
        return true;
    }
    //上のバーの所の各ボタンは押された時にどのメソッドを呼び出すのかを決める処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.image_edit:
                if(CurrentUri==null){
                    addImage();
                }
                else {
                    imageEdit();
                }
                return true;
            case R.id.save_image:
                saveImage();
                return true;
            case R.id.delte_image:
                deleteImage();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveImage() {

        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap= drawable.getBitmap();
        byte[] byte_for_image = DbBitmapUtils.getBytes(bitmap);
        DetailActivity.setImagebyte(byte_for_image);
        Toast toast=Toast.makeText(this,"Image saved",Toast.LENGTH_SHORT);
        toast.show();

    }

    private void deleteImage() {
        imageView.setImageResource(R.mipmap.book);
    }

    private void imageEdit() {

        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
        builder.setTitle("Change Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
//                カメラ機能を使うかどうかのパーミッションを表示する処理
                boolean result=Utility.checkPermission(ImageActivity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
//                        カメラを起動する
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
//                        アルバムを起動する処理
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    //ImageEdit()と同じような処理
    private void addImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(ImageActivity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
//        ギャラリーから画像を選択するとき
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
//        カメラを起動して撮影した画像を取得するとき
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }
    //    写真を撮影するときの処理
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        取った写真をpng形式に捨処理
        thumbnail.compress(Bitmap.CompressFormat.PNG, 90, bytes);
//写真ファイルをどこの領域に保存するかを決める処理
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".png");

        FileOutputStream fo;
        try {
//            ファイルを作成して写真を保存する処理
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
//ギャラリーから写真を選んでセットするまでの処理
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageView.setImageBitmap(bm);
    }
    //    写真を実際に追加する処理
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projecton = {DiaryContract.DiaryEntry._ID, DiaryContract.DiaryEntry.COLUMN_IMAGE_DATA};
        return new CursorLoader(this,CurrentUri,projecton,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if(data.getCount()==0)
            return;

        data.moveToFirst();
        byte[] byte_image = data.getBlob(data.getColumnIndex(DiaryContract.DiaryEntry.COLUMN_IMAGE_DATA));
        Bitmap bitmap = DbBitmapUtils.getImage(byte_image);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
