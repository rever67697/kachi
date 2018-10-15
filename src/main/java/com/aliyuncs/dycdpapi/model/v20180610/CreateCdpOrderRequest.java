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

import com.aliyuncs.RpcAcsRequest;

/**
 * @author auto create
 * @version 
 */
public class CreateCdpOrderRequest extends RpcAcsRequest<CreateCdpOrderResponse> {
	
	public CreateCdpOrderRequest() {
		super("Dycdpapi", "2018-06-10", "CreateCdpOrder");
	}

	private String reason;

	private Long resourceOwnerId;

	private Long maxPrice;

	private String resourceOwnerAccount;

	private Long offerId;

	private String phoneNumber;

	private String paramList;

	private Long ownerId;

	private String outOrderId;

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
		if(reason != null){
			putQueryParameter("Reason", reason);
		}
	}

	public Long getResourceOwnerId() {
		return this.resourceOwnerId;
	}

	public void setResourceOwnerId(Long resourceOwnerId) {
		this.resourceOwnerId = resourceOwnerId;
		if(resourceOwnerId != null){
			putQueryParameter("ResourceOwnerId", resourceOwnerId.toString());
		}
	}

	public Long getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
		if(maxPrice != null){
			putQueryParameter("MaxPrice", maxPrice.toString());
		}
	}

	public String getResourceOwnerAccount() {
		return this.resourceOwnerAccount;
	}

	public void setResourceOwnerAccount(String resourceOwnerAccount) {
		this.resourceOwnerAccount = resourceOwnerAccount;
		if(resourceOwnerAccount != null){
			putQueryParameter("ResourceOwnerAccount", resourceOwnerAccount);
		}
	}

	public Long getOfferId() {
		return this.offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
		if(offerId != null){
			putQueryParameter("OfferId", offerId.toString());
		}
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		if(phoneNumber != null){
			putQueryParameter("PhoneNumber", phoneNumber);
		}
	}

	public String getParamList() {
		return this.paramList;
	}

	public void setParamList(String paramList) {
		this.paramList = paramList;
		if(paramList != null){
			putQueryParameter("ParamList", paramList);
		}
	}

	public Long getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
		if(ownerId != null){
			putQueryParameter("OwnerId", ownerId.toString());
		}
	}

	public String getOutOrderId() {
		return this.outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
		if(outOrderId != null){
			putQueryParameter("OutOrderId", outOrderId);
		}
	}

	@Override
	public Class<CreateCdpOrderResponse> getResponseClass() {
		return CreateCdpOrderResponse.class;
	}

}
