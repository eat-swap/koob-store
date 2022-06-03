package org.eatswap.koobstore.base

import android.app.Activity

class ActivityCollector {
	companion object {
		var activities : MutableList<Activity> = mutableListOf()

		fun addActivity(activity: Activity) {
			activities.add(activity);
		}

		fun removeActivity(activity: Activity) {
			activities.remove(activity);
		}

		fun finishAll() {
			for (activity in activities) {
				activity.finish();
			}
			activities.clear();
		}
	}
}