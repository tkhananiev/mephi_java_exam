package mephi.java.exam.command;

import mephi.java.exam.service.LinkService;
import mephi.java.exam.service.UserService;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProcessor(UserService userService, LinkService linkService) {
        commands.put("create", new CreateCommand(userService, linkService));
        commands.put("edit", new EditCommand(userService, linkService));
        commands.put("remove", new RemoveCommand(userService, linkService));
        commands.put("goto", new GoToCommand(userService, linkService));
        commands.put("list", new ListCommand(userService));
    }

    public boolean process(String input){
        if (input == null || input.isBlank()){
            return true;
        }
        String[] parts = input.trim().split(" ");
        String commandName = parts[0].toLowerCase();
        if ("exit".equals(commandName)){
            System.out.println("Завершение работы");
            return false;
        }
        Command command = commands.get(commandName);
        if (command == null){
            System.out.println("Неизвестная команда. Доступные комманды - create, edit, remove, goto, list, exit");
            return true;
        }
        command.execute(parts);
        return true;
    }
}
