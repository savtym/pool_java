import java.util.*;


interface PoolObj {
	String str = "";
    void release();
    String getValue();
}

class Pool {
    private Queue<String> queue = new LinkedList<String>();

    Pool(String... args) {
        for (int i = 0; i < args.length; i++) {
        	queue.add(args[i]);
        }
    }

    public void print() {
    	System.out.print("All elements of queue: ");
    	String coma = "";
        for (String str : queue) {
		    System.out.print(coma + str);
		    coma = ", ";
		}
    	System.out.println();
    }

    public PoolObj allocate() {
    	final String poll = queue.poll();

    	if (poll == null) {
    		throw new IllegalArgumentException("Current pool is null! Release Objects");
    	}

    	return new PoolObj() {
		    String str = poll;

		    @Override
		    public void release() {
		    	if (str == null) {
		    		throw new IllegalArgumentException("Current string was released!");
		    	}
		    	queue.add(str);
		    	str = null;
		    }

		    @Override
		    public String getValue() {
		    	if (str == null) {
		    		throw new IllegalArgumentException("Current string is null!");
		    	}
		    	return str;
		    }
    	};
    }
}