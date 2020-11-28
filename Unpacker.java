import java.lang.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

class Unpacker
{    
    public FileOutputStream outstream = null;
    public Unpacker(String src)
    {
        unpackFile(src);
    }
    public void unpackFile(String FilePath)
    {
        try
        {
            FileInputStream instream = new FileInputStream(FilePath);
            
            byte Header[] = new byte[100];
            int length = 0;
            int counter=0;

            while((length = instream.read(Header,0,100)) > 0)
            {
                String str = new String(Header);          
                String ext = str.substring(str.lastIndexOf("\\"));
                
                ext = ext.substring(1);
                
                String words[] = ext.split("\\s");
                String name = words[0];
                int size = Integer.parseInt(words[1]);
                
                byte arr[] = new byte[size];
                instream.read(arr,0,size);

                System.out.println("New File Get Created as : "+name);
                //New File gets created
                FileOutputStream fout = new FileOutputStream(name);
                //Write data into newly created file
                fout.write(arr,0,size);
                counter++;
            }
        }
        catch(Exception obj)
        {
            System.out.println(obj);
        }
    }
}