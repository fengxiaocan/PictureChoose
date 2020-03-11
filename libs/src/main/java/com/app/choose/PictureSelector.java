package com.app.choose;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.luck.picture.lib.PictureExternalPreviewActivity;
import com.luck.picture.lib.PicturePlayAudioActivity;
import com.luck.picture.lib.PictureVideoPlayActivity;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.style.PictureParameterStyle;
import com.luck.picture.lib.tools.DoubleUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PictureSelector{
    Context context;

    private PictureSelector(Context context){
        this.context = context;
    }

    /**
     * Start PictureSelector for Activity.
     *
     * @return PictureSelector instance.
     */
    public static PictureSelector with(Context Context){
        return new PictureSelector(Context);
    }


    /**
     * @param chooseMode Select the type of picture you want，all or Picture or Video .
     * @return LocalMedia PictureSelectionModel
     */
    public PictureSelectionModel openGallery(int chooseMode){
        return new PictureSelectionModel(this,chooseMode);
    }

    /**
     * @param chooseMode Select the type of picture you want，Picture or Video.
     * @return LocalMedia PictureSelectionModel
     */
    public PictureSelectionModel openCamera(int chooseMode){
        return new PictureSelectionModel(this,chooseMode,true);
    }

    /**
     * 外部预览时设置样式
     *
     * @param themeStyle
     * @return
     */
    public PictureSelectionModel themeStyle(int themeStyle){
        return new PictureSelectionModel(this,PictureMimeType.ofImage()).theme(themeStyle);
    }

    /**
     * 外部预览时动态代码设置样式
     *
     * @param style
     * @return
     */
    public PictureSelectionModel setPictureStyle(PictureParameterStyle style){
        return new PictureSelectionModel(this,PictureMimeType.ofImage()).setPictureStyle(style);
    }

    /**
     * @param data
     * @return Selector Multiple LocalMedia
     */
    public static List<LocalMedia> obtainMultipleResult(Intent data){
        if(data != null){
            List<LocalMedia> result = data.getParcelableArrayListExtra(PictureConfig.EXTRA_RESULT_SELECTION);
            return result == null ? Collections.<LocalMedia>emptyList() : result;
        }
        return new ArrayList<>();
    }

    /**
     * @param data
     * @return Put image Intent Data
     */
    public static Intent putIntentResult(List<LocalMedia> data){
        return new Intent().putParcelableArrayListExtra(PictureConfig.EXTRA_RESULT_SELECTION,
                (ArrayList<? extends Parcelable>)data);
    }

    /**
     * @param bundle
     * @return get Selector  LocalMedia
     */
    public static List<LocalMedia> obtainSelectorList(Bundle bundle){
        if(bundle != null){
            List<LocalMedia> selectionMedias = bundle.getParcelableArrayList(PictureConfig.EXTRA_SELECT_LIST);
            return selectionMedias == null ? Collections.<LocalMedia>emptyList() : selectionMedias;
        }
        return new ArrayList<>();
    }

    /**
     * @param selectedImages
     * @return put Selector  LocalMedia
     */
    public static void saveSelectorList(Bundle outState,List<LocalMedia> selectedImages){
        outState.putParcelableArrayList(PictureConfig.EXTRA_SELECT_LIST,
                (ArrayList<? extends Parcelable>)selectedImages);
    }

    /**
     * set preview image
     *
     * @param position
     * @param medias
     */
    public void externalPicturePreview(int position,List<LocalMedia> medias,int enterAnimation){
        if(! DoubleUtils.isFastDoubleClick()){
            Intent intent = new Intent(context,PictureExternalPreviewActivity.class);
            intent.putParcelableArrayListExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST,
                    (ArrayList<? extends Parcelable>)medias);
            intent.putExtra(PictureConfig.EXTRA_POSITION,position);
            context.startActivity(intent);
            if(context instanceof Activity){
                ((Activity)context).overridePendingTransition(
                        enterAnimation != 0 ? enterAnimation : R.anim.picture_anim_enter,R.anim.picture_anim_fade_in);
            }
        }
    }

    /**
     * set preview image
     *
     * @param position
     * @param medias
     * @param directory_path
     */
    public void externalPicturePreview(int position,String directory_path,List<LocalMedia> medias,int enterAnimation){
        if(! DoubleUtils.isFastDoubleClick()){
            Intent intent = new Intent(context,PictureExternalPreviewActivity.class);
            intent.putParcelableArrayListExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST,
                    (ArrayList<? extends Parcelable>)medias);
            intent.putExtra(PictureConfig.EXTRA_POSITION,position);
            intent.putExtra(PictureConfig.EXTRA_DIRECTORY_PATH,directory_path);
            context.startActivity(intent);
            if(context instanceof Activity){
                ((Activity)context).overridePendingTransition(
                        enterAnimation != 0 ? enterAnimation : R.anim.picture_anim_enter,R.anim.picture_anim_fade_in);
            }
        }
    }

    /**
     * set preview video
     *
     * @param path
     */
    public void externalPictureVideo(String path){
        if(! DoubleUtils.isFastDoubleClick()){
            Intent intent = new Intent(context,PictureVideoPlayActivity.class);
            intent.putExtra(PictureConfig.EXTRA_VIDEO_PATH,path);
            intent.putExtra(PictureConfig.EXTRA_PREVIEW_VIDEO,true);
            context.startActivity(intent);
        }
    }

    /**
     * set preview audio
     *
     * @param path
     */
    public void externalPictureAudio(String path){
        if(! DoubleUtils.isFastDoubleClick()){
            Intent intent = new Intent(context,PicturePlayAudioActivity.class);
            intent.putExtra(PictureConfig.EXTRA_AUDIO_PATH,path);
            context.startActivity(intent);
            if(context instanceof Activity)
                ((Activity)context).overridePendingTransition(R.anim.picture_anim_enter,0);
        }
    }

}
