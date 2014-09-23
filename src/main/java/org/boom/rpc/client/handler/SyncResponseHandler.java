package org.boom.rpc.client.handler;

import org.boom.rpc.common.edecode.DecodeEnodeFactory;
import org.boom.rpc.common.edecode.ParamEncodeDecode;
import org.boom.rpc.protobuf.LesenRPCProto.LesenRPCResponse;
import org.boom.rpc.protobuf.LesenRPCProto.LesenRPCResult;

public class SyncResponseHandler implements ResponseHandler {

	private final Thread requestThread;
	private volatile Object result;
	private volatile Exception exception;

	public SyncResponseHandler(Thread requestThread) {
		this.requestThread = requestThread;
	}

	@Override
	public Object getReault() {
		return result;
	}

	@Override
	public Exception getException() {
		return exception;
	}

	@Override
	public void onResponse(LesenRPCResponse response) {
		if (response.hasError()) {
			exception = createException(response);
		} else {
			LesenRPCResult rpcResult = response.getReault(0);
			String clsName = rpcResult.getType();
			DecodeEnodeFactory manager = DecodeEnodeFactory.getInstance();
			ParamEncodeDecode decode = manager.getEncodeDecode(clsName);
			result = decode.decode(rpcResult.getValue().toByteArray());
		}
		notifyRequestComplated();
	}

	private void notifyRequestComplated() {
		requestThread.interrupt();
	}

	private Exception createException(LesenRPCResponse response) {
		String errorMessage = response.getError();
		RuntimeException exception = new RuntimeException(errorMessage);
		return exception;
	}

}
