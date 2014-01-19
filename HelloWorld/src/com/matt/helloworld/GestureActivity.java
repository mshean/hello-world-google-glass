package com.matt.helloworld;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.isabel.helloworld.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;

/**
 * Based on Google Glass Sample - Compass and StopWatch
 * @author IsabelM
 * 
 */
public class GestureActivity extends Activity {
	private TextToSpeech mSpeech;
	private boolean mResumed;
	private GestureDetector mGestureDetector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mGestureDetector = createGestureDetector(this);
        
        mSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            	//do nothing
            }
        });
		
	}

    private GestureDetector createGestureDetector(Context context) {
    GestureDetector gestureDetector = new GestureDetector(context);
        //Create a base listener for generic gestures
        gestureDetector.setBaseListener( new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {
            	switch(gesture) {
					case LONG_PRESS:
						break;
					case SWIPE_DOWN:
						break;
					case SWIPE_LEFT:
						mSpeech.speak(getString(R.string.left), TextToSpeech.QUEUE_FLUSH, null);
	                    return true;
					case SWIPE_RIGHT:
						mSpeech.speak(getString(R.string.right), TextToSpeech.QUEUE_FLUSH, null);
	                    return true;
					case SWIPE_UP:
						mSpeech.speak(getString(R.string.up), TextToSpeech.QUEUE_FLUSH, null);
	                    return true;
					case TAP:
						break;
					case THREE_LONG_PRESS:
						break;
					case THREE_TAP:
						mSpeech.speak(getString(R.string.tap_thrice), TextToSpeech.QUEUE_FLUSH, null);
	                    return true;
					case TWO_LONG_PRESS:
						break;
					case TWO_SWIPE_DOWN:
						break;
					case TWO_SWIPE_LEFT:
						break;
					case TWO_SWIPE_RIGHT:
						break;
					case TWO_SWIPE_UP:
						break;
					case TWO_TAP:
						mSpeech.speak(getString(R.string.tap_twice), TextToSpeech.QUEUE_FLUSH, null);
	                    return true;
					default:
						break;
            	}

                return false;
            }
        });
        gestureDetector.setFingerListener(new GestureDetector.FingerListener() {
            @Override
            public void onFingerCountChanged(int previousCount, int currentCount) {
              // do something on finger count changes
            }
        });
        gestureDetector.setScrollListener(new GestureDetector.ScrollListener() {
            @Override
            public boolean onScroll(float displacement, float delta, float velocity) {
				return mResumed;
                // do something on scrolling
            }
        });
        return gestureDetector;
    }

    /*
     * Send generic motion events to the gesture detector
     */
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if (mGestureDetector != null) {
            return mGestureDetector.onMotionEvent(event);
        }
        return false;
    }
}
