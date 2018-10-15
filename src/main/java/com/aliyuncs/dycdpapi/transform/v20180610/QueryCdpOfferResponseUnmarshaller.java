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

import com.aliyuncs.dycdpapi.model.v20180610.QueryCdpOfferResponse;
import com.aliyuncs.dycdpapi.model.v20180610.QueryCdpOfferResponse.FlowOffer;
import com.aliyuncs.transform.UnmarshallerContext;

import java.util.ArrayList;
import java.util.List;


public class QueryCdpOfferResponseUnmarshaller {

	public static QueryCdpOfferResponse unmarshall(QueryCdpOfferResponse queryCdpOfferResponse, UnmarshallerContext context) {
		
		queryCdpOfferResponse.setRequestId(context.stringValue("QueryCdpOfferResponse.RequestId"));
		queryCdpOfferResponse.setCode(context.stringValue("QueryCdpOfferResponse.Code"));
		queryCdpOfferResponse.setMessage(context.stringValue("QueryCdpOfferResponse.Message"));

		List<FlowOffer> flowOffers = new ArrayList<FlowOffer>();
		for (int i = 0; i < context.lengthValue("QueryCdpOfferResponse.FlowOffers.Length"); i++) {
			FlowOffer flowOffer = new FlowOffer();
			flowOffer.setVendor(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].Vendor"));
			flowOffer.setChannelType(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].ChannelType"));
			flowOffer.setProvince(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].Province"));
			flowOffer.setGrade(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].Grade"));
			flowOffer.setFlowType(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].FlowType"));
			flowOffer.setUseEff(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].UseEff"));
			flowOffer.setUseLimit(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].UseLimit"));
			flowOffer.setUseScene(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].UseScene"));
			flowOffer.setRight(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].Right"));
			flowOffer.setPrice(context.longValue("QueryCdpOfferResponse.FlowOffers["+ i +"].Price"));
			flowOffer.setDiscount(context.stringValue("QueryCdpOfferResponse.FlowOffers["+ i +"].Discount"));
			flowOffer.setOfferId(context.longValue("QueryCdpOfferResponse.FlowOffers["+ i +"].OfferId"));

			flowOffers.add(flowOffer);
		}
		queryCdpOfferResponse.setFlowOffers(flowOffers);
	 
	 	return queryCdpOfferResponse;
	}
}