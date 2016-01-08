package com.cemgokmen.optimexam;

/**
 * Created by funstein on 08/01/16.
 */
public class ConflictingScheduleException extends Exception {
    public ConflictingScheduleException()
    {
    }

    public ConflictingScheduleException(String message)
    {
        super(message);
    }

    public ConflictingScheduleException(Throwable cause)
    {
        super(cause);
    }

    public ConflictingScheduleException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ConflictingScheduleException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
