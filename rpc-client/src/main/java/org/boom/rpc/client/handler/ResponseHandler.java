package org.boom.rpc.client.handler;

import org.boom.rpc.protobuf.LesenRPCProto.LesenRPCResponse;

public interface ResponseHandler {

	void onResponse(LesenRPCResponse response);

    void writeResult(LesenRPCResponse response);

    Object getResult();

	Object getReault();

	Exception getException();

}
