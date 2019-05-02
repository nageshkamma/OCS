package com.OCS.utilities;

import com.OCS.accelerators.TestEngine;
import com.OCS.support.ConfiguratorSupport;
import com.OCS.support.ReportStampSupport;

public class Reporter extends TestEngine {
	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");
	static String timeStamp = ReportStampSupport.timeStamp().replace(":", "_")
			.replace(".", "_");

	public void reportCreater(String browser) throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));

		switch (intReporterType) {
		case 1:

			break;
		case 2:
 
			htmlCreateReport();
			//HtmlReportSupport.createDetailedReport();

			break;
		default:

			htmlCreateReport();
			break;
		}
	}

	
}
