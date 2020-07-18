package com.example.langs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class FamilyMembers extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompleteionListner = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
        private AudioManager mAudioManager;
        AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    mMediaPlayer.pause();
                    ;
                    mMediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    mMediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    releaseMediaPlayer();
                }
            }
        };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Father", "әpә", R.drawable.family_father,R.raw.family_father));
        words.add(new Word("Mother", "әṭa", R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("Son", "Angsi", R.drawable.family_son,R.raw.family_son));
        words.add(new Word("Daughter", "Tune", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("Elder Brother", "Taachi", R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("Younger Brother", "Chalitti", R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("Elder Sister", "teṭe", R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("Younger Sister", "Kolliti", R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("GrandMother", "Ama", R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("GrandFather", "Paapa", R.drawable.family_grandfather,R.raw.family_grandfather));


        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    mMediaPlayer = MediaPlayer.create(FamilyMembers.this, word.getAudioId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompleteionListner);
                }

            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}




