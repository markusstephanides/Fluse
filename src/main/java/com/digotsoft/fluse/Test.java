package com.digotsoft.fluse;

import com.digotsoft.fluse.server.files.FileLoader;
import com.digotsoft.fluse.transpreter.interpreting.FileInterpreter;

import java.io.IOException;

/**
 * @author Markus
 */
public class Test {

    public static void main (String[] args) throws IOException {
        FileLoader fileLoader = new FileLoader();

        new FileInterpreter( fileLoader.loadFile("classes/Page.fs").getData()).run();
        new FileInterpreter( fileLoader.loadFile("classes/Index.fs").getData()).run();
    }

}
