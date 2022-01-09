package _Data.Bottom;

import _Product.Bottom;
import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;

public class DataOfBottom {
    private static final IOFile<Bottom> ioFile = new IOFile<>();

    private final String PATH_BOTTOM = "src/_File/ProductFile/bottoms";

    public DataOfBottom(){}

    public ArrayList<Bottom> bottomList() throws NullPointerException{
        if (new File(PATH_BOTTOM).length() == 0)
            return new ArrayList<>();
        return ioFile.readFileData(PATH_BOTTOM);
    }
}
