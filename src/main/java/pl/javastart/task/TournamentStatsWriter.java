package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TournamentStatsWriter {

    public void saveTournamentStats(List<TournamentPlayer> listOfPlayers) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("stats.csv"))) {
            for (TournamentPlayer player : listOfPlayers) {
                String firstName = player.getFirstName();
                String lastName = player.getLastName();
                Integer result = player.getResult();
                bufferedWriter.write(firstName + " " + lastName + ";" + result);
                bufferedWriter.newLine();
            }
        }
    }
}
