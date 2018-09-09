package com.farubaba.mobile.base.http.protocol;

import com.farubaba.mobile.base.http.model.IModel;
import com.farubaba.mobile.server.model.ErrorResult;

public interface IModelResultCallback<M extends IModel> extends BaseCallback {
	public void onSuccess(M result);
	public void onFailure(ErrorResult result);
}
