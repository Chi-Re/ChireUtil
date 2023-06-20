package chireutil.python;

import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Python {
    public PythonInterpreter interpreter;
    public Python(PythonInterpreter interpreter){
        this.interpreter = interpreter;
    }
    public Python(){
        this.interpreter = init();
    }
    public static PythonInterpreter init() {
        try (PythonInterpreter inter = new PythonInterpreter()){
            return inter;
        }
    }
    public void printIn(String arg) {
        interpreter.exec("print(" + arg + ");");
    }
    public void print(String arg){
        printIn("'" + arg + "'");
    }
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

