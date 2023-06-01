package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TournamentStatsWriter {

    public void saveTournamentStats(List<TournamentStats.TournamentPlayer> listOfplayers) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("stats.csv"))) {
            for (TournamentStats.TournamentPlayer player : listOfplayers) {
                String firstName = player.getFirstName();
                String lastName = player.getLastName();
                Integer result = player.getResult();
                bufferedWriter.write(firstName + " " + lastName + ";" + result);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}