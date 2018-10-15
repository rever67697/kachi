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

import com.aliyuncs.dycdpapi.model.v20180610.CreateCdpOrderResponse;
import com.aliyuncs.dycdpapi.model.v20180610.CreateCdpOrderResponse.Data;
import com.aliyuncs.transform.UnmarshallerContext;


public class CreateCdpOrderResponseUnmarshaller {

	public static CreateCdpOrderResponse unmarshall(CreateCdpOrderResponse createCdpOrderResponse, UnmarshallerContext context) {
		
		createCdpOrderResponse.setRequestId(context.stringValue("CreateCdpOrderResponse.RequestId"));
		createCdpOrderResponse.setCode(context.stringValue("CreateCdpOrderResponse.Code"));
		createCdpOrderResponse.setMessage(context.stringValue("CreateCdpOrderResponse.Message"));

		Data data = new Data();
		data.setResultCode(context.stringValue("CreateCdpOrderResponse.Data.ResultCode"));
		data.setResultMsg(context.stringValue("CreateCdpOrderResponse.Data.ResultMsg"));
		data.setOrderId(context.stringValue("CreateCdpOrderResponse.Data.OrderId"));
		data.setExtendParam(context.stringValue("CreateCdpOrderResponse.Data.ExtendParam"));
		createCdpOrderResponse.setData(data);
	 
	 	return createCdpOrderResponse;
	}
}