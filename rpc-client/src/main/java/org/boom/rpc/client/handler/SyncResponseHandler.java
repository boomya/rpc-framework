package org.boom.rpc.client.handler;

import org.boom.rpc.common.edecode.DecodeEnodeFactory;
import org.boom.rpc.common.edecode.ParamEncodeDecode;
import org.boom.rpc.example.JSONDecodeEncode;
import org.boom.rpc.protobuf.LesenRPCProto;
import org.boom.rpc.protobuf.LesenRPCProto.LesenRPCResponse;
import org.boom.rpc.protobuf.LesenRPCProto.LesenRPCResult;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SyncResponseHandler implements ResponseHandler {

    private final    Thread    requestThread;
    private volatile Object    result;
    private volatile Exception exception;

    private Class<?> returnType;

    private BlockingQueue<Object> results = new ArrayBlockingQueue<Object>(1);

    public SyncResponseHandler(Thread requestThread, Class<?> returnType) {
        this.requestThread = requestThread;
        this.returnType = returnType;
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

    @Override
    public void writeResult(LesenRPCResponse response) {
        System.out.println("-----writeResult-1");
        Object tmp;
        if (response.hasError()) {
            exception = createException(response);
            tmp = exception;
        } else {
            System.out.println("-----writeResult-2");
            LesenRPCResult rpcResult = response.getReault(0);
            System.out.println("-----writeResult-3");
            String clsName = rpcResult.getType();
            System.out.println("-----writeResult-4");
            DecodeEnodeFactory manager = DecodeEnodeFactory.getInstance();
            System.out.println("-----writeResult-5");
//            ParamEncodeDecode decode = manager.getEncodeDecode(clsName);
//            ParamEncodeDecode decode = manager.getEncodeDecode("JSON");
            System.out.println("-----writeResult-6");
//            result = decode.decode(rpcResult.getValue().toByteArray());
            JSONDecodeEncode decode = new JSONDecodeEncode();
            result = decode.decode(rpcResult.getValue().toByteArray(), returnType);
            System.out.println("-----writeResult-7");

            tmp = result;
        }
        results.add(tmp);
    }

    @Override
    public Object getResult() {
        System.out.println("-----blocking get result.");
        Object tmp;
        try {
            tmp = results.poll(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return e;
        }
        return tmp;
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
