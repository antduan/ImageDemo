package com.tomyzhou.imagedemo;

import com.tomyzhou.imagedemo.util.ImageUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.widget.ImageView;

public class ImageDemoActivity extends Activity {
    /** Called when the activity is first created. */
	private ImageView mImageView01; ;
	private ImageView mImageView02
	;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupViews();
    }
    
    public void setupViews(){
    	mImageView01 = (ImageView) findViewById(R.id.image01);
    	mImageView02 = (ImageView)findViewById(R.id.image02);
    	
    	//��ȡ��ֽ����ֵ�� Drawable
    	Drawable drawable = getWallpaper();
    	Bitmap bitmap = ImageUtil.drawableToBitmap(drawable);
    	//��ȡ����ͼƬ
    	Bitmap zoomBitmap = ImageUtil.zoomBitmap(bitmap,300,250);
    	//��ȡԲ��ͼƬ
    	Bitmap roundbitBitmap = ImageUtil.getRoundedCornerBitmap(zoomBitmap, 10.0f);
    	//��ȡ��ӰͼƬ(��ԭͼƬ)
    	Bitmap reflectionBitmap = ImageUtil.createReflectionImageWithOrigin(roundbitBitmap);
    	
    	mImageView01.setImageBitmap(roundbitBitmap);
    	mImageView02.setImageBitmap(reflectionBitmap);
    	
    }
}