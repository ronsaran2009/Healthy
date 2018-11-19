package com.example.lab203_28.healthy.Sleep;

import android.content.ContentValues;

public class Sleep {
    ContentValues _row = new ContentValues();
    ContentValues contentValues;

    String timeSleep = "00:00";
    String timeWake = "00:00";
    String date;
    String timeDiff = "00:00";

    public Sleep() {
    }

    public Sleep(String sleep, String wake, String date) {
        String[] _sleep = sleep.split(":");
        int _sleepHour = Integer.parseInt(_sleep[0]);
        int _sleepMin = Integer.parseInt(_sleep[1]);
        timeSleep = String.format("%02d:%02d", _sleepHour, _sleepMin);
        String[] _wake = wake.split(":");
        int _wakeHour = Integer.parseInt(_wake[0]);
        int _wakeMin = Integer.parseInt(_wake[1]);
        timeWake = String.format("%02d:%02d", _wakeHour, _wakeMin);
        this.date = date;

        setTimeDiff(sleep, wake);
    }

    //ContentValues
    public void setContent(String sleep, String wake, String date) {
        this._row.put("sleep", sleep);
        this._row.put("wake", wake);
        this._row.put("date", date);
    }

    public ContentValues getContent() {
        return _row;
    }

    public String getTimeSleep() {
        return timeSleep;
    }

    public void setTimeSleep(String _timeSleep) {
        this.timeSleep = _timeSleep;
    }

    public String getTimeWake() {
        return timeWake;
    }

    public void setTimeWake(String _timeWake) {
        this.timeWake = _timeWake;
    }

    public String getTimeDiff() {
        return timeDiff;
    }

    //Calculate bedtime
    public void setTimeDiff(String sleep, String wake) {
        int _hour = 0;
        int _min = 0;

        String[] _sleep = sleep.split(":");
        int _sleepHour = Integer.parseInt(_sleep[0]);
        int _sleepMin = Integer.parseInt(_sleep[1]);

        String[] _wake = wake.split(":");
        int _wakeHour = Integer.parseInt(_wake[0]);
        int _wakeMin = Integer.parseInt(_wake[1]);

        if (_sleepHour >= _wakeHour && _sleepMin >= _wakeMin) {
            _hour = 24 - (_sleepHour - _wakeHour);
        } else if (_sleepHour >= _wakeHour && _sleepHour < _wakeHour) {
            _hour = _wakeHour - _sleepHour;
        }
        if (_sleepHour < _wakeHour) {
            _hour = _wakeHour - _sleepHour;
        }
        if (_sleepMin > _wakeMin) {
            _min = 60 - (_sleepMin - _wakeMin);
            _hour -= 1;
        } else if (_sleepMin == _wakeMin) {
            _min = 0;

        } else {
            _min = _wakeMin - _sleepMin;
        }

        this.timeDiff = String.format("%02d:%02d", _hour, _min);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String _date) {
        this.date = _date;
    }


    public ContentValues getContentValues() {
        return contentValues;
    }

    public void setContentValues() {
        this.contentValues.put("_date", getDate());
        this.contentValues.put("_timeSleep", getTimeSleep());
        this.contentValues.put("_timeWakeup", getTimeWake());

    }
}
