package WordStatistics;

import java.io.*;
import java.util.*;
import javax.swing.table.*;

import java.util.concurrent.locks.*;

public class file {
    
    private File mainPath;
    private ArrayList<File> folders;
    private long[][] lastModify;
    private Lock lock;
    private DefaultTableModel table;
    private ArrayList<String> minCount;
    private ArrayList<String> maxCount;
    private WordStatistics guiForm;
    
    file(String path, WordStatistics ws){
        mainPath = new File(path);
        folders = new ArrayList<File>();
        minCount = new ArrayList<String>();
        maxCount = new ArrayList<String>();
        
        lock = new ReentrantLock();
        table = ws.t;
        
        guiForm = ws;
    }

    public void getFiles(){
        
        folders.add(mainPath);
        if(guiForm.ISselectSubDirectories()) {  for (File file : mainPath.listFiles())
                                              if (file.isDirectory()) folders.add(file);
                                            }
        
        lastModify = new long[folders.size()][];
        for(int i=0; i<lastModify.length;i++) lastModify[i]=new long[(int)folders.get(i).length()];
        
        int j;
        for(int i=0; i<folders.size(); i++) {   j=0;
                                                for(File f: folders.get(i).listFiles())
                                                if(f.isFile()) {        lastModify[i][j] = f.lastModified();
                                                                        table.addRow(new Object[]{f.getName()});
                                                                        j++;
                                                                }
                                            }
    }
    
    public void wCouter(int folderIndex, int fileIndex, int tablePosition) throws FileNotFoundException, IOException {
        lock.lock();
        String line;
        int i=0, word=0;
        File f = folders.get(folderIndex).getAbsoluteFile();
        
        for (File file : f.listFiles())
        {   if(i!=fileIndex) {  i++;
                                continue;
                             }
                               System.out.println(file.getName()+"\t"+folderIndex+"\t"+fileIndex+"\t"+tablePosition);
            if(file.isFile()){  FileInputStream fileInputStream = new FileInputStream(file);
                                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                word = 0;
                                while ((line = bufferedReader.readLine()) != null)
                                {     String words[] = line.split("\\s+");
                                      word += words.length;
                                }
                             }
            break;
        }
       table.setValueAt(word, tablePosition, 1);
       lock.unlock();
    }
    
    public void isCount(int folderIndex, int fileIndex, int tablePosition) throws FileNotFoundException, IOException {
        lock.lock();
        String line;
        int i=0, word=0;
        File f = folders.get(folderIndex).getAbsoluteFile();

        for (File file : f.listFiles())
        {   if(i!=fileIndex) {  i++;
                                continue;
                             }
            
            if(file.isFile()){  FileInputStream fileInputStream = new FileInputStream(file);
                                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                word = 0;
                                while ((line = bufferedReader.readLine()) != null)
                                {     String words[] = line.split("\\s+");
                                       for(int j=0; j<words.length; j++){
                                          if ( words[j].equalsIgnoreCase("is")) word++;
                                        }
                                }
                            }
            break;
        }

       table.setValueAt(word, tablePosition, 2);
       lock.unlock();
    }
    
    public void areCount(int folderIndex, int fileIndex, int tablePosition) throws FileNotFoundException, IOException {
        lock.lock();
        String line;
        int i=0, word=0;
        File f = folders.get(folderIndex).getAbsoluteFile();

        for (File file : f.listFiles())
        {   if(i!=fileIndex) {  i++;
                                continue;
                             }
            
            if(file.isFile()){  FileInputStream fileInputStream = new FileInputStream(file);
                                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                word = 0;
                                while ((line = bufferedReader.readLine()) != null)
                                {     String words[] = line.split("\\s+");
                                      for(int j=0; j<words.length; j++){
                                          if ( words[j].equalsIgnoreCase("are")) word++;
                                        }
                                }
                            }
            break;
        }

       table.setValueAt(word, tablePosition, 3);
       lock.unlock();
    }
    
    public void youCount(int folderIndex, int fileIndex, int tablePosition) throws FileNotFoundException, IOException {
       lock.lock();
        String line;
        int i=0, word=0;
        File f = folders.get(folderIndex).getAbsoluteFile();

        for (File file : f.listFiles())
        {   if(i!=fileIndex) {  i++;
                                continue;
                             }
            
            if(file.isFile()){  FileInputStream fileInputStream = new FileInputStream(file);
                                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                word = 0;
                                while ((line = bufferedReader.readLine()) != null)
                                {     String words[]  = line.split("\\s+");
                                      for(int j=0; j<words.length; j++){
                                          if ( words[j].equalsIgnoreCase("you")) word++;
                                        }
                                }
                            }
            break;
        }

       table.setValueAt(word, tablePosition, 4);
       lock.unlock();
    }
      
    public void Min(int folderIndex, int fileIndex, int tablePosition) throws FileNotFoundException, IOException {
        lock.lock();
        int i=0;
        String line, Count = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Min="";
        File f = folders.get(folderIndex).getAbsoluteFile();

        for (File file : f.listFiles())
        {   if(i!=fileIndex) {  i++;
                                continue;
                             }
            
            if(file.isFile()){  FileInputStream fileInputStream = new FileInputStream(file);
                                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
                                while ((line = bufferedReader.readLine()) != null)
                                {       String words[] = line.split("\\s+");
                                        for (int j = 1; j < words.length; j++)
                                        {       if (words[j].length() < Count.length()) Count = words[j];
                                        }
                                }
                                minCount.add(Count);
                             }
            break;
        }
        table.setValueAt(Count, tablePosition, 6);
        
        Min = minCount.get(0);
        for(String s: minCount) 
            if(s.length() < Min.length()) Min=s;   
        
        guiForm.ShortestWords(Min);
        lock.unlock();
    }
    
    public void Max(int folderIndex, int fileIndex, int tablePosition) throws FileNotFoundException, IOException {
        lock.lock();
        int i=0;
        String line, Count = "", Max="";
        File f = folders.get(folderIndex).getAbsoluteFile();

        for (File file : f.listFiles())
        {   if(i!=fileIndex) {  i++;
                                continue;
                             }
        
            if(file.isFile()){  FileInputStream fileInputStream = new FileInputStream(file);
                                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
                                while ((line = bufferedReader.readLine()) != null)
                                {       String words[] = line.split("\\s+");
                                        for (int j = 0; j < words.length; j++)
                                        {       if (words[j].length() > Count.length()) Count = words[j];
                                        }
                                }
                                maxCount.add(Count);
                            }
            break;
        }
        table.setValueAt(Count, tablePosition, 5);   

        Max = maxCount.get(0);
        for(String s: maxCount) 
            if(s.length() > Max.length()) Max=s;

        guiForm.longestWord(Max);
        lock.unlock();
    }
    
    public boolean check(int folderIndex, int fileIndex) {
        File f = folders.get(folderIndex).getAbsoluteFile();
        long lastmodify;
        int i=0, count=0;
        
        for (File file : f.listFiles())
        {   if(i!=fileIndex) {  i++;
                                continue;
                             }
        
             else            {  if(file.isFile()) {     lastmodify = file.lastModified();                
                                                        if(lastmodify!=lastModify[folderIndex][i]){
                                                            lock.lock();
                                                            lastModify[folderIndex][i]=lastmodify;
                                                            lock.unlock();
                                                            return true;
                                                                                                   }
                                                   }
                                break;
                            }
        }   return false;
    }
}
