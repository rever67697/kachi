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

package com.aliyuncs.dycdpapi.model.v20180610;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.dycdpapi.transform.v20180610.QueryCdpOfferByIdResponseUnmarshaller;
import com.aliyuncs.transform.UnmarshallerContext;

import java.util.List;

/**
 * @author auto create
 * @version 
 */
public class QueryCdpOfferByIdResponse extends AcsResponse {

	private String requestId;

	private String code;

	private String message;

	private List<FlowOffer> flowOffers;

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<FlowOffer> getFlowOffers() {
		return this.flowOffers;
	}

	public void setFlowOffers(List<FlowOffer> flowOffers) {
		this.flowOffers = flowOffers;
	}

	public static class FlowOffer {

		private String vendor;

		private String channelType;

		private String province;

		private String grade;

		private String flowType;

		private String useEff;

		private String useLimit;

		private String useScene;

		private String right;

		private Long price;

		private String discount;

		private Long offerId;

		public String getVendor() {
			return this.vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getChannelType() {
			return this.channelType;
		}

		public void setChannelType(String channelType) {
			this.channelType = channelType;
		}

		public String getProvince() {
			return this.province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getGrade() {
			return this.grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public String getFlowType() {
			return this.flowType;
		}

		public void setFlowType(String flowType) {
			this.flowType = flowType;
		}

		public String getUseEff() {
			return this.useEff;
		}

		public void setUseEff(String useEff) {
			this.useEff = useEff;
		}

		public String getUseLimit() {
			return this.useLimit;
		}

		public void setUseLimit(String useLimit) {
			this.useLimit = useLimit;
		}

		public String getUseScene() {
			return this.useScene;
		}

		public void setUseScene(String useScene) {
			this.useScene = useScene;
		}

		public String getRight() {
			return this.right;
		}

		public void setRight(String right) {
			this.right = right;
		}

		public Long getPrice() {
			return this.price;
		}

		public void setPrice(Long price) {
			this.price = price;
		}

		public String getDiscount() {
			return this.discount;
		}

		public void setDiscount(String discount) {
			this.discount = discount;
		}

		public Long getOfferId() {
			return this.offerId;
		}

		public void setOfferId(Long offerId) {
			this.offerId = offerId;
		}
	}

	@Override
	public QueryCdpOfferByIdResponse getInstance(UnmarshallerContext context) {
		return	QueryCdpOfferByIdResponseUnmarshaller.unmarshall(this, context);
	}
}
