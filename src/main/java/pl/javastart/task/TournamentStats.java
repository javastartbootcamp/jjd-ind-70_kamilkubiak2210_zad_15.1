package pl.javastart.task;

import java.io.IOException;
import java.util.*;

public class TournamentStats {

    private static final int SORT_BY_FIRST_NAME = 1;
    private static final int SORT_BY_LAST_NAME = 2;
    private static final int SORT_BY_RESULT = 3;
    private static final int SORTING_BY_REVERSE = 2;
    private static final int SORT_ASCENDING = 1;
    private static boolean reverseList;

    void run(Scanner scanner) {
        TournamentPlayer tournamentPlayer = new TournamentPlayer();
        List<TournamentPlayer> tournamentPlayersList = tournamentPlayer.createListOfPersons(scanner);
        Comparator<TournamentPlayer> comparator = tournamentPlayerComparator(scanner);
        tournamentPlayersList.sort(comparator);
        try {
            TournamentStatsWriter tournamentStatsWriter = new TournamentStatsWriter();
            tournamentStatsWriter.saveTournamentStats(tournamentPlayersList);
        } catch (IOException e) {
            System.err.println("Statystyki nie zostały zapisane");
        }
    }

    private static Comparator<TournamentPlayer> tournamentPlayerComparator(Scanner scanner) {
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
        Comparator<TournamentPlayer> comparator = switch (parameter) {
            case SORT_BY_FIRST_NAME -> Comparator.comparing(TournamentPlayer::getFirstName);
            case SORT_BY_LAST_NAME -> Comparator.comparing(TournamentPlayer::getLastName);
            case SORT_BY_RESULT -> Comparator.comparing(TournamentPlayer::getResult);
            default -> throw new IllegalStateException("Niespotykana wartość" + parameter);
        };
        if (reverseList) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
