/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class File{
    
   
    //public static final String WORDS = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\Dictionary\\src\\dictionary\\word.txt";
    //public static final String EXPLAINS = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\Dictionary\\src\\dictionary\\explain.txt"; 
    public static final String Target = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\Dictionary\\src\\data list\\Target.txt";
    public static final String Explains = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\Dictionary\\src\\data list\\Explain.txt";
    //=============================================================================
    // Đọc file từ vựng
     public ArrayList<String> Load(String filePath) {
        ArrayList<String> array = new ArrayList<>();
        Reader reader;
        BufferedReader bufferedReader = null;
        try {
            //Opening the file
            reader = new FileReader(filePath);
            bufferedReader = new BufferedReader(reader);

            //Reading the file
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                      
                array.add(currentLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + filePath + "is not found !");
        } catch (IOException e) {
            System.out.println("Problem occurs when reading file !");
        } finally {
            //Closing the file
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Problem occurs when closing file !");
                }
            }
        }
        return array;
    }
    
    public int readWord(String word,ArrayList<String>Load) {
        int check = 0;
        int result = 0;
        for (String Load1 : Load) {
            check++;
            if (Load1.equals(word)) {
                result = check;
                break;
            }
        }
        return result;
    }
//=============================================================================================
// Đọc file đáp án
public String readExplain(int result,String EXPLAINS)
{    
            int check = 0;
            String currentLine;
            //Make sure you use \\ instead of \
		String filePath = EXPLAINS;

                Reader reader;
		BufferedReader bufferedReader = null;
		try 
                {
			//Opening the file
			reader = new FileReader(filePath);
			bufferedReader = new BufferedReader(reader);
 
			//Reading the file
			
			while ((currentLine = bufferedReader.readLine()) != null) 
                        {
                            check++;
                            if(result == check){
                                return currentLine;
                            }                              
			}
		} 
                catch (FileNotFoundException e) 
                {
			System.out.println("The file " + filePath + "is not found !");
		}
                catch (IOException e)
                {
			System.out.println("Problem occurs when reading file !");
		}
                finally
                {
			//Closing the file
			if (bufferedReader != null) 
                        {
				try
                                {
					bufferedReader.close();
				}
                                catch (IOException e)
                                {
					System.out.println("Problem occurs when closing file !");
				}
                                
                        }
    
                }  
                return "Từ không có trong từ diển";
    }  
//===================================================================================================
// viết tiếp vào file từ điển
public void writeContinue(String str,String FILENAME)
{
    BufferedWriter bw = null;
    FileWriter fw = null;
    try {
        String content =  str+"\r\n";
        fw = new FileWriter(FILENAME,true);
        bw = new BufferedWriter(fw);
        bw.write(content);
        //System.out.println("Viet file xong!");
 
    } 
    catch (IOException e) {} 
    finally {
        try {
            if (bw != null)
            bw.close();
            if (fw != null)
            fw.close();
            } 
        catch
                (IOException ex) { }
 
            }
} 

public void write(String str,String FILENAME)
{
    BufferedWriter bw = null;
    FileWriter fw = null;
    try {
        String content = str;
        fw = new FileWriter(FILENAME);
        bw = new BufferedWriter(fw);
        bw.write(content);
        //System.out.println("Viet file xong!");
    } 
    catch (IOException e) {} 
    finally {
        try {
            if (bw != null)
            bw.close();
            if (fw != null)
            fw.close();
            } 
        catch
                (IOException ex) { }
 
            }
} 
}
