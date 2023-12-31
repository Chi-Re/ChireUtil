package chireutil.python;

import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**@author 炽热S
 * 可以实现python的使用
 */
public class Python {
    public PythonInterpreter interpreter;
    public Python(PythonInterpreter interpreter){
        this.interpreter = interpreter;
    }
    public Python(){
        this.interpreter = init();
    }
    /**初始化，为了方便*/
    public static PythonInterpreter init() {
        try (PythonInterpreter inter = new PythonInterpreter()){
            return inter;
        }
    }
    /**打印变量*/
    public void printIn(String arg) {
        interpreter.exec("print(" + arg + ");");
    }
    /**打印字*/
    public void print(String arg){
        printIn("'" + arg + "'");
    }
    /**调用项目内的py文件(所有都可以)，返回文件内容*/
    public String putFile(String file, Class<?> c){
        try {
            ClassLoader classLoader = c.getClassLoader();
            URL resource = classLoader.getResource(file);
            if (resource == null) return "null";
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream(), StandardCharsets.UTF_8));
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e){
            return e.toString();
        }
    }
}

