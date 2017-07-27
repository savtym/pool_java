class main {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        Pool pool = new Pool(10, 4);

        System.out.println(pool.getClass());
        pool.print();

        try {

            PoolObj k1 = pool.allocate();
            PoolObj k2 = pool.allocate();
            PoolObj k3 = pool.allocate();

	        System.out.println(k1.getValue());
	        System.out.println(k2.getValue());
	        System.out.println(k3.getValue());

        	pool.print();

            k2.release();
            k3.release();
	        k1.release();
            pool.print();

        } catch(CustomException e) {
        	// TODO: release some action
    		System.out.println(e);
        }

    }
}