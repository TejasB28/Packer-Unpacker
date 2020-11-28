import java.lang.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

class Packer
{
	//Object For File Writing
	public FileOutputStream outstream=null;

	//Parametrised Constructor
	public Packer(String FolderName,String FileName)
	{
		try
		{
			//Create New File For Package
			File outfile=new File(FileName);
			outstream=new FileOutputStream(FileName);

			//Set the current working directory for folder traversal
			//System.setProperty("user.dir",FolderName);

			TravelDirectory(FolderName);
		}
		catch(Exception obj)
		{
			System.out.println(obj);
		}
	}

	public void TravelDirectory(String path)
	{
		File directoryPath=new File(path);
		int counter=0;
		//Get all file names from directory
		File arr[]=directoryPath.listFiles();

		for(File filename:arr)
		{
			//System.out.println(filename.getName());
			if(filename.getName().endsWith(".txt"))
			{				
				PackFile(filename.getAbsolutePath());
			}
		}
	}

	public void PackFile(String FilePath)
    {
        byte Header[] = new byte[100];
        byte Buffer[] = new byte[1024]; 
        int length=0;
		FileInputStream istream=null;

        File fobj = new File(FilePath);
        String temp = FilePath+" "+fobj.length();
        
        // Create header of 100 bytes
        for(int i = temp.length(); i< 100; i++)
        {
            temp = temp + " ";
        }        
        Header=temp.getBytes();
       	
       	try
       	{
       		//Open File For Reading
       		istream=new FileInputStream(FilePath);

       		outstream.write(Header,0,Header.length);
       		while((length = istream.read(Buffer))>0)
       		{
       			outstream.write(Buffer,0,length);
       		}
       		istream.close();
       	}
       	catch(Exception obj)
       	{
       		System.out.println(obj);
       	}
    }
}
