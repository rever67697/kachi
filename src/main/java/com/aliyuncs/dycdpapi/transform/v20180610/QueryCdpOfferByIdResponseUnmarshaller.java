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

import com.aliyuncs.dycdpapi.model.v20180610.QueryCdpOfferByIdResponse;
import com.aliyuncs.dycdpapi.model.v20180610.QueryCdpOfferByIdResponse.FlowOffer;
import com.aliyuncs.transform.UnmarshallerContext;

import java.util.ArrayList;
import java.util.List;


public class QueryCdpOfferByIdResponseUnmarshaller {

	public static QueryCdpOfferByIdResponse unmarshall(QueryCdpOfferByIdResponse queryCdpOfferByIdResponse, UnmarshallerContext context) {
		
		queryCdpOfferByIdResponse.setRequestId(context.stringValue("QueryCdpOfferByIdResponse.RequestId"));
		queryCdpOfferByIdResponse.setCode(context.stringValue("QueryCdpOfferByIdResponse.Code"));
		queryCdpOfferByIdResponse.setMessage(context.stringValue("QueryCdpOfferByIdResponse.Message"));

		List<FlowOffer> flowOffers = new ArrayList<FlowOffer>();
		for (int i = 0; i < context.lengthValue("QueryCdpOfferByIdResponse.FlowOffers.Length"); i++) {
			FlowOffer flowOffer = new FlowOffer();
			flowOffer.setVendor(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].Vendor"));
			flowOffer.setChannelType(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].ChannelType"));
			flowOffer.setProvince(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].Province"));
			flowOffer.setGrade(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].Grade"));
			flowOffer.setFlowType(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].FlowType"));
			flowOffer.setUseEff(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].UseEff"));
			flowOffer.setUseLimit(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].UseLimit"));
			flowOffer.setUseScene(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].UseScene"));
			flowOffer.setRight(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].Right"));
			flowOffer.setPrice(context.longValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].Price"));
			flowOffer.setDiscount(context.stringValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].Discount"));
			flowOffer.setOfferId(context.longValue("QueryCdpOfferByIdResponse.FlowOffers["+ i +"].OfferId"));

			flowOffers.add(flowOffer);
		}
		queryCdpOfferByIdResponse.setFlowOffers(flowOffers);
	 
	 	return queryCdpOfferByIdResponse;
	}
}