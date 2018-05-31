package map.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Map<String, Command> map = new ...
 * map.put(str1, cmd1);
 * map.put(str2, cmd2);
 * Command cmd = map.getOrDefault(name, defaultCmd);
 * cmd.execute();
 * 
 * instead of
 * switch/if constructions
 * 
 * Command cmd;
 * if (name.equals(str1)) {
 *  cmd = cmd1;
 * } else if (name.equals(str2)) {
 *  cmd = cmd2;
 * } else {
 *  cmd = defaultCmd;
 * }
 * cmd.execute()
 */
public class MapStringToAction {
    
    public static interface Command {
        void doAction();
    }
    public static class Hello implements Command {
        @Override
        public void doAction() {
            System.out.println("hello world!");
        }
    }
    public static class Class implements Command {
        @Override
        public void doAction() {
            System.out.println(MapStringToAction.class.getName());
        }
    }
    
    
    public static interface CommandFactory {
        Command newHello();
        Command newClass();
    }
    public static class DefaultCommandFactory implements CommandFactory {
        @Override
        public Command newHello() {
            return new Hello();
        }
        @Override
        public Command newClass() {
            return new Class();
        }
    }

    public static void main(String[] args) {
        
        CommandFactory cmdFac = new DefaultCommandFactory();
        Map<String, Command> strToCmd = new HashMap<>();
        strToCmd.put("hello", cmdFac.newHello());
        strToCmd.put("class", cmdFac.newClass());
        
        System.out.println("Enter string: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        
        Command cmd = strToCmd.getOrDefault(name, () -> System.out.println("no such command!")); // lambda
        cmd.doAction();
    }

}
