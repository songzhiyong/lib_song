/**
 * GPSUtils.java
 * com.jerome.utils.device
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 Jan 10, 2014 		Jerome Song
 *
 * Copyright (c) 2014, JEROME All Rights Reserved.
 */

package com.jerome.utils.device;

import android.content.Context;
import android.location.LocationManager;

/**
 * ClassName:GPSUtils Function: TODO ADD FUNCTION
 * 
 * @author Jerome Song
 * @version
 * @Date Jan 10, 2014 11:05:57 PM
 * 
 * @see
 */
public class GPSUtils {
	public static final boolean isGPSOpen(Context context) {
		LocationManager alm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER))
			return true;
		return false;
	}
}
