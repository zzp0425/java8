package com.zzp.learn.mode;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Desc
 * Created by zzp
 * on 2016/9/23.11:48
 */
public class ParseFile {

    private <T> T withLinesOf(Reader input, Function<Stream<String>, T> handler, Function<IOException, RuntimeException> error) {
        try(BufferedReader reader = new BufferedReader(input)) {
            return handler.apply(reader.lines());
        } catch (IOException e) {
            e.printStackTrace();
            throw error.apply(e);
        }
    }

    public List<String> findHeadings(Reader input) {
        return withLinesOf(input,
                lines -> lines.filter(line -> line.endsWith(":"))
                        .map(line -> line.substring(0, line.length() - 1))
                        .collect(Collectors.toList()),
                RuntimeException::new);
    }

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("E:\\logs\\a.txt"))) {
            ParseFile parseFile = new ParseFile();
            parseFile.findHeadings(br).stream().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
