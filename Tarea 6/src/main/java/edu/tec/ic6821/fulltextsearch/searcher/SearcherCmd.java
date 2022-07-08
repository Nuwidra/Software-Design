package edu.tec.ic6821.fulltextsearch.searcher;

import edu.tec.ic6821.fulltextsearch.IndexLocation;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class SearcherCmd {

    private final Set<String> terms;
    private final File indexLocation;

    public SearcherCmd(final Set<String> terms, final File indexLocation) {
        this.terms = terms;
        this.indexLocation = indexLocation;
    }

    public static void main(String[] args) {
        validateCmdLine(args);
        final File indexLocation = indexLocation();

        try {
            final Set<String> terms = new HashSet<>(Arrays.asList(args));
            new SearcherCmd(terms, indexLocation).execute();
        } catch (Exception e) {
            System.out.printf("Unexpected error occurred: %s%n", e.getMessage());
        }
    }

    private static void validateCmdLine(String[] args) {
        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: indexer <term>+");
        System.out.println("    <term>: each of the terms to search for");
    }

    private static File indexLocation() {
        final Optional<File> optionalIndexLocation = new IndexLocation().get();
        if (optionalIndexLocation.isEmpty()) {
            System.out.println("Error locating index file");
            System.exit(1);
        }
        return optionalIndexLocation.get();
    }

    public void execute() {
        final Searcher searcher = SearcherComponentFactory.searcher(this.indexLocation);
        final Set<File> resultSet = searcher.search(this.terms);
        resultSet.forEach(file -> System.out.println(file.getAbsolutePath()));
    }
}
