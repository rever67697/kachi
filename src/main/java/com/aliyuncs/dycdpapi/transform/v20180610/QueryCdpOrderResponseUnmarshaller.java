/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aliyuncs.dycdpapi.transform.v20180610;

import com.aliyuncs.dycdpapi.model.v20180610.QueryCdpOrderResponse;
import com.aliyuncs.dycdpapi.model.v20180610.QueryCdpOrderResponse.Data;
import com.aliyuncs.transform.UnmarshallerContext;


public class QueryCdpOrderResponseUnmarshaller {

	public static QueryCdpOrderResponse unmarshall(QueryCdpOrderResponse queryCdpOrderResponse, UnmarshallerContext context) {
		
		queryCdpOrderResponse.setRequestId(context.stringValue("QueryCdpOrderResponse.RequestId"));
		queryCdpOrderResponse.setCode(context.stringValue("QueryCdpOrderResponse.Code"));
		queryCdpOrderResponse.setMessage(context.stringValue("QueryCdpOrderResponse.Message"));

		Data data = new Data();
		data.setResultCode(context.stringValue("QueryCdpOrderResponse.Data.ResultCode"));
		data.setResultMsg(context.stringValue("QueryCdpOrderResponse.Data.ResultMsg"));
		data.setExtendParam(context.stringValue("QueryCdpOrderResponse.Data.ExtendParam"));
		queryCdpOrderResponse.setData(data);
	 
	 	return queryCdpOrderResponse;
	}
}