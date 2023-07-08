
import java.util.ArrayList;
import java.util.Scanner;

public class Menu<E> {
    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public int int_getChoice(ArrayList<E> options) {
        int response;
        int N = options.size();

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        System.out.print("Please choose an option 1.." + N + ": ");
        response = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character from the input buffer
        return response;
    }

    public E ref_getChoice(ArrayList<E> options) {
        int response;
        int N = options.size();
        E choice;

        do {
            response = int_getChoice(options);
        } while (response < 1 || response > N);

        choice = options.get(response - 1);
        return choice;
    }
}
