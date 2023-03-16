package com.napier.sem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Map.entry;

public class QueryParser implements IQueryParser {

    public QueryParser() {

    }

    @Override
    public List<Map<String, String>> ParseQueries(String pathToQueries) throws IOException {
        Path file = Paths.get(pathToQueries);
        String queryFileContent = new String(Files.readAllBytes(file));

        List<String> queries = List.of(queryFileContent.split("\n\n"));

        return SplitAllParsedQueries(queries);
    }

    private List<Map<String, String>> SplitAllParsedQueries(List<String> queries){
        List<Map<String, String>> splitQueries = new ArrayList<>();
        for (String query : queries) {
            Map<String, String> mappedQuery = SplitQueryFromName(query);
            splitQueries.add(mappedQuery);
        }

        return splitQueries;
    }

    private Map<String, String> SplitQueryFromName(String query){
        String[] splitQuery = query.split("\n", 2);

        return Map.ofEntries(
                entry("queryName", splitQuery[0]),
                entry("query",splitQuery[1])
        );
    }
}
