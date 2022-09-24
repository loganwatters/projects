/**
 * Class represents a HuffmanCodeBook and has numerous functions to efficiently add sequences, check if
 * a character has a sequence already, check to see if all letters of a string have a sequence in
 * the book, get a sequence for a character, add a sequence to the book, and encode a string.
 */

public class HuffmanCodeBook {
    private char[] chars;
    private BinarySequence[] binarySequences;
    private int currentlyUsed = 0;

    /**
     * Constructor.
     */
    public HuffmanCodeBook(){
        chars = new char[10];
        binarySequences = new BinarySequence[10];
    }

    /**
     * Taking in a char and a BinarySequence, the method checks to see if the arrays are at their max capacity. If they
     * are, two new arrays are made with double the amount of indexes and the information is copied into the new arrays.
     * From there, regardless of the size of the array, through a search method finding the proper index to place the
     * new char and sequence, the array is fully/partially moved in order to support inserting to a sorted list.
     * @param c
     * @param seq
     */

    public void addSequence(char c, BinarySequence seq){
        if(currentlyUsed+1 == chars.length){
            char[] biggerC = new char[currentlyUsed*2];
            BinarySequence[] biggerB = new BinarySequence[currentlyUsed*2];
            for(int i = 0; i<currentlyUsed; i++){
                biggerC[i] = chars[i];
                biggerB[i] = binarySequences[i];
            }
            chars = biggerC;
            binarySequences = biggerB;
        }
        int toStartMove = 0;

        if(currentlyUsed == 0){
            chars[currentlyUsed] = c;
            binarySequences[currentlyUsed] = seq;
            currentlyUsed++;
        }
        else if(c > chars[currentlyUsed-1]){
            chars[currentlyUsed] = c;
            binarySequences[currentlyUsed] = seq;
            currentlyUsed++;
        }
        else if(c<chars[0]){
            for(int i = currentlyUsed; i>0; i--){
                chars[i] = chars[i-1];
                binarySequences[i] = binarySequences[i-1];
            }
            chars[0] = c;
            binarySequences[0] = seq;
            currentlyUsed++;
        }
        else {
            for (int i = 0; i<currentlyUsed-1; i++){
                if(c > chars[i] && c < chars[i+1]){
                    toStartMove = i+1;
                }
            }
            for(int i = currentlyUsed; i>=toStartMove; i--){
                if(i == toStartMove){
                    chars[i] = c;
                    binarySequences[i] = seq;
                }
                else {
                    chars[i] = chars[i-1];
                    binarySequences[i] = binarySequences[i-1];
                }
            }
            currentlyUsed++;
        }

    }

    /**
     * Takes in a char representing a letter. If the currentlyUsed value == 0, false is returned as there is nothing
     * in the arrays and therefore the letter cannot be in the CodeBook. From there, a traditional binary search of the
     * chars array is done and then when/if a match is found true is returned. If no match is found and the binary
     * search ends, then false is returned.
     * @param letter
     * @return
     */
    public boolean contains(char letter) {
        if(currentlyUsed == 0){
            return false;
        }
        int low = 0;
        int high = currentlyUsed;
        while(low <= high) {
            int mid = low + ((high-low)/2);
            if(chars[mid] < letter){
                low = mid + 1;
            }
            else if (chars[mid] > letter) {
                high = mid - 1;
            }
            else if (chars[mid] == letter){
                return true;
            }
        }
        return false;
    }

    /**
     * Takes in a String. Using a for loop, each char in the string is used as a parameter for the contains() method
     * to be called. If the contains method returns false, then false is returned from this function as well.
     * If false is never returned, then every letter in the string must also be in the CodeBook and therefore true
     * is returned.
     * @param letters
     * @return
     */
    public boolean containsAll(String letters){
        for(int i = 0; i<letters.length(); i++){
            if(!contains(letters.charAt(i))){
                return false;
            }
        }

        return true;
    }

    /**
     * Takes in a char representing a letter. If the currentlyUsed value == 0, false is returned as there is nothing
     * in the arrays and therefore the letter cannot be in the CodeBook. From there, a traditional binary search of the
     * chars array is done and then when/if a match is found then the BinarySequence at the index of the found char is
     * returned from the binarySequences array. If no match is found and the binary search ends, then null is returned.
     * @param c
     * @return
     */
    public BinarySequence getSequence(char c){
        int low = 0;
        int high = currentlyUsed;
        while(low <= high) {
            int mid = low + ((high-low)/2);
            if(chars[mid] < c){
                low = mid + 1;
            }
            else if (chars[mid] > c) {
                high = mid - 1;
            }
            else if (chars[mid] == c){
                return binarySequences[mid];
            }
        }
        return null;
    }

    /**
     * Takes in a String. A new BinarySequence object is created. If all of the chars in the passed in String
     * are present in the CodeBook, then for every letter in the string the getSequence function is called
     * and the rtrnSeq is appended with this sequence. Then, rtrnSeq is returned. Otherwise, null is returned.
     * @param s
     * @return
     */
    public BinarySequence encode (String s){
        BinarySequence rtrnSeq = new BinarySequence();
        if(containsAll(s)) {
            for (int i = 0; i < s.length(); i++) {
                rtrnSeq.append(getSequence(s.charAt(i)));
            }
            return rtrnSeq;
        }
        else {
            return null;
        }
    }

    /**
     * Taking in an integer representing an index, this returns the char at that index in the chars array.
     * @param index
     * @return
     */
    public char loopFunction(int index) {
        return chars[index];
    }

    /**
     * Returns the currently used variable.
     * @return
     */
    public int getCurrentlyUsed(){
        return currentlyUsed;
    }

    public void String() {
        for(int i = 0; i<currentlyUsed; i++) {
            System.out.println(chars[i]);
            System.out.println(binarySequences[i].toString());
        }
    }
}
