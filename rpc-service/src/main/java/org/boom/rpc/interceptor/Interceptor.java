package org.boom.rpc.interceptor;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.boom.rpc.protobuf.LesenRPCProto.LesenRPCRequest;

public interface Interceptor {

	void onServiceStart(Channel channel);

	void onServiceStop(Channel channel);

	void beforeCall(ChannelHandlerContext context, LesenRPCRequest msg);

	void afterCall(ChannelHandlerContext context, LesenRPCRequest msg);
}
