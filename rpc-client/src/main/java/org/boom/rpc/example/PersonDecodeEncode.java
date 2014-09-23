package org.boom.rpc.example;

import com.google.protobuf.InvalidProtocolBufferException;
import org.boom.rpc.common.edecode.ParamEncodeDecode;
import org.boom.rpc.share.domain.RPCObject;

/**
 * 解析person.proto定义的PB对象,该对象的Java实现在 RPCObject中
 * 
 * @author Administrator
 * 
 */
class PersonDecodeEncode implements ParamEncodeDecode {

	@Override
	public byte[] encode(Object obj) {
		RPCObject.Person person = (RPCObject.Person) obj;
		return person.toByteArray();
	}

	@Override
	public Object decode(byte[] bytes) {
		try {
			return RPCObject.Person.parseFrom(bytes);
		} catch (InvalidProtocolBufferException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getTypeName() {
		return RPCObject.Person.class.getName();
	}
}
