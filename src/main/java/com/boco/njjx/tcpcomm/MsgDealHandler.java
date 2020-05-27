package com.boco.njjx.tcpcomm;

import com.boco.njjx.tcpcomm.IMessageListener;
import com.boco.njjx.model.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MsgDealHandler extends SimpleChannelInboundHandler<Response> {
	private IMessageListener messageListener;
	
	public MsgDealHandler(IMessageListener messageListener) {
		super();
		this.messageListener = messageListener;
	}

	//接收消息
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Response msg)
			throws Exception {		
		messageListener.onMessageRecv(msg);
	}
	
	//断开连接时处理
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	

	
}
