/**
 * Class represents a HuffmanCodeBook and has numerous functions to efficiently add sequences, check if
 * a character has a sequence already, check to see if all letters of a string have a sequence in
 * the book, get a sequence for a character, add a sequence to the book, and encode a string.
 */

public class HuffmanCodeBook2 {
    private char[] chars;
    private BinarySequence[] binarySequences;
    int currentlyUsed = 0;

    /**
     * Constructor.
     */
    public HuffmanCodeBook2(){
        chars = new char[10];
        binarySequences = new BinarySequence[10];
    }

    /**
     * Taking in a char array, and integer representing the size of the array, and a BinarySequence array,
     * the traditional mechanics of a merge sort are done on both of the arrays simultaneously.
     * @param chars
     * @param n
     * @param binarySequences
     */
    public void mergeSort(char[] chars, int n, BinarySequence[] binarySequences){
        if(n<2) {
            return;
        }

        int mid = n /2;

        char[] lc = new char[mid];
        char[] rc = new char[n-mid];
        BinarySequence[] lb = new BinarySequence[mid];
        BinarySequence[] rb = new BinarySequence[n - mid];

        for(int i = 0; i<mid; i++){
            lc[i] = chars[i];
            lb[i] = binarySequences[i];
        }
        int j = 0;
        for(int i = mid; i<n; i++){
            rc[j] = chars[i];
            rb[j] = binarySequences[i];
            j++;
        }

        mergeSort(lc, mid, lb);
        mergeSort(rc, n-mid, rb);
        merge(chars, lc, rc, binarySequences, lb, rb, mid, n-mid);
    }

    /**
     * Taking in 3 char arrays representing a final array, a left hand size and a right hand size, three
     * BinarySequence arrays representing the same concepts as the char arrays, and two integers representing
     * left and right values for the end/beginnings of the large array, a traditional merge is done in order to support
     * merge-sort mechanics.
     * @param chars
     * @param lc
     * @param rc
     * @param binarySequences
     * @param lb
     * @param rb
     * @param left
     * @param right
     */
    public void merge(char[] chars, char[] lc, char[] rc, BinarySequence[] binarySequences, BinarySequence[] lb, BinarySequence[] rb, int left, int right){
        int i = 0, j = 0, k=0;
        while(i<left && j < right){
            if(lc[i] <= rc[j]){
                chars[k] = lc[i];
                binarySequences[k] = lb[i];
                k++;
                i++;
            }
            else {
                chars[k] = rc[j];
                binarySequences[k] = rb[j];
                j++;
                k++;
            }
        }
        while(i<left) {
            chars[k] = lc[i];
            binarySequences[k] = lb[i];
            k++;
            i++;
        }
        while(j<right) {
            chars[k] = rc[j];
            binarySequences[k] = rb[j];
            k++;
            j++;
        }
    }

    /**
     * Taking in a char array to be sorted and a BinarySequence array to be sorted based on the movement of
     * the char array, the mergeSort method is called and traditional merge sort is done.
     * @param toSort
     * @param toFollow
     */
    private void doMergeSort(char[] toSort, BinarySequence[] toFollow) {
        char[] copyC = toSort.clone();
        BinarySequence [] copyB = toFollow.clone();
        mergeSort(copyC, currentlyUsed, copyB);
        chars = copyC.clone();
        binarySequences = copyB.clone();
    }

    /**
     * Taking in a char and a BinarySequence, the method checks to see if the arrays are at their max capacity. If they
     * are, two new arrays are made with double the amount of indexes and the information is copied into the new arrays.
     * From there, regardless of the size of the array, the next open char and BinarySequence array indexes are
     * assigned with the parameter values. currentlyUsed is then increased by one and the char and BinarySequence
     * arrays from the object are then sorted using the doMergeSort() method.
     * @param c
     * @param seq
     */
    public void addSequence(char c, BinarySequence seq){
        if(currentlyUsed == chars.length){
            char[] biggerC = new char[currentlyUsed*2];
            BinarySequence[] biggerB = new BinarySequence[currentlyUsed*2];
            for(int i = 0; i<currentlyUsed; i++){
                biggerC[i] = chars[i];
                biggerB[i] = binarySequences[i];
            }
            chars = biggerC;
            binarySequences = biggerB;
        }

        chars[currentlyUsed] = c;
        binarySequences[currentlyUsed] = seq;
        currentlyUsed++;
        doMergeSort(chars, binarySequences);
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

    public char loopFunction(int index) {
        return chars[index];
    }

    public void String() {
        for(int i = 0; i<currentlyUsed; i++) {
            System.out.println(chars[i]);
        }
    }
}
