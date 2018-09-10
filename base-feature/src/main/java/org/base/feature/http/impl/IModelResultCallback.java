package org.base.feature.http.impl;

import org.root.feature.interf.IModel;
import org.root.feature.interf.impl.ErrorResult;

public interface IModelResultCallback<M extends IModel> extends BaseCallback {
	public void onSuccess(M result);
	public void onFailure(ErrorResult result);
}
