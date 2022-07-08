package edu.byohttp.resource;


import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DirectoryResourceSearcher implements ResourceSearcher {

    private final ResourceFactory resourceFactory;
    private final Map<String, String> mimeTypesMapping = new HashMap<>();
    private final File resourcesDirectory;

    public DirectoryResourceSearcher(final ResourceFactory resourceFactory,
                                     final File mimeTypesMapping, final File resourcesDirectory) {

        this.resourceFactory = resourceFactory;
        this.resourcesDirectory = resourcesDirectory;


        try {
            final String filePath = mimeTypesMapping.getAbsolutePath();
            final BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] mime = line.split(",");
                this.mimeTypesMapping.put(mime[0], mime[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<Resource> findResource(String resource) {
        final String resourceFullPath = this.resourcesDirectory.getAbsolutePath() + "/" +  resource;

        File file = new File(resourceFullPath);
        if (!file.exists()) {
            return Optional.empty();
        }

        return Optional.of(this.resourceFactory.resource(this.getResourceName(file), this.identifyMime(file),
                this.getResourceSize(file), this.getResourceLastModified(file),
                this.getResourceBytes(file)));
    }

    private String identifyMime (File resourceFile) {
        final String extension = getFileExtension(resourceFile);

        return this.mimeTypesMapping.get(extension);
    }

    private Long getResourceSize(File resourceFile) {

        return resourceFile.length();
    }

    private String getResourceName(File resourceFile) {
        return resourceFile.getName();
    }

    private String getResourceLastModified(File resourceFile) {

        final long lastModified = resourceFile.lastModified();

        return new Date(lastModified).toString();


    }

    private InputStream getResourceBytes(File resourceFile) {
        try {
            return new FileInputStream(resourceFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileExtension(File file) {
        String extension = "";

        final String name = file.getName();
        final int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf > 0) {
            extension = name.substring(lastIndexOf + 1);
        }

        return extension;
    }
}

