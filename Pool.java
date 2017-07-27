import java.util.*;


interface PoolObj {
	String str = "";
    void release();
    String getValue();
}

class Pool {
    private Queue<Optional<String>> queue = new LinkedList<Optional<String>>();

    Pool(String... args) {

        for (int i = 0; i < args.length; i++) {
        	queue.add(Optional.of(args[i]));
        }
    }

    public void print() {
    	System.out.print("All elements of queue: ");
    	String coma = "";
        for (Optional<String> str : queue) {
		    System.out.print(coma + str.get());
		    coma = ", ";
		}
    	System.out.println();
    }

    public PoolObj allocate() {
    	final Optional<String> poll = queue.poll();

    	if (poll == null) {
    		throw new NullPointerException("Current pool is null! Objects are Released!");
    	}

    	return new PoolObj() {
		    Optional<String> str = poll;

		    @Override
		    public void release() {
		    	if (!str.isPresent()) {
		    		throw new NullPointerException("Current string was released!");
		    	}
		    	queue.add(str);
		    	str = Optional.empty();
		    }

		    @Override
		    public String getValue() {
		    	if (!str.isPresent()) {
		    		throw new NullPointerException("Current string is null!");
		    	}
		    	return str.get();
		    }
    	};
    }
}