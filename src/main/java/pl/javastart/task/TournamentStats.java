package pl.javastart.task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TournamentStats {

    void run(Scanner scanner) {
        TournamentStatsWriter tournamentStatsWriter = new TournamentStatsWriter();
        LinkedList<TournamentPlayer> tournamentPlayers = listOfPersonsMaker(scanner);
        sortListOfPlayers(scanner, tournamentStatsWriter, tournamentPlayers);
    }

    private static void sortListOfPlayers(Scanner scanner, TournamentStatsWriter tournamentStatsWriter, LinkedList<TournamentPlayer> tournamentPlayers) {
        int sortDescendingOrAscending;

        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
        int parameter = scanner.nextInt();
        LinkedList<TournamentPlayer> sortedListByParameter = sortListByParameter(tournamentPlayers, parameter);
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        sortDescendingOrAscending = scanner.nextInt();
        if (sortDescendingOrAscending == 2) {
            List<TournamentPlayer> reversedSortedList = IntStream.range(0, sortedListByParameter.size())
                    .map(i -> sortedListByParameter.size() - 1 - i).mapToObj(sortedListByParameter::get).collect(Collectors.toList());
            tournamentStatsWriter.saveTournamentStats(reversedSortedList);
        } else {
            tournamentStatsWriter.saveTournamentStats(sortedListByParameter);
        }
    }

    private static LinkedList<TournamentPlayer> sortListByParameter(LinkedList<TournamentPlayer> tournamentPlayers, int parameter) {
        switch (parameter) {
            case 1 -> tournamentPlayers.sort(Comparator.comparing(o -> o.firstName));
            case 2 -> tournamentPlayers.sort(Comparator.comparing(o -> o.lastName));
            case 3 -> tournamentPlayers.sort(Comparator.comparing(o -> o.result));
            default -> throw new IllegalStateException("Unexpected value: " + parameter);
        }
        return tournamentPlayers;
    }

    private LinkedList<TournamentPlayer> listOfPersonsMaker(Scanner scanner) {
        LinkedList<TournamentPlayer> listOfPlayers = new LinkedList<>();
        String line;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            line = scanner.nextLine();
            if (line.equalsIgnoreCase("stop")) {
                break;
            }
            TournamentPlayer tournamentPlayer = personMaker(line);
            listOfPlayers.add(tournamentPlayer);
        } while (!line.equals("stop"));
        return listOfPlayers;
    }

    private TournamentPlayer personMaker(String line) {
        String[] strings = line.split(" ");
        String firstName = strings[0];
        String lastName = strings[1];
        Integer result = Integer.valueOf(strings[2]);
        return new TournamentPlayer(firstName, lastName, result);
    }

    protected static class TournamentPlayer {
        private final String firstName;
        private final String lastName;
        private final Integer result;

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

        @Override
        public String toString() {
            return firstName + " " + lastName + " " + result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TournamentPlayer player = (TournamentPlayer) o;
            return Objects.equals(firstName, player.firstName) && Objects.equals(lastName, player.lastName) && Objects.equals(result, player.result);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, result);
        }
    }
}
