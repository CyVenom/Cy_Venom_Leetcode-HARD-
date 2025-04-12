import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {
    // Stack to store the current position in the nested list at each level
    private Stack<Iterator<NestedInteger>> stack = new Stack<>();
    private Integer nextElement = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        // Push the iterator of the top level of the nested list
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // Make sure we have a valid next element
        if (nextElement == null) {
            throw new IllegalStateException("No next element");
        }
        Integer result = nextElement;
        nextElement = null;  // Reset nextElement for future calls
        return result;
    }

    @Override
    public boolean hasNext() {
        // If we already have a next element, return true
        if (nextElement != null) {
            return true;
        }

        // Continue searching for the next integer
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> iter = stack.peek();

            // If the current iterator has a next element
            if (iter.hasNext()) {
                NestedInteger ni = iter.next();

                // If the element is an integer, we set nextElement and return true
                if (ni.isInteger()) {
                    nextElement = ni.getInteger();
                    return true;
                } else {
                    // If the element is a list, push it to the stack
                    stack.push(ni.getList().iterator());
                }
            } else {
                // If the current iterator is exhausted, pop it
                stack.pop();
            }
        }

        // No more elements to iterate, return false
        return false;
    }
}
