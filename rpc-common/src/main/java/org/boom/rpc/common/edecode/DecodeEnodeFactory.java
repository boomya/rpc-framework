package org.boom.rpc.common.edecode;

import org.boom.rpc.common.message.LesenRPCProto;

import java.util.HashMap;
import java.util.Map;

public class DecodeEnodeFactory {

	private Map<String, ParamEncodeDecode> decodeEncodes;

	private static final DecodeEnodeFactory INSTANCE = new DecodeEnodeFactory();

	public static DecodeEnodeFactory getInstance() {
		return INSTANCE;
	}

	private DecodeEnodeFactory() {
		decodeEncodes = new HashMap<String, ParamEncodeDecode>();
		registDefaultHandler();
	}

	public void registDecodeEncode(ParamEncodeDecode encodeDecode) {
		decodeEncodes.put(encodeDecode.getTypeName(), encodeDecode);
	}

	public ParamEncodeDecode getEncodeDecode(Object obj) {
		return getEncodeDecode(obj.getClass().getName());
	}

    public ParamEncodeDecode getEncodeDecodeByName(String name) {
        return getEncodeDecode(name);
    }

	public ParamEncodeDecode getEncodeDecode(String clsName) {
		String objClsName = convertPrimitTypeToObjType(clsName);
		ParamEncodeDecode objectEDer = decodeEncodes.get(objClsName);
		return objectEDer;
	}

	private String convertPrimitTypeToObjType(String clsName) {
		if (clsName.equals("int") || ("java.lang.Integer").equals(clsName))
			return Integer.class.getName();
		if (clsName.equals("long"))
			return Long.class.getName();
		if (clsName.equals("char"))
			return Character.class.getName();
		if (clsName.equals("double"))
			return Double.class.getName();
		if (clsName.equals("float"))
			return Float.class.getName();
		if (clsName.equals("byte"))
			return Byte.class.getName();
		if (clsName.equals("short"))
			return Short.class.getName();
        if ("java.lang.String".equals(clsName)){
            return String.class.getName();
        }
//		return clsName;
        return "JSON";
	}

	public Object parser(LesenRPCProto.LesenRPCResult result) {
		String type = result.getType();
		ParamEncodeDecode objectEncodeDecode = decodeEncodes.get(type);
		byte[] byteArray = result.getValue().toByteArray();
		return objectEncodeDecode.decode(byteArray);
	}

	private void registDefaultHandler() {

		ByteEncodeDeocde byteED = new ByteEncodeDeocde();
		CharEncodeDeocde charED = new CharEncodeDeocde();
		ShortEncodeDeocde shortED = new ShortEncodeDeocde();
		LongEncodeDeocde longED = new LongEncodeDeocde();
		IntegerEncodeDeocde integerED = new IntegerEncodeDeocde();
		DoubleEncodeDeocde doubleED = new DoubleEncodeDeocde();
		FloatEncodeDeocde floatEd = new FloatEncodeDeocde();
		StringEncodeDeocde stringED = new StringEncodeDeocde();

		registDecodeEncode(byteED);
		registDecodeEncode(charED);
		registDecodeEncode(shortED);
		registDecodeEncode(integerED);
		registDecodeEncode(doubleED);
		registDecodeEncode(longED);
		registDecodeEncode(floatEd);
		registDecodeEncode(stringED);
	}

	public void clear() {
		decodeEncodes.clear();
	}
}
