import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;


public class UserVO implements Serializable {

	private int id;

	private String imei;

	private String channelid;

	private int sdk;

	private String cdate;

	private int xc_coo_id;

	private int coo_id;

	private String xmodel;

	private String xversion;

	private String ximsi;

	private int xinternet;

	private String xoperator;

	private int xcou;

	private int countryLevel;

	private int creator;

	private int sdkversion;

	private String xdate;

	private int scoo_id;

	private String ipaddr;

	private int sdkStyle;
	
	private String table;
	
	private String sdate;
	
	private int dataType;

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public UserVO() {
	}

	public int getCoo_id() {
		return coo_id;
	}

	public void setCoo_id(int coo_id) {
		this.coo_id = coo_id;
	}

	public int getSdkStyle() {
		return sdkStyle;
	}

	public void setSdkStyle(int sdkStyle) {
		this.sdkStyle = sdkStyle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public int getSdk() {
		return sdk;
	}

	public void setSdk(int sdk) {
		this.sdk = sdk;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getXc_coo_id() {
		return xc_coo_id;
	}

	public void setXc_coo_id(int xc_coo_id) {
		this.xc_coo_id = xc_coo_id;
	}

	public String getXmodel() {
		return xmodel;
	}

	public void setXmodel(String xmodel) {
		this.xmodel = xmodel;
	}

	public String getXversion() {
		return xversion;
	}

	public void setXversion(String xversion) {
		this.xversion = xversion;
	}

	public String getXimsi() {
		return ximsi;
	}

	public void setXimsi(String ximsi) {
		this.ximsi = ximsi;
	}

	public int getXinternet() {
		return xinternet;
	}

	public void setXinternet(int xinternet) {
		this.xinternet = xinternet;
	}

	public String getXoperator() {
		return xoperator;
	}

	public void setXoperator(String xoperator) {
		this.xoperator = xoperator;
	}

	public int getXcou() {
		return xcou;
	}

	public void setXcou(int xcou) {
		this.xcou = xcou;
	}

	public int getCountryLevel() {
		return countryLevel;
	}

	public void setCountryLevel(int countryLevel) {
		this.countryLevel = countryLevel;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getSdkversion() {
		return sdkversion;
	}

	public void setSdkversion(int sdkversion) {
		this.sdkversion = sdkversion;
	}

	public String getXdate() {
		return xdate;
	}

	public void setXdate(String xdate) {
		this.xdate = xdate;
	}

	public int getScoo_id() {
		return scoo_id;
	}

	public void setScoo_id(int scoo_id) {
		this.scoo_id = scoo_id;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	
	

//	@Override
//	public UserVO read(Kryo kryo, Input arg1, Class<UserVO> arg2) {
//
//		return kryo.readObject(arg1, arg2);
//	}
//
//	@Override
//	public void write(Kryo kryo, Output arg1, UserVO arg2) {
//		kryo.writeClassAndObject(arg1, arg2);
//	}
//
//	public static void main(String[] args) {
//		long start = System.currentTimeMillis();
//		try {
//			setSerializableObject();
//		} catch (FileNotFoundException e) {
//			
//		}
//		System.out.println("Kryo Serializable writeObject time:" + (System.currentTimeMillis() - start) + " ms");
//		start = System.currentTimeMillis();
//		getSerializableObject();
//		System.out.println("Kryo Serializable readObject time:" + (System.currentTimeMillis() - start) + " ms");
//	}
//
//	public static void setSerializableObject() throws FileNotFoundException {
//
//		Kryo kryo = new Kryo();
//
//		kryo.setReferences(false);
//
//		kryo.setRegistrationRequired(false);
//
//		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
//
//		kryo.register(UserVO.class);
//
//		Output output = new Output(new FileOutputStream("file.bin"));
//		UserVO userVO = new UserVO();
//		for (int i = 0; i < 10; i++) {
//			userVO.setId(i);
//			userVO.setCoo_id(i + 10000);
//			kryo.writeObject(output, userVO);
//		}
//
//		output.flush();
//		output.close();
//	}
//
//	public static void getSerializableObject() {
//		Kryo kryo = new Kryo();
//
//		kryo.setReferences(false);
//
//		kryo.setRegistrationRequired(false);
//
//		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
//
//		Input input;
//		try {
//			input = new Input(new FileInputStream("file.bin"));
//			UserVO userVO = null;
//			while ((userVO = kryo.readObject(input, UserVO.class)) != null) {
//				System.out.println(userVO.getId() + " pid" + userVO.getCoo_id());
//			}
//			input.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (KryoException e) {
//
//		}
//	}

}
