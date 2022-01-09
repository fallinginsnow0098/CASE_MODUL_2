package _Data.Tops;

import _Product.Tops;
import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;

public class DataOfTops {
    private static final IOFile<Tops> ioFile = new IOFile<>();

    private final String PATH_TOPS = "src/_File/ProductFile/tops";

    public DataOfTops(){}

    public ArrayList<Tops> topsList() throws NullPointerException{
        if (new File(PATH_TOPS).length() == 0)
            return new ArrayList<>();
        return ioFile.readFileData(PATH_TOPS);
    }
}
