import java.text.SimpleDateFormat;
import java.util.Date;

class LogWriter {
    private String class_name;
    private boolean debug;

    /**
     * create a data stamped string for the message
     * @param msg the message
     * @return a date stamped string of the message (which
     *  also include the method name and the class name
     */
    private  String make_date_msg(String msg, int stack_trace_num) {
        Thread t = Thread.currentThread();
        String method_name = new Throwable().getStackTrace()[stack_trace_num].getMethodName();
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/y HH:mm:ss:SSS");
        Date date = new Date();
        return String.format("[%d][%s][%s][%s]: %s%n", t.getId(), formatter.format(date), class_name, method_name, msg);
    }

    private String make_ip_msg(String ip, String msg) {
        String ip_msg_format = "<%s> %s";
        return String.format(ip_msg_format, ip, msg);
    }

    private void print_out(String msg, int stack_trace_num) {
        String date_msg = make_date_msg(msg, stack_trace_num);
        if(debug) {
            System.out.print(date_msg);
        }
    }

    private void print_err(String msg) {
        String date_msg = make_date_msg(msg, 3);
        System.err.print(date_msg);
    }

    void force_write_out(String msg) {
        String date_msg = make_date_msg(msg, 3);
        System.out.print(date_msg);
    }

    void force_write_out(String msg, String ip) {
        String ip_msg = make_ip_msg(ip, msg);
        String date_msg = make_date_msg(ip_msg, 3);
        System.out.print(date_msg);
    }

    /**
     * constructor
     * @param class_name the name of the class that is using the logger
     */
    LogWriter(String class_name, boolean debug) {
        this.class_name = class_name;
        this.debug = debug;
    }

    /**
     * write a msg to stdout or file
     * @param msg the message
     */
    void write_out(String msg) {
        print_out(msg, 3);
    }

    /**
     * write a msg from the server
     * @param ip the ip that the msg came from
     * @param msg the msg
     */
    void write_out(String ip, String msg) {
        print_out(make_ip_msg(ip, msg), 3);
    }

    /**
     * write a msg to stderr
     * @param msg the message
     */
    void write_err(String msg) {
        print_err(msg);
    }

    /**
     * write a msg from the server
     * @param ip the ip that the msg came from
     * @param msg the msg
     */
    void write_err(String ip, String msg) {
        print_err(make_ip_msg(ip, msg));
    }

    /**
     * write error msg and exit
     * @param msg the msg
     */
    void exit_with_error(String msg) {
        print_err(msg);
        System.exit(0);
    }

    /**
     * write error msg and exit
     * @param ip the ip the msg came from
     * @param msg the msg
     */
    void exit_with_error(String ip, String msg) {
        print_err(make_ip_msg(ip, msg));
        System.exit(0);
    }
}
