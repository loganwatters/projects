/**
 * Class representing  HuffmanCodeTree with numerous methods to put a CodeBook into the tree, put new sequences
 * into the tree, and decode BinarySequences.
 */

public class HuffmanCodeTree {
    private HuffmanNode root;

    /**
     * Constructor. Sets root equal to the parameter.
     * @param root
     */
    public HuffmanCodeTree(HuffmanNode root){
        this.root = root;
    }

    /**
     * Constructor. Sets the root to be a new HuffmanNode with a null one and zero variable. Then, for every array
     * index in the passed-in codebook, the put function is run with the sequence and char from that index using
     * the getSequence and loopFunctions from the HuffmanCodeBook class.
     * @param codebook
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook){
        this.root = new HuffmanNode(null, null);
        for(int i = 0; i<codebook.getCurrentlyUsed(); i++){
            put(codebook.getSequence(codebook.loopFunction(i)),codebook.loopFunction(i));
        }
    }

    /**
     * Returns true if the HuffmanNode isValid() method returns true on the root HuffmanNode. Else, false.
     * @return
     */
    public boolean isValid() {
        return root.isValid();
    }

    /**
     * For every index in the passed in sequence, if it is equal to true, then the node is set to the node in the
     * one variable of the node and the same logic is applied to the 0s in the sequence. If the zero/one variable
     * is null, then a new HuffmanNode is assigned to the variables. After the whole sequence is looped through,
     * the data of the last node explored in the loop has its data set the letter parameter. Returns nothing.
     * @param seq
     * @param letter
     */
    public void put (BinarySequence seq, char letter){
        HuffmanNode node = root;
        for(int i = 0; i<seq.size(); i++){
            if(seq.get(i)){
                if(node.getOne() == null){
                    node.setOne(new HuffmanNode(null, null));
                    node.setZero(new HuffmanNode(null, null));
                }
                node = node.getOne();
            }
            else{
                if(node.getZero() == null){
                    node.setOne(new HuffmanNode(null, null));
                    node.setZero(new HuffmanNode(null, null));
                }
                node = node.getZero();
            }
        }
        node.setData(letter);
    }

    /**
     * Taking in a BinarySequence, for each index in the sequence, either the one variable or zero variable is
     * accessed and set to be the new node or, if that node is a leaf, the data at that node is appended to
     * a StringBuilder object. At the end of the sequence, this StringBuilder object is converted to a String
     * and returned as a String.
     * @param s
     * @return
     */
    public String decode(BinarySequence s) {
        HuffmanNode node = root;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.size()+1; i++) {
            if (s.get(i)) {
                if (node.isLeaf()) {
                    stringBuilder.append(node.getData());
                    node = root;
                }
                node = node.getOne();
            } else {
                if (node.isLeaf()) {
                    stringBuilder.append(node.getData());
                    node = root;
                }
                node = node.getZero();
            }
        }
        String rtn = stringBuilder.toString();
        return rtn;
    }

}
