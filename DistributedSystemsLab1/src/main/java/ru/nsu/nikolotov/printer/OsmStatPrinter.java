package ru.nsu.nikolotov.printer;

import ru.nsu.nikolotov.osmstat.OsmStats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OsmStatPrinter {

    private final Consumer<Map.Entry<String, Integer>> userChangesPrinter;
    private final Consumer<Map.Entry<String, Integer>> tagsPrinter;
    private final List<Map.Entry<String, Integer>> tagsCountPerKey;
    private final List<Map.Entry<String, Integer>> userChanges;

    public OsmStatPrinter(OsmStats stats, Consumer<Map.Entry<String, Integer>> userChangesPrinter,
                          Consumer<Map.Entry<String, Integer>> tagsPerCountPrinter) {
        this.userChangesPrinter = userChangesPrinter;
        this.tagsPrinter = tagsPerCountPrinter;
        this.tagsCountPerKey = new ArrayList<>(stats.getTagCountPerKey().entrySet());
        userChanges = new ArrayList<>(stats.getUserChanges().entrySet());
        sortUserChangesStats();
    }

    public void printStats() {
        printStats(SortDirection.DESC);
    }

    public void printStats(SortDirection userChangesSortDir) {
        printUserChangesStats(userChangesSortDir);
        tagsCountPerKey.forEach(tagsPrinter);
    }

    private void printUserChangesStats(SortDirection userChangesSortDir) {
        switch (userChangesSortDir) {
            case ASC -> userChanges.forEach(userChangesPrinter);
            case DESC -> {
                for (int i = userChanges.size() - 1; i >= 0; i--) {
                    userChangesPrinter.accept(userChanges.get(i));
                }
            }
        }
    }

    private void sortUserChangesStats() {
        userChanges.sort(Comparator.comparingInt(Map.Entry::getValue));
    }
}
