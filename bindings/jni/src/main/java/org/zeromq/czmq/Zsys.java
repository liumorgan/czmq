/*
################################################################################
#  THIS FILE IS 100% GENERATED BY ZPROJECT; DO NOT EDIT EXCEPT EXPERIMENTALLY  #
#  Read the zproject/README.md for information about making permanent changes. #
################################################################################
*/
package org.zeromq.czmq;

public class Zsys {
    static {
        try {
            System.loadLibrary ("czmqjni");
        }
        catch (Exception e) {
            System.exit (-1);
        }
    }
    public long self;
    /*
    Initialize CZMQ zsys layer; this happens automatically when you create
    a socket or an actor; however this call lets you force initialization
    earlier, so e.g. logging is properly set-up before you start working.
    Not threadsafe, so call only from main thread. Safe to call multiple
    times. Returns global CZMQ context.
    */
    native static long __init ();
    public long init () {
        return __init ();
    }
    /*
    Optionally shut down the CZMQ zsys layer; this normally happens automatically
    when the process exits; however this call lets you force a shutdown
    earlier, avoiding any potential problems with atexit() ordering, especially
    with Windows dlls.
    */
    native static void __shutdown ();
    public void shutdown () {
        __shutdown ();
    }
    /*
    Get a new ZMQ socket, automagically creating a ZMQ context if this is
    the first time. Caller is responsible for destroying the ZMQ socket
    before process exits, to avoid a ZMQ deadlock. Note: you should not use
    this method in CZMQ apps, use zsock_new() instead.
    *** This is for CZMQ internal use only and may change arbitrarily ***
    */
    native static long __socket (int type, String filename, long lineNbr);
    public long socket (int type, String filename, long lineNbr) {
        return __socket (type, filename, lineNbr);
    }
    /*
    Destroy/close a ZMQ socket. You should call this for every socket you
    create using zsys_socket().
    *** This is for CZMQ internal use only and may change arbitrarily ***
    */
    native static int __close (long handle, String filename, long lineNbr);
    public int Close (long handle, String filename, long lineNbr) {
        return __close (handle, filename, lineNbr);
    }
    /*
    Return ZMQ socket name for socket type
    *** This is for CZMQ internal use only and may change arbitrarily ***
    */
    native static String __sockname (int socktype);
    public String sockname (int socktype) {
        return __sockname (socktype);
    }
    /*
    Create a pipe, which consists of two PAIR sockets connected over inproc.
    The pipe is configured to use the zsys_pipehwm setting. Returns the
    frontend socket successful, NULL if failed.
    */
    native static long __createPipe (long backendP);
    public Zsock createPipe (Zsock backendP) {
        return new Zsock (__createPipe (backendP.self));
    }
    /*
    Reset interrupt handler, call this at exit if needed
    */
    native static void __handlerReset ();
    public void handlerReset () {
        __handlerReset ();
    }
    /*
    Set default interrupt handler, so Ctrl-C or SIGTERM will set
    zsys_interrupted. Idempotent; safe to call multiple times.
    Can be supressed by ZSYS_SIGHANDLER=false
    *** This is for CZMQ internal use only and may change arbitrarily ***
    */
    native static void __catchInterrupts ();
    public void catchInterrupts () {
        __catchInterrupts ();
    }
    /*
    Return 1 if file exists, else zero
    */
    native static boolean __fileExists (String filename);
    public boolean fileExists (String filename) {
        return __fileExists (filename);
    }
    /*
    Return file modification time. Returns 0 if the file does not exist.
    */
    native static long __fileModified (String filename);
    public long fileModified (String filename) {
        return __fileModified (filename);
    }
    /*
    Return file mode; provides at least support for the POSIX S_ISREG(m)
    and S_ISDIR(m) macros and the S_IRUSR and S_IWUSR bits, on all boxes.
    Returns a mode_t cast to int, or -1 in case of error.
    */
    native static int __fileMode (String filename);
    public int fileMode (String filename) {
        return __fileMode (filename);
    }
    /*
    Delete file. Does not complain if the file is absent
    */
    native static int __fileDelete (String filename);
    public int fileDelete (String filename) {
        return __fileDelete (filename);
    }
    /*
    Check if file is 'stable'
    */
    native static boolean __fileStable (String filename);
    public boolean fileStable (String filename) {
        return __fileStable (filename);
    }
    /*
    Create a file path if it doesn't exist. The file path is treated as
    printf format.
    */
    native static int __dirCreate (String pathname);
    public int dirCreate (String pathname []) {
        return __dirCreate (pathname [0]);
    }
    /*
    Remove a file path if empty; the pathname is treated as printf format.
    */
    native static int __dirDelete (String pathname);
    public int dirDelete (String pathname []) {
        return __dirDelete (pathname [0]);
    }
    /*
    Move to a specified working directory. Returns 0 if OK, -1 if this failed.
    */
    native static int __dirChange (String pathname);
    public int dirChange (String pathname) {
        return __dirChange (pathname);
    }
    /*
    Set private file creation mode; all files created from here will be
    readable/writable by the owner only.
    */
    native static void __fileModePrivate ();
    public void fileModePrivate () {
        __fileModePrivate ();
    }
    /*
    Reset default file creation mode; all files created from here will use
    process file mode defaults.
    */
    native static void __fileModeDefault ();
    public void fileModeDefault () {
        __fileModeDefault ();
    }
    /*
    Return the CZMQ version for run-time API detection; returns version
    number into provided fields, providing reference isn't null in each case.
    */
    native static void __version (int major, int minor, int patch);
    public void version (int major, int minor, int patch) {
        __version (major, minor, patch);
    }
    /*
    Format a string using printf formatting, returning a freshly allocated
    buffer. If there was insufficient memory, returns NULL. Free the returned
    string using zstr_free().
    */
    native static String __sprintf (String format);
    public String sprintf (String format) {
        return __sprintf (format);
    }
    /*
    Handle an I/O error on some socket operation; will report and die on
    fatal errors, and continue silently on "try again" errors.
    *** This is for CZMQ internal use only and may change arbitrarily ***
    */
    native static void __socketError (String reason);
    public void socketError (String reason) {
        __socketError (reason);
    }
    /*
    Return current host name, for use in public tcp:// endpoints. Caller gets
    a freshly allocated string, should free it using zstr_free(). If the host
    name is not resolvable, returns NULL.
    */
    native static String __hostname ();
    public String hostname () {
        return __hostname ();
    }
    /*
    Move the current process into the background. The precise effect depends
    on the operating system. On POSIX boxes, moves to a specified working
    directory (if specified), closes all file handles, reopens stdin, stdout,
    and stderr to the null device, and sets the process to ignore SIGHUP. On
    Windows, does nothing. Returns 0 if OK, -1 if there was an error.
    */
    native static int __daemonize (String workdir);
    public int daemonize (String workdir) {
        return __daemonize (workdir);
    }
    /*
    Drop the process ID into the lockfile, with exclusive lock, and switch
    the process to the specified group and/or user. Any of the arguments
    may be null, indicating a no-op. Returns 0 on success, -1 on failure.
    Note if you combine this with zsys_daemonize, run after, not before
    that method, or the lockfile will hold the wrong process ID.
    */
    native static int __runAs (String lockfile, String group, String user);
    public int runAs (String lockfile, String group, String user) {
        return __runAs (lockfile, group, user);
    }
    /*
    Returns true if the underlying libzmq supports CURVE security.
    Uses a heuristic probe according to the version of libzmq being used.
    */
    native static boolean __hasCurve ();
    public boolean hasCurve () {
        return __hasCurve ();
    }
    /*
    Configure the number of I/O threads that ZeroMQ will use. A good
    rule of thumb is one thread per gigabit of traffic in or out. The
    default is 1, sufficient for most applications. If the environment
    variable ZSYS_IO_THREADS is defined, that provides the default.
    Note that this method is valid only before any socket is created.
    */
    native static void __setIoThreads (long ioThreads);
    public void setIoThreads (long ioThreads) {
        __setIoThreads (ioThreads);
    }
    /*
    Configure the scheduling policy of the ZMQ context thread pool.
    Not available on Windows. See the sched_setscheduler man page or sched.h
    for more information. If the environment variable ZSYS_THREAD_SCHED_POLICY
    is defined, that provides the default.
    Note that this method is valid only before any socket is created.
    */
    native static void __setThreadSchedPolicy (int policy);
    public void setThreadSchedPolicy (int policy) {
        __setThreadSchedPolicy (policy);
    }
    /*
    Configure the scheduling priority of the ZMQ context thread pool.
    Not available on Windows. See the sched_setscheduler man page or sched.h
    for more information. If the environment variable ZSYS_THREAD_PRIORITY is
    defined, that provides the default.
    Note that this method is valid only before any socket is created.
    */
    native static void __setThreadPriority (int priority);
    public void setThreadPriority (int priority) {
        __setThreadPriority (priority);
    }
    /*
    Configure the number of sockets that ZeroMQ will allow. The default
    is 1024. The actual limit depends on the system, and you can query it
    by using zsys_socket_limit (). A value of zero means "maximum".
    Note that this method is valid only before any socket is created.
    */
    native static void __setMaxSockets (long maxSockets);
    public void setMaxSockets (long maxSockets) {
        __setMaxSockets (maxSockets);
    }
    /*
    Return maximum number of ZeroMQ sockets that the system will support.
    */
    native static long __socketLimit ();
    public long socketLimit () {
        return __socketLimit ();
    }
    /*
    Configure the maximum allowed size of a message sent.
    The default is INT_MAX.
    */
    native static void __setMaxMsgsz (int maxMsgsz);
    public void setMaxMsgsz (int maxMsgsz) {
        __setMaxMsgsz (maxMsgsz);
    }
    /*
    Return maximum message size.
    */
    native static int __maxMsgsz ();
    public int maxMsgsz () {
        return __maxMsgsz ();
    }
    /*
    Configure the threshold value of filesystem object age per st_mtime
    that should elapse until we consider that object "stable" at the
    current zclock_time() moment.
    The default is S_DEFAULT_ZSYS_FILE_STABLE_AGE_MSEC defined in zsys.c
    which generally depends on host OS, with fallback value of 5000.
    */
    native static void __setFileStableAgeMsec (long fileStableAgeMsec);
    public void setFileStableAgeMsec (long fileStableAgeMsec) {
        __setFileStableAgeMsec (fileStableAgeMsec);
    }
    /*
    Return current threshold value of file stable age in msec.
    This can be used in code that chooses to wait for this timeout
    before testing if a filesystem object is "stable" or not.
    */
    native static long __fileStableAgeMsec ();
    public long fileStableAgeMsec () {
        return __fileStableAgeMsec ();
    }
    /*
    Configure the default linger timeout in msecs for new zsock instances.
    You can also set this separately on each zsock_t instance. The default
    linger time is zero, i.e. any pending messages will be dropped. If the
    environment variable ZSYS_LINGER is defined, that provides the default.
    Note that process exit will typically be delayed by the linger time.
    */
    native static void __setLinger (long linger);
    public void setLinger (long linger) {
        __setLinger (linger);
    }
    /*
    Configure the default outgoing pipe limit (HWM) for new zsock instances.
    You can also set this separately on each zsock_t instance. The default
    HWM is 1,000, on all versions of ZeroMQ. If the environment variable
    ZSYS_SNDHWM is defined, that provides the default. Note that a value of
    zero means no limit, i.e. infinite memory consumption.
    */
    native static void __setSndhwm (long sndhwm);
    public void setSndhwm (long sndhwm) {
        __setSndhwm (sndhwm);
    }
    /*
    Configure the default incoming pipe limit (HWM) for new zsock instances.
    You can also set this separately on each zsock_t instance. The default
    HWM is 1,000, on all versions of ZeroMQ. If the environment variable
    ZSYS_RCVHWM is defined, that provides the default. Note that a value of
    zero means no limit, i.e. infinite memory consumption.
    */
    native static void __setRcvhwm (long rcvhwm);
    public void setRcvhwm (long rcvhwm) {
        __setRcvhwm (rcvhwm);
    }
    /*
    Configure the default HWM for zactor internal pipes; this is set on both
    ends of the pipe, for outgoing messages only (sndhwm). The default HWM is
    1,000, on all versions of ZeroMQ. If the environment var ZSYS_ACTORHWM is
    defined, that provides the default. Note that a value of zero means no
    limit, i.e. infinite memory consumption.
    */
    native static void __setPipehwm (long pipehwm);
    public void setPipehwm (long pipehwm) {
        __setPipehwm (pipehwm);
    }
    /*
    Return the HWM for zactor internal pipes.
    */
    native static long __pipehwm ();
    public long pipehwm () {
        return __pipehwm ();
    }
    /*
    Configure use of IPv6 for new zsock instances. By default sockets accept
    and make only IPv4 connections. When you enable IPv6, sockets will accept
    and connect to both IPv4 and IPv6 peers. You can override the setting on
    each zsock_t instance. The default is IPv4 only (ipv6 set to 0). If the
    environment variable ZSYS_IPV6 is defined (as 1 or 0), this provides the
    default. Note: has no effect on ZMQ v2.
    */
    native static void __setIpv6 (int ipv6);
    public void setIpv6 (int ipv6) {
        __setIpv6 (ipv6);
    }
    /*
    Return use of IPv6 for zsock instances.
    */
    native static int __ipv6 ();
    public int ipv6 () {
        return __ipv6 ();
    }
    /*
    Set network interface name to use for broadcasts, particularly zbeacon.
    This lets the interface be configured for test environments where required.
    For example, on Mac OS X, zbeacon cannot bind to 255.255.255.255 which is
    the default when there is no specified interface. If the environment
    variable ZSYS_INTERFACE is set, use that as the default interface name.
    Setting the interface to "*" means "use all available interfaces".
    */
    native static void __setInterface (String value);
    public void setInterface (String value) {
        __setInterface (value);
    }
    /*
    Return network interface to use for broadcasts, or "" if none was set.
    Note that "interface" is among reserved Java keywords, so it had to be
    renamed here manually to "getInterface".
    */
    native static String __interface ();
    public String getInterface () {
        return __interface ();
    }
    /*
    Set IPv6 address to use zbeacon socket, particularly for receiving zbeacon.
    This needs to be set IPv6 is enabled as IPv6 can have multiple addresses
    on a given interface. If the environment variable ZSYS_IPV6_ADDRESS is set,
    use that as the default IPv6 address.
    */
    native static void __setIpv6Address (String value);
    public void setIpv6Address (String value) {
        __setIpv6Address (value);
    }
    /*
    Return IPv6 address to use for zbeacon reception, or "" if none was set.
    */
    native static String __ipv6Address ();
    public String ipv6Address () {
        return __ipv6Address ();
    }
    /*
    Set IPv6 milticast address to use for sending zbeacon messages. This needs
    to be set if IPv6 is enabled. If the environment variable
    ZSYS_IPV6_MCAST_ADDRESS is set, use that as the default IPv6 multicast
    address.
    */
    native static void __setIpv6McastAddress (String value);
    public void setIpv6McastAddress (String value) {
        __setIpv6McastAddress (value);
    }
    /*
    Return IPv6 multicast address to use for sending zbeacon, or "" if none was
    set.
    */
    native static String __ipv6McastAddress ();
    public String ipv6McastAddress () {
        return __ipv6McastAddress ();
    }
    /*
    Configure the automatic use of pre-allocated FDs when creating new sockets.
    If 0 (default), nothing will happen. Else, when a new socket is bound, the
    system API will be used to check if an existing pre-allocated FD with a
    matching port (if TCP) or path (if IPC) exists, and if it does it will be
    set via the ZMQ_USE_FD socket option so that the library will use it
    instead of creating a new socket.
    */
    native static void __setAutoUseFd (int autoUseFd);
    public void setAutoUseFd (int autoUseFd) {
        __setAutoUseFd (autoUseFd);
    }
    /*
    Return use of automatic pre-allocated FDs for zsock instances.
    */
    native static int __autoUseFd ();
    public int autoUseFd () {
        return __autoUseFd ();
    }
    /*
    Set log identity, which is a string that prefixes all log messages sent
    by this process. The log identity defaults to the environment variable
    ZSYS_LOGIDENT, if that is set.
    */
    native static void __setLogident (String value);
    public void setLogident (String value) {
        __setLogident (value);
    }
    /*
    Sends log output to a PUB socket bound to the specified endpoint. To
    collect such log output, create a SUB socket, subscribe to the traffic
    you care about, and connect to the endpoint. Log traffic is sent as a
    single string frame, in the same format as when sent to stdout. The
    log system supports a single sender; multiple calls to this method will
    bind the same sender to multiple endpoints. To disable the sender, call
    this method with a null argument.
    */
    native static void __setLogsender (String endpoint);
    public void setLogsender (String endpoint) {
        __setLogsender (endpoint);
    }
    /*
    Enable or disable logging to the system facility (syslog on POSIX boxes,
    event log on Windows). By default this is disabled.
    */
    native static void __setLogsystem (boolean logsystem);
    public void setLogsystem (boolean logsystem) {
        __setLogsystem (logsystem);
    }
    /*
    Log error condition - highest priority
    */
    native static void __error (String format);
    public void error (String format) {
        __error (format);
    }
    /*
    Log warning condition - high priority
    */
    native static void __warning (String format);
    public void warning (String format) {
        __warning (format);
    }
    /*
    Log normal, but significant, condition - normal priority
    */
    native static void __notice (String format);
    public void notice (String format) {
        __notice (format);
    }
    /*
    Log informational message - low priority
    */
    native static void __info (String format);
    public void info (String format) {
        __info (format);
    }
    /*
    Log debug-level message - lowest priority
    */
    native static void __debug (String format);
    public void debug (String format) {
        __debug (format);
    }
    /*
    Self test of this class.
    */
    native static void __test (boolean verbose);
    public static void test (boolean verbose) {
        __test (verbose);
    }
}
