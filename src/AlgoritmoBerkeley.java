import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AlgoritmoBerkeley {

    public static void main(String[] args) throws InterruptedException {
        // Definir o tempo atual do relógio mestre
        Instant currentTime = Instant.now();

        // Criar um conjunto de relógios para sincronizar
        List<Instant> clocks = new ArrayList<>();
        clocks.add(currentTime);
        clocks.add(currentTime.plusSeconds(1));
        clocks.add(currentTime.minusMillis(500));
        clocks.add(currentTime.plusMillis(750));

        // Selecionar o relógio mestre
        Instant masterClock = clocks.get(0);

        // Calcular a média ponderada das diferenças de tempo entre os relógios e o relógio mestre
        long sum = 0;
        for (int i = 1; i < clocks.size(); i++) {
            sum += clocks.get(i).toEpochMilli() - masterClock.toEpochMilli();
        }
        double averageDifference = (double) sum / (double) (clocks.size() - 1);

        // Ajustar os relógios dos outros computadores na rede
        for (int i = 1; i < clocks.size(); i++) {
            clocks.set(i, clocks.get(i).minusNanos((long) (averageDifference * 1_000_000)));
        }

        // Exibir os relógios ajustados
        System.out.println("Relógios ajustados:");
        for (Instant clock : clocks) {
            System.out.println(clock);
        }
    }

}
