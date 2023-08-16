import service.Commands;
import service.UserInteraction;

public class Main {

    public static void main(String[] args) {
        Commands commands = new Commands();
        while(true){
            commands.comandHandler();
        }
    }
}