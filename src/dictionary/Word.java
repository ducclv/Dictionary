/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author Admin
 */
public class Word {
    int idx;
    String word;
    public Word(int idx, String word) {
        this.idx = idx;
        this.word = word;
    }
    @Override
        public String toString(){
            return word;
        }
}
