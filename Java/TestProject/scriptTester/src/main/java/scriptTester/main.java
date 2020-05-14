package scriptTester;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class main {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try{
            Boolean eval = (Boolean) engine.eval("40>50");
            System.out.println("eval = " + eval);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
