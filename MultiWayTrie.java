//
// Multi way trie
//
public class MultiWayTrie {
    // Private fields
    private static int      lastPhraseIndex = 0;
    private MultiWayTrie    _parent;
    private MultiWayTrie    _rightSibling;
    private MultiWayTrie    _firstChild;
    private Byte            _mismatchedValue;
    private int             _phraseIndex;

    //
    // Create new MultiWayTrie
    //
    public MultiWayTrie() {
        // Initialize multi way trie to have null phrase
        _parent             = null;
        _rightSibling       = null;
        _firstChild         = null;
        _mismatchedValue    = null;
        _phraseIndex        = 0;
    }

    //
    // Get right sibling
    //
    public MultiWayTrie getRightSibling() {
        return _rightSibling;
    }

    //
    // Get first child
    //
    public MultiWayTrie getFirstChild() {
        return _firstChild;
    }

    //
    // Get parent
    //
    public MultiWayTrie getParent() {
        return _parent;
    }

    //
    // Insert a new value or return the node that is equal to the value
    //
    public MultiWayTrie insert(Byte value) {
        MultiWayTrie currentChild = _firstChild;
        
        // Iterate through this node's children
        while (currentChild != null) {
            if (Byte.compare(currentChild._mismatchedValue, value) == 0) { // If value is already in phrase, return node
                // TODO: Move currentChild to front of linked list in order to eventually have somewhat 'ordered by frequency' linekd list
                return currentChild;
            }
            currentChild = currentChild._rightSibling;
        }

        // Insert value after all other child nodes
        currentChild = newNonRootMultiWayTrie(value, lastPhraseIndex+1);
        lastPhraseIndex += 1;

        return null; // Returning null indicates value was inserted
    }

    //
    // Create a new multiway trie that is not a root node
    //
    private MultiWayTrie newNonRootMultiWayTrie(byte mismatchedValue, int phraseIndex) {
        MultiWayTrie t = new MultiWayTrie();
        // Set node properties
        t._mismatchedValue = mismatchedValue;
        t._phraseIndex = phraseIndex;
        return t;

    }
}