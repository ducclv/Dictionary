/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CommandLine {
    
    public ArrayList<String> DictionarySearch (ArrayList<String>s1, String s2){
        ArrayList<String>result = new ArrayList<>();
        for(int i = 0; i < s1.size();i++){
            if(s1.get(i).length()>=s2.length()){
                String word_split = splitString(s1.get(i),s2.length());
                if(word_split.equals(s2)){
                    result.add(s1.get(i));
                }
            }
        }
    return result;
    }
   
    public String splitString(String s, int n){
        String str="";
        for(int i=0;i<n;i++){
            str+=s.charAt(i);
        }
        return str;      
    } 
    
    public void sortword(ArrayList<String>s1, ArrayList<String>s2){
      
		int n = s1.size();
		for(int i=0; i<n-1; i++)
		{
			for(int j=i+1; j<n; j++)
			{
				String str1 = s1.get(i);
				String str2 = s1.get(j);
				String str3 = s2.get(i);
				String str4 = s2.get(j);
				if(str1.compareTo(str2)>0)
				{
					String s = str1;
					s1.set(i, str2);
					s1.set(j,s);
					String a = str3;
					s2.set(i, str4);
					s2.set(j, a);
				}
			}
		}
    }
    
    public void sound(String word) {
        VoiceManager vm;
        Voice v;
        System.setProperty("mbrola.base", "C:\\Users\\Admin\\Documents\\NetBeansProjects\\Dictionary\\src\\mbrola");
        vm = VoiceManager.getInstance();
        v = vm.getVoice("mbrola_us1");
        v.allocate();
        v.speak(word);
    }
    
    
    
    
  
}
