import java.util.*;


interface PoolObj {
    void release() throws CustomException;
    Integer getValue() throws CustomException;
}

class Pool {
    private Queue<Integer> queue = new LinkedList<Integer>();

    Pool(int fisrt, int size) {
        for (int i = 0; i < size; i++) {
        	queue.add(fisrt++);
        }
    }

    public void print() {
    	System.out.print("All elements of queue: ");
    	String coma = "";

        for (Integer str : queue) {
		    System.out.print(coma + str);
		    coma = ", ";
		}

    	System.out.println();
    }


    public PoolObj allocate() throws CustomException {
    	final Integer value = queue.poll();

    	if (value == null) {
    		throw new CustomException("Current pool is null! Objects are Released!");
    	}

        return getPoolObj(value);
    }

    private PoolObj getPoolObj(final Integer value) {
        return new PoolObj() {
            private boolean isReleased = false;

            @Override
            public void release() throws CustomException {
                if (isReleased) {
                    throw new CustomException("Current string was released!");
                }
                queue.add(value);
                isReleased = true;
            }

            @Override
            public Integer getValue() throws CustomException {
                if (isReleased) {
                    throw new CustomException("Current string is null!");
                }
                return value;
            }
        };
    }
}