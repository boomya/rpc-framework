package org.boom.rpc.interceptor;

import org.boom.rpc.protobuf.LesenRPCProto.LesenRPCRequest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.net.SocketAddress;

public class DefaultInterceptor implements Interceptor {

	@Override
	public void onServiceStart(Channel channel) {
		System.err.println("service is running");
	}

	@Override
	public void onServiceStop(Channel channel) {
		System.err.println("service is close");
	}

	@Override
	public void beforeCall(ChannelHandlerContext context, LesenRPCRequest msg) {
		Channel channel = context.channel();
		SocketAddress remoteAddress = channel.remoteAddress();
		String client = remoteAddress.toString();
		String serviceName = msg.getServiceName();
		String methdoName = msg.getMethodName();
		String message = client + "-call-" + serviceName + "." + methdoName+ " start";
		System.err.println(message);
	}

	@Override
	public void afterCall(ChannelHandlerContext context, LesenRPCRequest msg) {
		Channel channel = context.channel();
		SocketAddress remoteAddress = channel.remoteAddress();
		String client = remoteAddress.toString();
		String serviceName = msg.getServiceName();
		String methdoName = msg.getMethodName();
		String message = client + "-call-" + serviceName + "." + methdoName+ " finished";
		System.err.println(message);
	}

}
