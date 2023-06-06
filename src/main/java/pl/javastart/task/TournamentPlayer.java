package pl.javastart.task;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TournamentPlayer {
    private String firstName;
    private String lastName;
    private Integer result;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getResult() {
        return result;
    }

    public TournamentPlayer(String firstName, String lastName, Integer result) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.result = result;
    }

    public TournamentPlayer() {
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + result;
    }

    public List<TournamentPlayer> createListOfPersons(Scanner scanner) {
        List<TournamentPlayer> listOfPlayers = new LinkedList<>();
        String line;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            line = scanner.nextLine();
            if (line.equalsIgnoreCase("stop")) {
                break;
            }
            TournamentPlayer tournamentPlayer = createPerson(line);
            listOfPlayers.add(tournamentPlayer);
        } while (!line.equals("stop"));
        return listOfPlayers;
    }

    private TournamentPlayer createPerson(String line) {
        String[] strings = line.split(" ");
        String firstName = strings[0];
        String lastName = strings[1];
        Integer result = Integer.valueOf(strings[2]);
        return new TournamentPlayer(firstName, lastName, result);
    }
}


