package pl.javastart.task;

import java.util.*;

public class TournamentStats {

    private static final int SORT_BY_FIRST_NAME = 1;
    private static final int SORT_BY_LAST_NAME = 2;
    private static final int SORT_BY_RESULT = 3;
    private static final int SORTING_BY_REVERSE = 2;
    private static final int SORT_ASCENDING = 1;
    private static boolean reverseList;

    void run(Scanner scanner) {
        TournamentStatsWriter tournamentStatsWriter = new TournamentStatsWriter();
        List<TournamentPlayer> tournamentPlayers = listOfPersonsMaker(scanner);
        sortListOfPlayers(scanner, tournamentStatsWriter, tournamentPlayers);
    }

    private static void sortListOfPlayers(Scanner scanner, TournamentStatsWriter tournamentStatsWriter, List<TournamentPlayer> tournamentPlayers) {
        int sortDescendingOrAscending;

        System.out.printf("Po jakim parametrze posortować? (%d - imię, %d - nazwisko, %d - wynik)\n", SORT_BY_FIRST_NAME, SORT_BY_LAST_NAME, SORT_BY_RESULT);
        int parameter = scanner.nextInt();
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        sortDescendingOrAscending = scanner.nextInt();
        if (sortDescendingOrAscending == SORTING_BY_REVERSE) {
            reverseList = true;
        } else if (sortDescendingOrAscending == SORT_ASCENDING) {
            reverseList = false;
        }
        List<TournamentPlayer> sortedListByParameter = sortListByParameter(tournamentPlayers, parameter);
        tournamentStatsWriter.saveTournamentStats(sortedListByParameter);

    }

    private static List<TournamentPlayer> sortListByParameter(List<TournamentPlayer> tournamentPlayers, int parameter) {
        Comparator<TournamentPlayer> comparator = switch (parameter) {
            case SORT_BY_FIRST_NAME -> Comparator.comparing(o -> o.firstName);
            case SORT_BY_LAST_NAME -> Comparator.comparing(o -> o.lastName);
            case SORT_BY_RESULT -> Comparator.comparing(o -> o.result);
            default -> throw new IllegalStateException("Niespotykana wartość" + parameter);
        };
        if (reverseList) {
            comparator = comparator.reversed();
        }
        tournamentPlayers.sort(comparator);
        return tournamentPlayers;
    }

    private List<TournamentPlayer> listOfPersonsMaker(Scanner scanner) {
        List<TournamentPlayer> listOfPlayers = new LinkedList<>();
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

    }
}
