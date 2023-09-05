package WordStatistics;

import java.io.*;

public class Run implements Runnable{
    private int folderNo;
    private int fileNo;
    private int tablePosition;
    private file functions;
    
    Run(int folder,int file, int tp,file fun){
        folderNo=folder;
        fileNo=file;
        tablePosition= tp;
        functions= fun;
    }
    
    @Override
    public void run() {
        try {
            while(true) {
                            functions.wCouter(folderNo,fileNo,tablePosition);
            
                            functions.isCount(folderNo,fileNo,tablePosition);
            
                            functions.areCount(folderNo,fileNo,tablePosition);
            
                            functions.youCount(folderNo,fileNo,tablePosition);
            
                            functions.Min(folderNo,fileNo,tablePosition);
            
                            functions.Max(folderNo,fileNo,tablePosition);            
                            while(!functions.check(folderNo,fileNo));
                        }
            }catch (IOException ex) { System.out.println("exeption: file is Not Found!"); }
                                        
    }
}
