/**
 * Log.java
 * com.jerome.base
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-5-31 		Jerome Song
 *
 * Copyright (c) 2013, JEROME All Rights Reserved.
 */

package com.jerome.base;

/**
 * ClassName:Log
 * 
 * @author Jerome Song
 * @version
 * @Date 2013-5-31 下午9:23:29
 * 
 * @see 对android.util.Log的封装，可以根据Level控制log的输出。<br>
 *      默认级别VERBOSE,打印所有,建议用Log.d Log.i<br>
 *      软件发布时调整级别为SUPPRESS
 * 
 */
public final class Log {
	/**
	 * Priority constant for the println method; use Log.v.
	 */
	public static final int VERBOSE = android.util.Log.VERBOSE;

	/**
	 * Priority constant for the println method; use Log.d.
	 */
	public static final int DEBUG = android.util.Log.DEBUG;

	/**
	 * Priority constant for the println method; use Log.i.
	 */
	public static final int INFO = android.util.Log.INFO;

	/**
	 * Priority constant for the println method; use Log.w.
	 */
	public static final int WARN = android.util.Log.WARN;

	/**
	 * Priority constant for the println method; use Log.e.
	 */
	public static final int ERROR = android.util.Log.ERROR;

	/**
	 * Priority constant for the println method.
	 */
	public static final int ASSERT = android.util.Log.ASSERT;

	public static final int SUPPRESS = ASSERT + 1;

	private static int LOG_LEVEL = VERBOSE;

	private Log() {
	}

	public static void setLogLevel(int log_level) {
		LOG_LEVEL = log_level;
	}

	/**
	 * Send a {@link #VERBOSE} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int v(String tag, String msg) {
		if (LOG_LEVEL <= VERBOSE) {
			return android.util.Log.v(tag, msg);
		}
		return 0;
	}

	/**
	 * Send a {@link #VERBOSE} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int v(String tag, String msg, Throwable tr) {
		if (LOG_LEVEL <= VERBOSE) {
			return android.util.Log.v(tag, msg, tr);
		}
		return 0;
	}

	/**
	 * Send a {@link #DEBUG} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int d(String tag, String msg) {
		if (LOG_LEVEL <= DEBUG) {
			return android.util.Log.d(tag, msg);
		}
		return 0;
	}

	/**
	 * Send a {@link #DEBUG} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int d(String tag, String msg, Throwable tr) {
		if (LOG_LEVEL <= DEBUG) {
			return android.util.Log.d(tag, msg, tr);
		}
		return 0;
	}

	/**
	 * Send an {@link #INFO} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int i(String tag, String msg) {
		if (LOG_LEVEL <= INFO) {
			return android.util.Log.i(tag, msg);
		}
		return 0;
	}

	/**
	 * Send a {@link #INFO} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int i(String tag, String msg, Throwable tr) {
		if (LOG_LEVEL <= INFO) {
			return android.util.Log.w(tag, msg, tr);
		}
		return 0;
	}

	/**
	 * Send a {@link #WARN} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int w(String tag, String msg) {
		if (LOG_LEVEL <= WARN) {
			return android.util.Log.w(tag, msg);
		}
		return 0;
	}

	/**
	 * Send a {@link #WARN} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int w(String tag, String msg, Throwable tr) {
		if (LOG_LEVEL <= WARN) {
			return android.util.Log.w(tag, msg, tr);
		}
		return 0;
	}

	/**
	 * Checks to see whether or not a log for the specified tag is loggable at
	 * the specified level.
	 * 
	 * The default level of any tag is set to INFO. This means that any level
	 * above and including INFO will be logged. Before you make any calls to a
	 * logging method you should check to see if your tag should be logged. You
	 * can change the default level by setting a system property: 'setprop
	 * log.tag.&lt;YOUR_LOG_TAG> &lt;LEVEL>' Where level is either VERBOSE,
	 * DEBUG, INFO, WARN, ERROR, ASSERT, or SUPPRESS. SUPRESS will turn off all
	 * logging for your tag. You can also create a local.prop file that with the
	 * following in it: 'log.tag.&lt;YOUR_LOG_TAG>=&lt;LEVEL>' and place that in
	 * /data/local.prop.
	 * 
	 * @param tag
	 *            The tag to check.
	 * @param level
	 *            The level to check.
	 * @return Whether or not that this is allowed to be logged.
	 * @throws IllegalArgumentException
	 *             is thrown if the tag.length() > 23.
	 */
	public static boolean isLoggable(String tag, int level) {
		return android.util.Log.isLoggable(tag, level);
	}

	/**
	 * Send a {@link #WARN} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param tr
	 *            An exception to log
	 */
	public static int w(String tag, Throwable tr) {
		if (LOG_LEVEL <= WARN) {
			return android.util.Log.w(tag, tr);
		}
		return 0;
	}

	/**
	 * Send an {@link #ERROR} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int e(String tag, String msg) {
		if (LOG_LEVEL <= ERROR) {
			return android.util.Log.e(tag, msg);
		}
		return 0;
	}

	/**
	 * Send a {@link #ERROR} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int e(String tag, String msg, Throwable tr) {
		if (LOG_LEVEL <= ERROR) {
			return android.util.Log.e(tag, msg, tr);
		}
		return 0;
	}

	/**
	 * Handy function to get a loggable stack trace from a Throwable
	 * 
	 * @param tr
	 *            An exception to log
	 */
	public static String getStackTraceString(Throwable tr) {
		return android.util.Log.getStackTraceString(tr);
	}

	/**
	 * Low-level logging call.
	 * 
	 * @param priority
	 *            The priority/type of this log message
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @return The number of bytes written.
	 */
	public static int println(int priority, String tag, String msg) {
		return android.util.Log.println(priority, tag, msg);
	}
}
