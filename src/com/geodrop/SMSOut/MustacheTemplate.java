package com.geodrop.SMSOut;

/**
 * Mustache template used in the request
 * 
 * @author A-Tono s.r.l.
 * @since 1.0
 */
class MustacheTemplate 
{
	static final String SMS_Send_Estimatecost =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+	"<GEOSMS>"
		+		"<MESSAGE content=\"text\">"
		+			"<TEXT>"
		+				"<![CDATA[{{{message}}}]]>"
		+			"</TEXT>"
		+		"</MESSAGE>"
		+		"<LIST>"
		+			"{{#dest_msisdns}}<DEST msisdn=\"{{this}}\"/>{{/dest_msisdns}}"
		+		"</LIST>"
		+		"{{#tpoa}}<TPOA>{{{tpoa}}}</TPOA>{{/tpoa}}" //only in SMSSend
		+		"{{#deferred}}<DEFERRED>{{deferred}}</DEFERRED>{{/deferred}}"
		+ 		"{{#jobnotify}}<JOBNOTIFY>{{/jobnotify}}"
		+			"{{#job_notifyurl}}<NOTIFYURL>{{job_url}}</NOTIFYURL>{{/job_notifyurl}}"
		+		"{{#jobnotify}}</JOBNOTIFY>{{/jobnotify}}"
		+ 		"{{#dlrnotify}}<DLRNOTIFY>{{/dlrnotify}}"
		+			"{{#dlr_notifyurl}}<NOTIFYURL>{{dlr_url}}</NOTIFYURL>{{/dlr_notifyurl}}"
		+		"{{#dlrnotify}}</DLRNOTIFY>{{/dlrnotify}}"
		+		"{{#runrange}}<RUNRANGE><L_TIME>{{runrange_ltime}}</L_TIME><TIME_WINDOW>{{runrange_timewindow}}</TIME_WINDOW></RUNRANGE>{{/runrange}}"
		+	"</GEOSMS>";
		
	static final String SMS_Status = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
		+	"<GEOSMSSTATUS>"
		+		"{{#client_id}}<ID_CLIENT>{{{client_id}}}</ID_CLIENT>{{/client_id}}"	//optional
		+		"<REQUEST_TYPE>{{request_type}}</REQUEST_TYPE>"
		+		"{{#job}}<JOB orderid=\"{{job_orderid}}\" limit=\"{{job_limit}}\" />{{/job}}" //only in SMSStatusJob
		+		"{{#range}}<RANGE date_from=\"{{range_date_from}}\" date_to=\"{{range_date_to}}\" limit=\"{{range_limit}}\"/>{{/range}}" //only in SMSStatusRange
		+ 		"<LIST>"
		+			"{{#adhoc}}<DEST msisdn=\"{{msisdn}}\" orderid=\"{{orderid}}\"/>{{/adhoc}}" //only in SMSStatusAdhoc
		+ 		"</LIST>"
		+ 	"</GEOSMSSTATUS>";
	
	static final String SMS_Scheduled_Modify = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
		+	"<GEOSMSSCHEDULER>"
		+		"<JOB JOBID=\"{{job_id}}\" ACTION=\"{{action}}\" >"
		+			"{{#message_text}}<MESSAGETEXT><![CDATA[{{{message_text}}}]]></MESSAGETEXT>{{/message_text}}"
		+			"<LISTMSISDN>"
		+				"{{#msisdn_add}}<ADD msisdn=\"{{msisdn_add}}\" />{{/msisdn_add}}"
		+				"{{#msisdn_del}}<DEL msisdn=\"{{msisdn_del}}\" />{{/msisdn_del}}"
		+			"</LISTMSISDN>"
		+			"{{#tpoa}}<TPOA>{{{tpoa}}}</TPOA>{{/tpoa}}"
		+			"{{#deferred_time}}<DEFERREDTIME>{{deferred_time}}</DEFERREDTIME>{{/deferred_time}}"
		+		"</JOB>"
		+	"</GEOSMSSCHEDULER>";

	static final String SMS_Scheduled_Status_Delete = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
		+	"<GEOSMSSCHEDULER>"
		+		"<JOB JOBID=\"{{job_id}}\" ACTION=\"{{action}}\" />"
		+	"</GEOSMSSCHEDULER>";
	
	MustacheTemplate(){}
}
