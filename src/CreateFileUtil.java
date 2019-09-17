
import java.io.*;
public class CreateFileUtil {
    /**
     * ����.json��ʽ�ļ�
     */
    public static boolean createJsonFile(String jsonString, String filePath, String fileName) {
        // ����ļ������Ƿ�ɹ�
        boolean flag = true;

        // ƴ���ļ�����·��
        String fullPath = filePath + File.separator + fileName + ".json";

        // ����json��ʽ�ļ�
        try {
            // ��֤����һ�����ļ�
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // �����Ŀ¼�����ڣ�������Ŀ¼
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // ����Ѵ���,ɾ�����ļ�
                file.delete();
            }
            file.createNewFile();

            if(jsonString.indexOf("'")!=-1){  
                //��������ת��һ�£���ΪJSON���е��ַ������Ϳ��Ե�������������  
                jsonString = jsonString.replaceAll("'", "\\'");  
            }  
            if(jsonString.indexOf("\"")!=-1){  
                //��˫����ת��һ�£���ΪJSON���е��ַ������Ϳ��Ե�������������  
                jsonString = jsonString.replaceAll("\"", "\\\"");  
            }  
              
            if(jsonString.indexOf("\r\n")!=-1){  
                //���س�����ת��һ�£���ΪJSON�����ַ������ܳ�����ʽ�Ļس�����  
                jsonString = jsonString.replaceAll("\r\n", "\\u000d\\u000a");  
            }  
            if(jsonString.indexOf("\n")!=-1){  
                //������ת��һ�£���ΪJSON�����ַ������ܳ�����ʽ�Ļ���  
                jsonString = jsonString.replaceAll("\n", "\\u000a");  
            }  
            
            // ��ʽ��json�ַ���
            jsonString = JsonFormatTool.formatJson(jsonString);

            // ����ʽ������ַ���д���ļ�
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        // �����Ƿ�ɹ��ı��
        return flag;
    }
       
}
