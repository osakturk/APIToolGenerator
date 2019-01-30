package beans;


public class ManagedBean {
    private static boolean stop = false;

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg != null)
                start(arg);
            else
                stop();
        }
    }

    /**
     * Service method for on start event
     */
    private static void start(String arg) {
        RequestBean.start(arg);
    }

    /**
     * Service method for on stop event
     */
    private static void stop() {
        stop = true;
        System.out.println("Stoping service");
        System.exit(0);
    }
}
