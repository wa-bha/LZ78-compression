//
// Multi way trie
//
public class MultiWayTrie {
    // Private fields
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
}