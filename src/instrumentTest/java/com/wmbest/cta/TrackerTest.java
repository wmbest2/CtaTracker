package com.wmbest.cta;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.wmbest.cta.TrackerTest \
 * com.wmbest.cta.tests/android.test.InstrumentationTestRunner
 */
public class TrackerTest extends ActivityInstrumentationTestCase2<Tracker> {

    public TrackerTest() {
        super("com.wmbest.cta", Tracker.class);
    }

}
