package com.example.langs;

public class Word {
    private String mDefaultTranslation;
    private String mLangsTranslation;
    private int mImageId = NO_IMAGE_PROVIDED;
    private int mAudioId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation,String langsTranslation,int audioId){
        mDefaultTranslation = defaultTranslation;
        mLangsTranslation = langsTranslation;
        mAudioId = audioId;
    }

    public Word(String defaultTranslation,String langsTranslation,int imageId,int audioId){
        mDefaultTranslation = defaultTranslation;
        mLangsTranslation = langsTranslation;
        mImageId = imageId;
        mAudioId = audioId;
    }


    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getLangsTranslation(){
        return mLangsTranslation;
    }
    public int getImageId(){return mImageId;}
    public int getAudioId(){return mAudioId;}
    public boolean hasImage(){
        return mImageId!= NO_IMAGE_PROVIDED;
    }
}
