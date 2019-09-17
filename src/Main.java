import java.util.regex.*;
import net.sf.json.*;
import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception {
		
		List<Object> agencyList = new ArrayList<Object>();
        
		
		ArrayList<String> arrayList = readInput("src/in.txt");
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
	        agencyMap.put("����",name);
	        agencyMap.put("�ֻ�", num);
	        agencyMap.put("��ַ", list);
	        agencyList.add(agencyMap);
	        
	        
		}
		JSONArray jsonObject = JSONArray.fromObject(agencyList);
        String jsonString1 = jsonObject.toString();
        CreateFileUtil.createJsonFile(jsonString1, "C:\\Users\\CYH\\Desktop\\", "ans");
		
//		
//		String s = jsonRead();
//        JSONObject jsonObject = new JSONObject().fromObject(s);
		
		 

	}
	
	public static List<String> divideAddress1(String address){
        String regex="(?<province>[^ʡ]+������|.*?ʡ|.*?������|.*?��)(?<city>[^��]+������|.*?����|.*?������λ|.+��|��Ͻ��|.*?��|.*?��)(?<county>[^��]+��|.+��|.+��|.+��|.+����|.+��)?(?<town>[^��]+��|.+��|.+�ֵ�)?(?<village>.*)";
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
        String regex="(?<province>[^ʡ]+������|.*?ʡ|.*?������|.*?��)(?<city>[^��]+"
        		+ "������|.*?����|.*?������λ|.+��|��Ͻ��|.*?��|.*?��)(?<county>[^��]+"
        		+ "��|.+��|.+��|.+��|.+����|.+��)?(?<town>[^��]+��|.+��|.+�ֵ�)?(?<road>[^·]+·|.+��|.+��)"
        		+ "?(?<doorplate>[^��]+��)?(?<village>.*)";
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
		// ʹ��ArrayList���洢ÿ�ж�ȡ�����ַ���
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			FileReader fr = new FileReader(name);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// ���ж�ȡ�ַ���
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
        File file = new File("pcas.json");//����һ��file����������ʼ��FileReader
        FileReader reader = new FileReader(file);//����һ��fileReader����������ʼ��BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//newһ��BufferedReader���󣬽��ļ����ݶ�ȡ������
        StringBuilder sb = new StringBuilder();//����һ���ַ������棬���ַ�����Ż�����
        String s = "";
        while ((s =bReader.readLine()) != null) {//���ж�ȡ�ļ����ݣ�����ȡ���з���ĩβ�Ŀո�
            sb.append(s + "\n");//����ȡ���ַ�����ӻ��з����ۼӴ���ڻ�����
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
			//�����ڷ���-1
			obj[0] = -1;
			obj[1] = -1;
		}
		
		return obj;
	}
	
	
	
	

	


}
