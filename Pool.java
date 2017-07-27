import java.util.*;


interface PoolObj {
    void release();
    Integer getValue();
}

class Pool {
    private Queue<Optional<Integer>> queue = new LinkedList<Optional<Integer>>();

    Pool(Integer fisrt, Integer size) {
        for (int i = 0; i < size; i++) {
        	queue.add(Optional.of(fisrt++));
        }
    }

    public void print() {
    	System.out.print("All elements of queue: ");
    	String coma = "";
        for (Optional<Integer> str : queue) {
		    System.out.print(coma + str.get());
		    coma = ", ";
		}
    	System.out.println();
    }

    public PoolObj allocate() {
    	final Optional<Integer> poll = queue.poll();

    	if (poll == null) {
    		throw new NullPointerException("Current pool is null! Objects are Released!");
    	}

    	return new PoolObj() {
		    Optional<Integer> str = poll;

		    @Override
		    public void release() {
		    	if (!str.isPresent()) {
		    		throw new NullPointerException("Current string was released!");
		    	}
		    	queue.add(str);
		    	str = Optional.empty();
		    }

		    @Override
		    public Integer getValue() {
		    	if (!str.isPresent()) {
		    		throw new NullPointerException("Current string is null!");
		    	}
		    	return str.get();
		    }
    	};
    }
}