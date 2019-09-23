import java.util.regex.*;
import net.sf.json.*;
import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception {
		
		List<Object> agencyList = new ArrayList<Object>();
        
		
		ArrayList<String> arrayList = readInput(args[0]);
		for(int i = 0;i < arrayList.size();i++) {
			String str = (String)arrayList.get(i);
			String num = getNum(str);
			Main ab = new Main();
			Object[] result = ab.deleteSubString(str,getNum(str));
			str = (String)result[0];
			String name = str.substring(2,str.indexOf(","));
			String add = str.substring(str.indexOf(",")+1,str.indexOf("."));
			List<String> list = new ArrayList<String>();
			
			if(str.charAt(0) == '1') {
				list = divideAddress1(add);
			}
			else  {
				list = divideAddress2(add);
				
			}
			System.out.println(list);
			List<String> a = new ArrayList<String>(); 
			
			Map<String, Object> agencyMap = new HashMap<>();
	        agencyMap.put("姓名",name);
	        agencyMap.put("手机", num);
	        agencyMap.put("地址", list);
	        agencyList.add(agencyMap);
	        
	        
		}
		JSONArray jsonObject = JSONArray.fromObject(agencyList);
        String jsonString1 = jsonObject.toString();
        CreateFileUtil.createJsonFile(jsonString1, args[1], "ans");
		
//		
//		String s = jsonRead();
//        JSONObject jsonObject = new JSONObject().fromObject(s);
		
		 

	}
	
	public static List<String> divideAddress1(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇|.+街道)?(?<village>.*)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<String> list=new ArrayList<String>();
        
        while(m.find()){
            province=m.group("province");
            list.add( province==null?"":province.trim());
            city=m.group("city");
            list.add( city==null?"":city.trim());
            county=m.group("county");
            list.add( county==null?"":county.trim());
            town=m.group("town");
            list.add( town==null?"":town.trim());
            village=m.group("village");
            list.add( village==null?"":village.trim());
        }
        if(list.isEmpty()) {
        	System.out.println("hhh");
        }
        return list;
    }
	
	public static List<String> divideAddress2(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+"
        		+ "自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+"
        		+ "县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇|.+街道)?(?<road>[^路]+路|.+街|.+巷)"
        		+ "?(?<doorplate>[^号]+号)?(?<village>.*)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,road=null,doorplate=null,village=null;
        List<String> list=new ArrayList<String>();
        
        while(m.find()){
            province=m.group("province");
            list.add( province==null?"":province.trim());
            city=m.group("city");
            list.add( city==null?"":city.trim());
            county=m.group("county");
            list.add( county==null?"":county.trim());
            town=m.group("town");
            list.add( town==null?"":town.trim());
            road=m.group("road");
            list.add( road==null?"":road.trim());
            doorplate=m.group("doorplate");
            list.add( doorplate==null?"":doorplate.trim());
            village=m.group("village");
            list.add( village==null?"":village.trim());
            
        }
        if(list.isEmpty()) {
        	System.out.println("hhh");
        }
        return list;
    }
	
	public static ArrayList<String> readInput(String name) {
		// 使用ArrayList来存储每行读取到的字符串
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			FileReader fr = new FileReader(name);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// 按行读取字符串
			while ((str = bf.readLine()) != null) {
				arrayList.add(str);
			}
			bf.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return arrayList;
	}

	
	public static String jsonRead() throws Exception {
        File file = new File("pcas.json");//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            System.out.println(s);
        }
        bReader.close();
        String str = sb.toString();
        System.out.println(str );
        return str;
    }
	
//	public void readJSON(JSONObject object){
//		System.out.println("people="+object.get("people").getAsString());
//		JSONArray array = object.get("pcas").;
//		for(int i=0;i<array.length();i++){
//			System.out.println("-----------");
//			JSONObject subObject = array.get(i).getAsJSONObject();
//			System.out.println("id="+subObject.get("id").getAsInt());
//			System.out.println("age="+subObject.get("age").getAsInt());
//			System.out.println("name="+subObject.get("name").getAsString());
//		}
//		System.out.println("-----------");
//		System.out.println("clever="+object.get("clever").getAsBoolean());
//	}
	
	public static String getNum(String num){
		if(num == null || num.length() == 0){return "";}
		Pattern pattern = Pattern.compile("(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)"); 
		Matcher matcher = pattern.matcher(num); 
		StringBuffer bf = new StringBuffer(64); 
		while (matcher.find()) { 
			bf.append(matcher.group()).append(","); 
		} 
		int len = bf.length(); 
		if (len > 0) { 
			bf.deleteCharAt(len - 1); 
		} 
		return bf.toString();
	} 
	
	public Object[] deleteSubString(String str1,String str2) {
		StringBuffer sb = new StringBuffer(str1);
		int delCount = 0;
		Object[] obj = new Object[2];
 
		while (true) {
			int index = sb.indexOf(str2);
			if(index == -1) {
				break;
			}
			sb.delete(index, index+str2.length());
			delCount++;
			
		}
		if(delCount!=0) {
			obj[0] = sb.toString();
			obj[1] = delCount;
		}else {
			//不存在返回-1
			obj[0] = -1;
			obj[1] = -1;
		}
		
		return obj;
	}
	
	
	
	

	


}
