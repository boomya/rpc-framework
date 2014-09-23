package org.boom.rpc.client.handler;

import org.boom.rpc.protobuf.LesenRPCProto.LesenRPCResponse;

public interface ResponseHandler {

	void onResponse(LesenRPCResponse response);

	Object getReault();

	Exception getException();

}
