package edu.tec.ic6821.fulltextsearch.indexer;

import edu.tec.ic6821.fulltextsearch.IndexLocation;

import java.io.File;
import java.util.Optional;

public final class IndexerCmd {

    private final File startingDirectory;
    private final File indexLocation;

    public IndexerCmd(final File startingDirectory, final File indexLocation) {
        this.startingDirectory = startingDirectory;
        this.indexLocation = indexLocation;
    }

    public static void main(String[] args) {
        validateCmdLine(args);
        final File startingDirectory = startingDirectory(args[0]);
        final File indexLocation = indexLocation();

        try {
            new IndexerCmd(startingDirectory, indexLocation).execute();
        } catch (Exception e) {
            System.out.printf("Unexpected error occurred: %s%n", e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println("Usage: indexer <directory location>");
        System.out.println("    <directory location>: the path of the directory to index");
    }

    private static void validateCmdLine(String[] args) {
        if (args.length != 1) {
            printUsage();
            System.exit(1);
        }
    }

    private static File startingDirectory(String arg) {
        final File startingDirectory = new File(arg);
        if (!startingDirectory.exists() || !startingDirectory.isDirectory()) {
            System.out.printf("Location %s does not exist or is not a directory%n",
                startingDirectory.getAbsolutePath());
            System.exit(1);
        }
        return startingDirectory;
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
        final Indexer indexer = IndexerComponentFactory.indexer(this.startingDirectory, this.indexLocation);
        indexer.index((File f) -> {
            if (f.isDirectory()) {
                System.out.println(f.getAbsolutePath());
            }
        });
    }
}
