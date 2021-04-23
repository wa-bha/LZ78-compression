import java.util.ArrayList;
import java.util.List;

//
// Multi way trie
//
public class MultiWayTrie {
    // Private fields
    private static  List<Integer> trieCounts = new ArrayList<Integer>();
    private int             _trieIndex;
    private MultiWayTrie    _parent;
    private MultiWayTrie    _rightSibling;
    private MultiWayTrie    _firstChild;
    private Byte            _mismatchedValue;
    private int             _phraseIndex;

    //
    // Create new MultiWayTrie that is the root
    //
    public MultiWayTrie() {
        _trieIndex = trieCounts.size();
        trieCounts.add(0);

        // Initialize multi way trie to have null phrase
        _parent             = null;
        _rightSibling       = null;
        _firstChild         = null;
        _mismatchedValue    = null;
        _phraseIndex        = 0;
    }
    //
    // Create new MultiWayTrie that is not  the root
    //
    private MultiWayTrie(MultiWayTrie parent, Byte mismatchedValue) {
        _trieIndex = parent._trieIndex;
        trieCounts.set(_trieIndex, trieCounts.get(_trieIndex)+1); // Increment trie count

        // Initialize multi way trie to have non null phrase
        _parent             = parent;
        _rightSibling       = null;
        _firstChild         = null;
        _mismatchedValue    = mismatchedValue;
        _phraseIndex        = trieCounts.get(_trieIndex);
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
        currentChild = new MultiWayTrie(this, value);
        return null; // Returning null indicates value was inserted
    }
}