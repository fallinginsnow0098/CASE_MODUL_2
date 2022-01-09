package _Data.Accessories;

import _Product.Accessories;
import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;

public class DataOfAccessories {
    private static final IOFile<Accessories> ioFile = new IOFile<>();

    private final String PATH_ACCESSORIES = "src/_File/ProductFile/accessories";

    public DataOfAccessories(){}

    public ArrayList<Accessories> accessoriesList() throws NullPointerException{
        if (new File(PATH_ACCESSORIES).length() == 0)
            return new ArrayList<>();
        return ioFile.readFileData(PATH_ACCESSORIES);
    }
}
