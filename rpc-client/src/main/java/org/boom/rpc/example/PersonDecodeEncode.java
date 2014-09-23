package org.boom.rpc.example;

import com.google.protobuf.InvalidProtocolBufferException;
import org.boom.rpc.common.edecode.ParamEncodeDecode;
import org.boom.rpc.share.domain.RPCObject;

/**
 * ����person.proto�����PB����,�ö����Javaʵ���� RPCObject��
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
