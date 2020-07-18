package com.example.langs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.text.Transliterator;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class Numbers extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompleteionListner = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){
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
            words.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
            words.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
            words.add(new Word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
            words.add(new Word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
            words.add(new Word("Five", "Massokka", R.drawable.number_five, R.raw.number_five));
            words.add(new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
            words.add(new Word("Seven", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
            words.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
            words.add(new Word("Nine", "Wo'e", R.drawable.number_nine, R.raw.number_nine));
            words.add(new Word("Ten", "Na'aacha", R.drawable.number_ten, R.raw.number_ten));


            WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Word word = words.get(position);
                    releaseMediaPlayer();
                    int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                        mMediaPlayer = MediaPlayer.create(Numbers.this, word.getAudioId());
                        mMediaPlayer.start();
                        mMediaPlayer.setOnCompletionListener(mCompleteionListner);
                    }
                }
            });
        }

        @Override
        protected void onStop() {
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

